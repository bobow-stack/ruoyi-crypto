package com.ruoyi.app.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.IdType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 价格快照(币安推送落库，用于触发成交/强平审计；大建议分区/分)对象 u_price_tick
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@TableName("u_price_tick")
public class PriceTick
        {
        private static final long serialVersionUID = 1L;

            /** 主键ID(建议雪花ID) */
            @TableId(value = "id", type = IdType.INPUT)
    private Long id;

            /** 合约ID */
        @TableField("contract_id")
        @Excel(name = "合约ID")
    private Long contractId;

            /** 价格来源：BINANCE/OTHER(预留) */
        @TableField("source")
        @Excel(name = "价格来源：BINANCE/OTHER(预留)")
    private String source;

            /** 最新成交价(Last) */
        @TableField("last_price")
        @Excel(name = "最新成交价(Last)")
    private BigDecimal lastPrice;

            /** 标记价格(Mark，用于强平/盈亏) */
        @TableField("mark_price")
        @Excel(name = "标记价格(Mark，用于强平/盈亏)")
    private BigDecimal markPrice;

            /** 指数价格(Index，可选) */
        @TableField("index_price")
        @Excel(name = "指数价格(Index，可选)")
    private BigDecimal indexPrice;

            /** 最优买价(可选) */
        @TableField("best_bid")
        @Excel(name = "最优买价(可选)")
    private BigDecimal bestBid;

            /** 最优卖价(可选) */
        @TableField("best_ask")
        @Excel(name = "最优卖价(可选)")
    private BigDecimal bestAsk;

            /** 最优买量(可选) */
        @TableField("bid_qty")
        @Excel(name = "最优买量(可选)")
    private BigDecimal bidQty;

            /** 最优卖量(可选) */
        @TableField("ask_qty")
        @Excel(name = "最优卖量(可选)")
    private BigDecimal askQty;

            /** 数据时间(推送时间/事件时间) */
        @TableField("ts")
        @Excel(name = "数据时间(推送时间/事件时间)")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ts;

            /** 系统接收时间(入库时间) */
        @TableField("recv_time")
        @Excel(name = "系统接收时间(入库时间)")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date recvTime;

        }
