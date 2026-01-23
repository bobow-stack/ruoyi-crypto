package com.ruoyi.app.controller.model;

import java.math.BigDecimal;

/**
 * 市价单请求。
 */
public class MarketOrderRequest
{
    /**
     * 合约ID
     */
    private Long contractId;

    /**
     * 方向：1买(BUY)；2卖(SELL)
     */
    private Long side;

    /**
     * 数量
     */
    private BigDecimal qty;

    /**
     * 杠杆倍数
     */
    private Long leverage;

    /**
     * 保证金模式：1全仓；2逐仓
     */
    private Long marginMode;

    /**
     * 只减仓：0否；1是
     */
    private Long reduceOnly;

    /**
     * 客户端幂等ID
     */
    private String clientOrderId;

    public Long getContractId()
    {
        return contractId;
    }

    public void setContractId(Long contractId)
    {
        this.contractId = contractId;
    }

    public Long getSide()
    {
        return side;
    }

    public void setSide(Long side)
    {
        this.side = side;
    }

    public BigDecimal getQty()
    {
        return qty;
    }

    public void setQty(BigDecimal qty)
    {
        this.qty = qty;
    }

    public Long getLeverage()
    {
        return leverage;
    }

    public void setLeverage(Long leverage)
    {
        this.leverage = leverage;
    }

    public Long getMarginMode()
    {
        return marginMode;
    }

    public void setMarginMode(Long marginMode)
    {
        this.marginMode = marginMode;
    }

    public Long getReduceOnly()
    {
        return reduceOnly;
    }

    public void setReduceOnly(Long reduceOnly)
    {
        this.reduceOnly = reduceOnly;
    }

    public String getClientOrderId()
    {
        return clientOrderId;
    }

    public void setClientOrderId(String clientOrderId)
    {
        this.clientOrderId = clientOrderId;
    }
}
