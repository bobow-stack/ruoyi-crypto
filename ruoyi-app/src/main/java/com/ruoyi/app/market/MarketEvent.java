package com.ruoyi.app.market;

import java.math.BigDecimal;

/**
 * Unified market event.
 */
public class MarketEvent
{
    private Long contractId;

    private String symbol;

    private long ts;

    private BigDecimal lastPrice;

    private BigDecimal markPrice;

    private BigDecimal indexPrice;

    private BigDecimal bestBid;

    private BigDecimal bestAsk;

    private BigDecimal bidQty;

    private BigDecimal askQty;

    private BigDecimal fundingRate;

    private Long nextFundingTime;

    public Long getContractId()
    {
        return contractId;
    }

    public void setContractId(Long contractId)
    {
        this.contractId = contractId;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public void setSymbol(String symbol)
    {
        this.symbol = symbol;
    }

    public long getTs()
    {
        return ts;
    }

    public void setTs(long ts)
    {
        this.ts = ts;
    }

    public BigDecimal getLastPrice()
    {
        return lastPrice;
    }

    public void setLastPrice(BigDecimal lastPrice)
    {
        this.lastPrice = lastPrice;
    }

    public BigDecimal getMarkPrice()
    {
        return markPrice;
    }

    public void setMarkPrice(BigDecimal markPrice)
    {
        this.markPrice = markPrice;
    }

    public BigDecimal getIndexPrice()
    {
        return indexPrice;
    }

    public void setIndexPrice(BigDecimal indexPrice)
    {
        this.indexPrice = indexPrice;
    }

    public BigDecimal getBestBid()
    {
        return bestBid;
    }

    public void setBestBid(BigDecimal bestBid)
    {
        this.bestBid = bestBid;
    }

    public BigDecimal getBestAsk()
    {
        return bestAsk;
    }

    public void setBestAsk(BigDecimal bestAsk)
    {
        this.bestAsk = bestAsk;
    }

    public BigDecimal getBidQty()
    {
        return bidQty;
    }

    public void setBidQty(BigDecimal bidQty)
    {
        this.bidQty = bidQty;
    }

    public BigDecimal getAskQty()
    {
        return askQty;
    }

    public void setAskQty(BigDecimal askQty)
    {
        this.askQty = askQty;
    }

    public BigDecimal getFundingRate()
    {
        return fundingRate;
    }

    public void setFundingRate(BigDecimal fundingRate)
    {
        this.fundingRate = fundingRate;
    }

    public Long getNextFundingTime()
    {
        return nextFundingTime;
    }

    public void setNextFundingTime(Long nextFundingTime)
    {
        this.nextFundingTime = nextFundingTime;
    }
}
