package com.ruoyi.app.controller.model;

import java.math.BigDecimal;

public class MarketOrderResult
{
    private Long orderId;
    private Long tradeId;
    private Long positionId;
    private BigDecimal execPrice;
    private BigDecimal feeAmount;
    private BigDecimal pnl;

    public Long getOrderId()
    {
        return orderId;
    }

    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }

    public Long getTradeId()
    {
        return tradeId;
    }

    public void setTradeId(Long tradeId)
    {
        this.tradeId = tradeId;
    }

    public Long getPositionId()
    {
        return positionId;
    }

    public void setPositionId(Long positionId)
    {
        this.positionId = positionId;
    }

    public BigDecimal getExecPrice()
    {
        return execPrice;
    }

    public void setExecPrice(BigDecimal execPrice)
    {
        this.execPrice = execPrice;
    }

    public BigDecimal getFeeAmount()
    {
        return feeAmount;
    }

    public void setFeeAmount(BigDecimal feeAmount)
    {
        this.feeAmount = feeAmount;
    }

    public BigDecimal getPnl()
    {
        return pnl;
    }

    public void setPnl(BigDecimal pnl)
    {
        this.pnl = pnl;
    }
}
