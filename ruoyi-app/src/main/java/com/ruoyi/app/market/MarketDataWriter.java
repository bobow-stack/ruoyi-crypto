package com.ruoyi.app.market;

import com.ruoyi.app.domain.FundingRate;
import com.ruoyi.app.domain.Kline;
import com.ruoyi.app.domain.PriceTick;
import com.ruoyi.app.domain.Ticker;
import com.ruoyi.app.service.IFundingRateService;
import com.ruoyi.app.service.IKlineService;
import com.ruoyi.app.service.IPriceTickService;
import com.ruoyi.app.service.ITickerService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 行情数据批量写入。
 * 通过定时批量落库降低 DB 压力。
 */
@Component
public class MarketDataWriter
{
    private static final Logger log = LoggerFactory.getLogger(MarketDataWriter.class);

    @Autowired
    private MarketDataProcessor processor;

    @Autowired
    private BinanceMarketProperties properties;

    @Autowired
    private ITickerService tickerService;

    @Autowired
    private IPriceTickService priceTickService;

    @Autowired
    private IKlineService klineService;

    @Autowired
    private IFundingRateService fundingRateService;

    private final ConcurrentLinkedQueue<PriceTick> tickQueue = new ConcurrentLinkedQueue<>();

    private final ConcurrentLinkedQueue<Kline> klineQueue = new ConcurrentLinkedQueue<>();

    private final ConcurrentLinkedQueue<FundingRate> fundingQueue = new ConcurrentLinkedQueue<>();

    private final Map<Long, Long> tickerIdMap = new ConcurrentHashMap<>();

    @javax.annotation.PostConstruct
    public void loadTickerIds()
    {
        List<Ticker> list = tickerService.list();
        for (Ticker ticker : list)
        {
            tickerIdMap.put(ticker.getContractId(), ticker.getId());
        }
    }

    public void enqueuePriceTick(PriceTick tick)
    {
        tickQueue.offer(tick);
    }

    public void enqueueKline(Kline kline)
    {
        klineQueue.offer(kline);
    }

    public void enqueueFundingRate(FundingRate fundingRate)
    {
        fundingQueue.offer(fundingRate);
    }

    @Scheduled(fixedDelayString = "${binance.market.tickerFlushMillis:500}")
    public void flushTicker()
    {
        List<Ticker> batch = new ArrayList<>();
        for (MarketSnapshot snapshot : processor.getSnapshotMap().values())
        {
            if (snapshot.getContractId() == null)
            {
                continue;
            }
            Ticker ticker = new Ticker();
            Long tickerId = tickerIdMap.get(snapshot.getContractId());
            if (tickerId == null)
            {
                // 首次写入：创建新行
                tickerId = MarketIdGenerator.nextId();
                tickerIdMap.put(snapshot.getContractId(), tickerId);
                ticker.setCreateTime(new Date());
            }
            ticker.setId(tickerId);
            ticker.setContractId(snapshot.getContractId());
            ticker.setLastPrice(snapshot.getLastPrice());
            ticker.setMarkPrice(snapshot.getMarkPrice());
            ticker.setIndexPrice(snapshot.getIndexPrice());
            ticker.setTs(new Date(snapshot.getTs() > 0 ? snapshot.getTs() : System.currentTimeMillis()));
            ticker.setUpdateTime(new Date());
            batch.add(ticker);
        }
        if (batch.isEmpty())
        {
            return;
        }
        try
        {
            // saveOrUpdateBatch 按 ID 做 upsert
            tickerService.saveOrUpdateBatch(batch, batch.size());
        }
        catch (Exception e)
        {
            log.warn("Failed to flush ticker batch: {}", e.getMessage());
        }
    }

    @Scheduled(fixedDelayString = "${binance.market.tickFlushMillis:1000}")
    public void flushTicks()
    {
        List<PriceTick> batch = drainQueue(tickQueue, 1000);
        if (batch.isEmpty())
        {
            return;
        }
        try
        {
            // 批量写入 tick 供审计
            priceTickService.saveBatch(batch, batch.size());
        }
        catch (Exception e)
        {
            log.warn("Failed to flush price ticks: {}", e.getMessage());
        }
    }

    @Scheduled(fixedDelay = 1000)
    public void flushKlines()
    {
        List<Kline> batch = drainQueue(klineQueue, 500);
        if (batch.isEmpty())
        {
            return;
        }
        try
        {
            // 批量写入已收盘 K 线
            klineService.saveBatch(batch, batch.size());
        }
        catch (Exception e)
        {
            log.warn("Failed to flush klines: {}", e.getMessage());
        }
    }

    @Scheduled(fixedDelay = 2000)
    public void flushFundingRates()
    {
        List<FundingRate> batch = drainQueue(fundingQueue, 200);
        if (batch.isEmpty())
        {
            return;
        }
        try
        {
            // 批量写入资金费率快照
            fundingRateService.saveBatch(batch, batch.size());
        }
        catch (Exception e)
        {
            log.warn("Failed to flush funding rates: {}", e.getMessage());
        }
    }

    private <T> List<T> drainQueue(ConcurrentLinkedQueue<T> queue, int max)
    {
        List<T> list = new ArrayList<>(max);
        // 限制每次排空数量，避免阻塞过久
        while (list.size() < max)
        {
            T item = queue.poll();
            if (item == null)
            {
                break;
            }
            list.add(item);
        }
        return list;
    }
}
