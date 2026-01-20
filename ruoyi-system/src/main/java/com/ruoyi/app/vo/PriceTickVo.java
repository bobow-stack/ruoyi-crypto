package com.ruoyi.app.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 价格快照(币安推送落库，用于触发成交/强平审计；大建议分区/分) VO（用于接口入参/出参）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PriceTickVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

        /** 主键ID(建议雪花ID) */
    private Long id;

        /** 合约ID */
    private Long contractId;

        /** 价格来源：BINANCE/OTHER(预留) */
    private String source;

        /** 最新成交价(Last) */
    private BigDecimal lastPrice;

        /** 标记价格(Mark，用于强平/盈亏) */
    private BigDecimal markPrice;

        /** 指数价格(Index，可选) */
    private BigDecimal indexPrice;

        /** 最优买价(可选) */
    private BigDecimal bestBid;

        /** 最优卖价(可选) */
    private BigDecimal bestAsk;

        /** 最优买量(可选) */
    private BigDecimal bidQty;

        /** 最优卖量(可选) */
    private BigDecimal askQty;

        /** 数据时间(推送时间/事件时间) */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ts;

        /** 系统接收时间(入库时间) */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date recvTime;

}
