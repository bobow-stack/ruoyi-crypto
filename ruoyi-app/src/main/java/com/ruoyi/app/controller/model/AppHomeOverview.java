package com.ruoyi.app.controller.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class AppHomeOverview
{
    private AssetSummary asset;
    private List<TickerItem> tickers;
    private List<PositionItem> positions;
    private List<AnnouncementItem> announcements;

    public AssetSummary getAsset()
    {
        return asset;
    }

    public void setAsset(AssetSummary asset)
    {
        this.asset = asset;
    }

    public List<TickerItem> getTickers()
    {
        return tickers;
    }

    public void setTickers(List<TickerItem> tickers)
    {
        this.tickers = tickers;
    }

    public List<PositionItem> getPositions()
    {
        return positions;
    }

    public void setPositions(List<PositionItem> positions)
    {
        this.positions = positions;
    }

    public List<AnnouncementItem> getAnnouncements()
    {
        return announcements;
    }

    public void setAnnouncements(List<AnnouncementItem> announcements)
    {
        this.announcements = announcements;
    }

    public static class AssetSummary
    {
        private String asset;
        private BigDecimal totalEquity;
        private BigDecimal available;
        private BigDecimal marginUsed;
        private BigDecimal unrealizedPnl;

        public String getAsset()
        {
            return asset;
        }

        public void setAsset(String asset)
        {
            this.asset = asset;
        }

        public BigDecimal getTotalEquity()
        {
            return totalEquity;
        }

        public void setTotalEquity(BigDecimal totalEquity)
        {
            this.totalEquity = totalEquity;
        }

        public BigDecimal getAvailable()
        {
            return available;
        }

        public void setAvailable(BigDecimal available)
        {
            this.available = available;
        }

        public BigDecimal getMarginUsed()
        {
            return marginUsed;
        }

        public void setMarginUsed(BigDecimal marginUsed)
        {
            this.marginUsed = marginUsed;
        }

        public BigDecimal getUnrealizedPnl()
        {
            return unrealizedPnl;
        }

        public void setUnrealizedPnl(BigDecimal unrealizedPnl)
        {
            this.unrealizedPnl = unrealizedPnl;
        }
    }

    public static class TickerItem
    {
        private Long contractId;
        private String symbol;
        private BigDecimal lastPrice;
        private BigDecimal change24h;
        private BigDecimal turnover24h;

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

        public BigDecimal getLastPrice()
        {
            return lastPrice;
        }

        public void setLastPrice(BigDecimal lastPrice)
        {
            this.lastPrice = lastPrice;
        }

        public BigDecimal getChange24h()
        {
            return change24h;
        }

        public void setChange24h(BigDecimal change24h)
        {
            this.change24h = change24h;
        }

        public BigDecimal getTurnover24h()
        {
            return turnover24h;
        }

        public void setTurnover24h(BigDecimal turnover24h)
        {
            this.turnover24h = turnover24h;
        }
    }

    public static class PositionItem
    {
        private Long contractId;
        private String symbol;
        private Long side;
        private Long leverage;
        private BigDecimal qty;
        private BigDecimal entryPrice;
        private BigDecimal markPrice;
        private BigDecimal unrealizedPnl;

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

        public Long getSide()
        {
            return side;
        }

        public void setSide(Long side)
        {
            this.side = side;
        }

        public Long getLeverage()
        {
            return leverage;
        }

        public void setLeverage(Long leverage)
        {
            this.leverage = leverage;
        }

        public BigDecimal getQty()
        {
            return qty;
        }

        public void setQty(BigDecimal qty)
        {
            this.qty = qty;
        }

        public BigDecimal getEntryPrice()
        {
            return entryPrice;
        }

        public void setEntryPrice(BigDecimal entryPrice)
        {
            this.entryPrice = entryPrice;
        }

        public BigDecimal getMarkPrice()
        {
            return markPrice;
        }

        public void setMarkPrice(BigDecimal markPrice)
        {
            this.markPrice = markPrice;
        }

        public BigDecimal getUnrealizedPnl()
        {
            return unrealizedPnl;
        }

        public void setUnrealizedPnl(BigDecimal unrealizedPnl)
        {
            this.unrealizedPnl = unrealizedPnl;
        }
    }

    public static class AnnouncementItem
    {
        private Long id;
        private String title;
        private Date publishTime;

        public Long getId()
        {
            return id;
        }

        public void setId(Long id)
        {
            this.id = id;
        }

        public String getTitle()
        {
            return title;
        }

        public void setTitle(String title)
        {
            this.title = title;
        }

        public Date getPublishTime()
        {
            return publishTime;
        }

        public void setPublishTime(Date publishTime)
        {
            this.publishTime = publishTime;
        }
    }
}
