package com.ruoyi.app.market;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Binance market data settings.
 */
@Component
@ConfigurationProperties(prefix = "binance.market")
public class BinanceMarketProperties
{
    /**
     * Enable market data connection.
     */
    private boolean enabled = true;

    /**
     * WebSocket base endpoint (combined stream).
     */
    private String wsBaseUrl = "wss://fstream.binance.com/stream?streams=";

    /**
     * Enable !bookTicker stream.
     */
    private boolean enableBookTicker = true;

    /**
     * Enable markPrice stream.
     */
    private boolean enableMarkPrice = false;

    /**
     * Enable kline stream.
     */
    private boolean enableKline = true;

    /**
     * Kline intervals (binance format).
     */
    private List<String> klineIntervals = Arrays.asList("1m", "5m", "15m", "1h");

    /**
     * Reconnect base delay (ms).
     */
    private long reconnectBaseMillis = 1000;

    /**
     * Reconnect max delay (ms).
     */
    private long reconnectMaxMillis = 30000;

    /**
     * Heartbeat timeout (ms).
     */
    private long heartbeatTimeoutMillis = 15000;

    /**
     * Ticker flush interval (ms).
     */
    private long tickerFlushMillis = 500;

    /**
     * Price tick sample interval (ms).
     */
    private long tickSampleMillis = 1000;

    /**
     * Price tick flush interval (ms).
     */
    private long tickFlushMillis = 1000;

    /**
     * Price tick change threshold (rate). 0 disables change threshold.
     */
    private BigDecimal tickChangeRate = new BigDecimal("0.0002");

    /**
     * Proxy host (optional).
     */
    private String proxyHost;

    /**
     * Proxy port (optional).
     */
    private Integer proxyPort;

    /**
     * Proxy type: http or socks.
     */
    private String proxyType = "http";

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public String getWsBaseUrl()
    {
        return wsBaseUrl;
    }

    public void setWsBaseUrl(String wsBaseUrl)
    {
        this.wsBaseUrl = wsBaseUrl;
    }

    public boolean isEnableBookTicker()
    {
        return enableBookTicker;
    }

    public void setEnableBookTicker(boolean enableBookTicker)
    {
        this.enableBookTicker = enableBookTicker;
    }

    public boolean isEnableMarkPrice()
    {
        return enableMarkPrice;
    }

    public void setEnableMarkPrice(boolean enableMarkPrice)
    {
        this.enableMarkPrice = enableMarkPrice;
    }

    public boolean isEnableKline()
    {
        return enableKline;
    }

    public void setEnableKline(boolean enableKline)
    {
        this.enableKline = enableKline;
    }

    public List<String> getKlineIntervals()
    {
        return klineIntervals;
    }

    public void setKlineIntervals(List<String> klineIntervals)
    {
        this.klineIntervals = klineIntervals;
    }

    public long getReconnectBaseMillis()
    {
        return reconnectBaseMillis;
    }

    public void setReconnectBaseMillis(long reconnectBaseMillis)
    {
        this.reconnectBaseMillis = reconnectBaseMillis;
    }

    public long getReconnectMaxMillis()
    {
        return reconnectMaxMillis;
    }

    public void setReconnectMaxMillis(long reconnectMaxMillis)
    {
        this.reconnectMaxMillis = reconnectMaxMillis;
    }

    public long getHeartbeatTimeoutMillis()
    {
        return heartbeatTimeoutMillis;
    }

    public void setHeartbeatTimeoutMillis(long heartbeatTimeoutMillis)
    {
        this.heartbeatTimeoutMillis = heartbeatTimeoutMillis;
    }

    public long getTickerFlushMillis()
    {
        return tickerFlushMillis;
    }

    public void setTickerFlushMillis(long tickerFlushMillis)
    {
        this.tickerFlushMillis = tickerFlushMillis;
    }

    public long getTickSampleMillis()
    {
        return tickSampleMillis;
    }

    public void setTickSampleMillis(long tickSampleMillis)
    {
        this.tickSampleMillis = tickSampleMillis;
    }

    public long getTickFlushMillis()
    {
        return tickFlushMillis;
    }

    public void setTickFlushMillis(long tickFlushMillis)
    {
        this.tickFlushMillis = tickFlushMillis;
    }

    public BigDecimal getTickChangeRate()
    {
        return tickChangeRate;
    }

    public void setTickChangeRate(BigDecimal tickChangeRate)
    {
        this.tickChangeRate = tickChangeRate;
    }

    public String getProxyHost()
    {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost)
    {
        this.proxyHost = proxyHost;
    }

    public Integer getProxyPort()
    {
        return proxyPort;
    }

    public void setProxyPort(Integer proxyPort)
    {
        this.proxyPort = proxyPort;
    }

    public String getProxyType()
    {
        return proxyType;
    }

    public void setProxyType(String proxyType)
    {
        this.proxyType = proxyType;
    }
}
