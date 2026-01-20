package com.ruoyi.app.market;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.app.domain.Contract;
import com.ruoyi.app.domain.Kline;
import com.ruoyi.app.domain.PriceTick;
import com.ruoyi.app.domain.FundingRate;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.math.RoundingMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 解析 Binance WS 消息并更新缓存/队列。
 * 统一处理不同流的 payload。
 */
@Component
public class MarketDataProcessor
{
    private static final Logger log = LoggerFactory.getLogger(MarketDataProcessor.class);

    @Autowired
    private ContractSymbolCache symbolCache;

    @Autowired
    private BinanceMarketProperties properties;

    @Autowired
    private MarketDataWriter writer;

    private final Map<Long, MarketSnapshot> snapshotMap = new ConcurrentHashMap<>();

    private final Map<Long, Long> lastFundingTimeMap = new ConcurrentHashMap<>();

    private final AtomicLong lastMessageAt = new AtomicLong(0);

    public void handleMessage(String payload)
    {
        if (payload == null || payload.isEmpty())
        {
            return;
        }
        lastMessageAt.set(System.currentTimeMillis());
        try
        {
            JSONObject root = JSON.parseObject(payload);
            Object data = root.get("data");
            if (data == null)
            {
                // 部分消息可能不带 "data" 包裹
                handleEvent(root);
                return;
            }
            if (data instanceof JSONObject)
            {
                handleEvent((JSONObject) data);
            }
            else if (data instanceof JSONArray)
            {
                JSONArray array = (JSONArray) data;
                for (int i = 0; i < array.size(); i++)
                {
                    Object item = array.get(i);
                    if (item instanceof JSONObject)
                    {
                        handleEvent((JSONObject) item);
                    }
                }
            }
        }
        catch (Exception e)
        {
            log.warn("Failed to parse market message: {}", e.getMessage());
        }
    }

    public Map<Long, MarketSnapshot> getSnapshotMap()
    {
        return snapshotMap;
    }

    public long getLastMessageAt()
    {
        return lastMessageAt.get();
    }

    private void handleEvent(JSONObject data)
    {
        String eventType = data.getString("e");
        // Kline 流
        if ("kline".equals(eventType))
        {
            handleKline(data);
            return;
        }
        // 标记价格 / 资金费率流
        if ("markPriceUpdate".equals(eventType))
        {
            handleMarkPrice(data);
            return;
        }
        // 最优档行情流
        if ("bookTicker".equals(eventType) || (data.containsKey("b") && data.containsKey("a")))
        {
            handleBookTicker(data);
        }
    }

    private void handleBookTicker(JSONObject data)
    {
        String symbol = data.getString("s");
        Contract contract = symbolCache.getBySymbol(symbol);
        if (contract == null)
        {
            return;
        }
        long ts = data.getLongValue("E", System.currentTimeMillis());
        MarketSnapshot snapshot = getOrCreateSnapshot(contract, symbol, ts);
        // 最优买卖一档快照
        snapshot.setBestBid(decimal(data.getString("b")));
        snapshot.setBidQty(decimal(data.getString("B")));
        snapshot.setBestAsk(decimal(data.getString("a")));
        snapshot.setAskQty(decimal(data.getString("A")));
        snapshot.setTs(ts);
        maybeSampleTick(snapshot, ts);
    }

    private void handleMarkPrice(JSONObject data)
    {
        String symbol = data.getString("s");
        Contract contract = symbolCache.getBySymbol(symbol);
        if (contract == null)
        {
            return;
        }
        long ts = data.getLongValue("E", System.currentTimeMillis());
        MarketSnapshot snapshot = getOrCreateSnapshot(contract, symbol, ts);
        // 标记价/指数价/资金费率
        snapshot.setMarkPrice(decimal(data.getString("p")));
        snapshot.setIndexPrice(decimal(data.getString("i")));
        snapshot.setFundingRate(decimal(data.getString("r")));
        if (data.containsKey("T"))
        {
            snapshot.setNextFundingTime(data.getLong("T"));
        }
        if (snapshot.getLastPrice() == null)
        {
            snapshot.setLastPrice(snapshot.getMarkPrice());
        }
        snapshot.setTs(ts);
        maybeSampleTick(snapshot, ts);
        maybePersistFunding(snapshot, ts);
        log.debug("Market {} mark={} bid={} ask={} ts={}", symbol, snapshot.getMarkPrice(), snapshot.getBestBid(),
                snapshot.getBestAsk(), ts);
    }

    private void handleKline(JSONObject data)
    {
        JSONObject k = data.getJSONObject("k");
        if (k == null)
        {
            return;
        }
        String symbol = k.getString("s");
        Contract contract = symbolCache.getBySymbol(symbol);
        if (contract == null)
        {
            return;
        }
        boolean closed = k.getBooleanValue("x");
        long eventTime = data.getLongValue("E", System.currentTimeMillis());
        MarketSnapshot snapshot = getOrCreateSnapshot(contract, symbol, eventTime);
        BigDecimal close = decimal(k.getString("c"));
        if (close != null)
        {
            snapshot.setLastPrice(close);
            snapshot.setTs(eventTime);
            maybeSampleTick(snapshot, eventTime);
        }
        if (!closed)
        {
            return;
        }
        // 只落库已收盘 K 线
        Kline kline = new Kline();
        kline.setId(MarketIdGenerator.nextId());
        kline.setContractId(contract.getId());
        kline.setIntervalMin(intervalToMinutes(k.getString("i")));
        kline.setOpenTime(new Date(k.getLongValue("t")));
        kline.setOpenPrice(decimal(k.getString("o")));
        kline.setHighPrice(decimal(k.getString("h")));
        kline.setLowPrice(decimal(k.getString("l")));
        kline.setClosePrice(close);
        kline.setVolume(decimal(k.getString("v")));
        kline.setTurnover(decimal(k.getString("q")));
        kline.setTradeCount(k.getLong("n"));
        kline.setCreateTime(new Date());
        writer.enqueueKline(kline);
    }

    private MarketSnapshot getOrCreateSnapshot(Contract contract, String symbol, long ts)
    {
        return snapshotMap.computeIfAbsent(contract.getId(), id -> {
            MarketSnapshot snap = new MarketSnapshot();
            snap.setContractId(contract.getId());
            snap.setSymbol(symbol);
            snap.setTs(ts);
            return snap;
        });
    }

    private void maybeSampleTick(MarketSnapshot snapshot, long eventTs)
    {
        BigDecimal price = snapshot.getMarkPrice() != null ? snapshot.getMarkPrice() : snapshot.getLastPrice();
        if (price == null)
        {
            return;
        }
        long now = System.currentTimeMillis();
        long lastTick = snapshot.getLastTickTime();
        // 时间采样
        if (eventTs - lastTick < properties.getTickSampleMillis())
        {
            return;
        }
        if (snapshot.getLastTickPrice() != null && properties.getTickChangeRate() != null
                && properties.getTickChangeRate().compareTo(BigDecimal.ZERO) > 0)
        {
            BigDecimal last = snapshot.getLastTickPrice();
            BigDecimal change = price.subtract(last).abs();
            BigDecimal rate = change.divide(last, 8, RoundingMode.HALF_UP);
            // 涨跌幅阈值采样
            if (rate.compareTo(properties.getTickChangeRate()) < 0)
            {
                return;
            }
        }
        snapshot.setLastTickTime(eventTs);
        snapshot.setLastTickPrice(price);

        PriceTick tick = new PriceTick();
        tick.setId(MarketIdGenerator.nextId());
        tick.setContractId(snapshot.getContractId());
        tick.setSource("BINANCE");
        tick.setLastPrice(snapshot.getLastPrice());
        tick.setMarkPrice(snapshot.getMarkPrice());
        tick.setIndexPrice(snapshot.getIndexPrice());
        tick.setBestBid(snapshot.getBestBid());
        tick.setBestAsk(snapshot.getBestAsk());
        tick.setBidQty(snapshot.getBidQty());
        tick.setAskQty(snapshot.getAskQty());
        tick.setTs(new Date(eventTs));
        tick.setRecvTime(new Date(now));
        writer.enqueuePriceTick(tick);
    }

    private void maybePersistFunding(MarketSnapshot snapshot, long eventTs)
    {
        if (snapshot.getFundingRate() == null || snapshot.getNextFundingTime() == null)
        {
            return;
        }
        Long lastTime = lastFundingTimeMap.get(snapshot.getContractId());
        if (snapshot.getNextFundingTime().equals(lastTime))
        {
            return;
        }
        // 同一结算时间仅保留一条
        lastFundingTimeMap.put(snapshot.getContractId(), snapshot.getNextFundingTime());
        FundingRate funding = new FundingRate();
        funding.setId(MarketIdGenerator.nextId());
        funding.setContractId(snapshot.getContractId());
        funding.setFundingRate(snapshot.getFundingRate());
        funding.setFundingTime(new Date(snapshot.getNextFundingTime()));
        funding.setCreateTime(new Date(eventTs));
        writer.enqueueFundingRate(funding);
    }

    private BigDecimal decimal(String value)
    {
        if (value == null)
        {
            return null;
        }
        try
        {
            return new BigDecimal(value);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    private long intervalToMinutes(String interval)
    {
        if (interval == null)
        {
            return 0L;
        }
        switch (interval)
        {
            case "1m":
                return 1L;
            case "5m":
                return 5L;
            case "15m":
                return 15L;
            case "1h":
                return 60L;
            case "4h":
                return 240L;
            case "1d":
                return 1440L;
            default:
                return 0L;
        }
    }
}
