package com.ruoyi.app.market;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import com.binance.connector.client.impl.WebSocketStreamClientImpl;
import com.binance.connector.client.utils.websocketcallback.WebSocketMessageCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Binance WebSocket 客户端（组合流），基于 binance-connector-java。
 */
@Component
public class BinanceWsClient
{
    private static final Logger log = LoggerFactory.getLogger(BinanceWsClient.class);

    @Autowired
    private BinanceMarketProperties properties;

    @Autowired
    private ContractSymbolCache symbolCache;

    @Autowired
    private MarketDataProcessor processor;

    private WebSocketStreamClientImpl client;

    private Integer connectionId;

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    private final AtomicInteger reconnectAttempts = new AtomicInteger(0);

    private final AtomicBoolean reconnecting = new AtomicBoolean(false);

    public synchronized void start()
    {
        if (!properties.isEnabled())
        {
            log.info("Binance market data disabled.");
            return;
        }
        connect();
        // 心跳检测：用于发现“静默断开”
        scheduler.scheduleWithFixedDelay(this::checkHeartbeat, 5, 5, TimeUnit.SECONDS);
    }

    private void connect()
    {
        List<String> streams = buildStreams();
        if (streams.isEmpty())
        {
            log.warn("Binance WS skipped: no streams configured.");
            return;
        }
        try
        {
            applyProxy();
            client = new WebSocketStreamClientImpl();
            // 组合流订阅会返回连接 id，用于 closeConnection
            connectionId = client.combineStreams(new ArrayList<>(streams),
                    (WebSocketMessageCallback) processor::handleMessage);
            reconnectAttempts.set(0);
            reconnecting.set(false);
            log.info("Binance WS connected via binance-connector-java.");
        }
        catch (Exception e)
        {
            log.warn("Binance WS connect failed: {}", e.getMessage());
            scheduleReconnect();
        }
    }

    private void checkHeartbeat()
    {
        if (client == null)
        {
            return;
        }
        long last = processor.getLastMessageAt();
        if (last <= 0)
        {
            return;
        }
        long idle = System.currentTimeMillis() - last;
        if (idle > properties.getHeartbeatTimeoutMillis())
        {
            log.warn("Binance WS heartbeat timeout, reconnecting.");
            tryReconnect();
        }
    }

    private void scheduleReconnect()
    {
        if (!reconnecting.compareAndSet(false, true))
        {
            return;
        }
        int attempt = reconnectAttempts.incrementAndGet();
        // 指数退避，并限制最大延迟
        long delay = properties.getReconnectBaseMillis() * (1L << Math.min(attempt, 6));
        delay = Math.min(delay, properties.getReconnectMaxMillis());
        scheduler.schedule(this::tryReconnect, delay, TimeUnit.MILLISECONDS);
    }

    private synchronized void tryReconnect()
    {
        closeClient();
        connect();
    }

    private void closeClient()
    {
        if (client == null)
        {
            return;
        }
        try
        {
            // 优先：SDK 提供批量关闭方法
            client.closeAllConnections();
            return;
        }
        catch (Exception e)
        {
            // 忽略，走句柄关闭
        }
        if (connectionId != null)
        {
            try
            {
                // 兜底：按 connection id 关闭
                client.closeConnection(connectionId);
            }
            catch (Exception e)
            {
                // ignore
            }
        }
    }

    private List<String> buildStreams()
    {
        List<String> streams = new ArrayList<>();
        if (properties.isEnableBookTicker())
        {
            streams.add("!bookTicker");
        }
        if (properties.isEnableMarkPrice())
        {
            for (String symbol : symbolCache.getActiveSymbolsLower())
            {
                streams.add(symbol + "@markPrice@1s");
            }
        }
        if (properties.isEnableKline())
        {
            for (String interval : properties.getKlineIntervals())
            {
                for (String symbol : symbolCache.getActiveSymbolsLower())
                {
                    streams.add(symbol + "@kline_" + interval);
                }
            }
        }
        return streams;
    }

    private void applyProxy()
    {
        if (properties.getProxyHost() == null || properties.getProxyHost().trim().isEmpty()
                || properties.getProxyPort() == null)
        {
            return;
        }
        String type = properties.getProxyType();
        if (type == null || type.trim().isEmpty())
        {
            type = "http";
        }
        // OkHttp 会读取 JVM 代理配置
        if ("socks".equalsIgnoreCase(type))
        {
            System.setProperty("socksProxyHost", properties.getProxyHost());
            System.setProperty("socksProxyPort", String.valueOf(properties.getProxyPort()));
        }
        else
        {
            System.setProperty("http.proxyHost", properties.getProxyHost());
            System.setProperty("http.proxyPort", String.valueOf(properties.getProxyPort()));
            System.setProperty("https.proxyHost", properties.getProxyHost());
            System.setProperty("https.proxyPort", String.valueOf(properties.getProxyPort()));
        }
    }
}