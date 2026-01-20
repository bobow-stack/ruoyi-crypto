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
 * 成交明细(平台对手方模式，审计靠价格快照)对象 u_trade
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@TableName("u_trade")
public class Trade
        {
        private static final long serialVersionUID = 1L;

            /** 成交ID(主键) */
            @TableId(value = "id", type = IdType.INPUT)
    private Long id;

            /** 订单ID(u_order.id) */
        @TableField("order_id")
        @Excel(name = "订单ID(u_order.id)")
    private Long orderId;

            /** 用户UID */
        @TableField("user_id")
        @Excel(name = "用户UID")
    private Long userId;

            /** 合约ID */
        @TableField("contract_id")
        @Excel(name = "合约ID")
    private Long contractId;

            /** 成交原因：1用户市价；2用户限价触发；3止盈触发；4止损触发；5强平成交；6管理员成交(预留) */
        @TableField("exec_reason")
        @Excel(name = "成交原因：1用户市价；2用户限价触发；3止盈触发；4止损触发；5强平成交；6管理员成交(预留)")
    private Long execReason;

            /** 成交价(基于币安价格源规则得出) */
        @TableField("exec_price")
        @Excel(name = "成交价(基于币安价格源规则得出)")
    private BigDecimal execPrice;

            /** 成交数量 */
        @TableField("qty")
        @Excel(name = "成交数量")
    private BigDecimal qty;

            /** 成交名义价值(可选：exec_price*qty*contract_size) */
        @TableField("turnover")
        @Excel(name = "成交名义价值(可选：exec_price*qty*contract_size)")
    private BigDecimal turnover;

            /** 手续费币种 */
        @TableField("fee_asset")
        @Excel(name = "手续费币种")
    private String feeAsset;

            /** 手续费率快照 */
        @TableField("fee_rate")
        @Excel(name = "手续费率快照")
    private BigDecimal feeRate;

            /** 手续费金额(正数表示扣费) */
        @TableField("fee_amount")
        @Excel(name = "手续费金额(正数表示扣费)")
    private BigDecimal feeAmount;

            /** 成交使用的价格快照ID(u_price_tick.id)，用于审计 */
        @TableField("price_tick_id")
        @Excel(name = "成交使用的价格快照ID(u_price_tick.id)，用于审计")
    private Long priceTickId;

            /** 成交时间(业务时间) */
        @TableField("ts")
        @Excel(name = "成交时间(业务时间)")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ts;

            /** 入库时间 */
        @TableField("create_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

        }
