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
 * 强平事件(以价格推送触发，字段更完整可审计)对象 u_liquidation_event
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@TableName("u_liquidation_event")
public class LiquidationEvent
        {
        private static final long serialVersionUID = 1L;

            /** 强平事件ID(主键) */
            @TableId(value = "id", type = IdType.INPUT)
    private Long id;

            /** 用户UID */
        @TableField("user_id")
        @Excel(name = "用户UID")
    private Long userId;

            /** 合约ID */
        @TableField("contract_id")
        @Excel(name = "合约ID")
    private Long contractId;

            /** 持仓ID(u_position.id) */
        @TableField("position_id")
        @Excel(name = "持仓ID(u_position.id)")
    private Long positionId;

            /** 方向：2LONG；3SHORT */
        @TableField("position_side")
        @Excel(name = "方向：2LONG；3SHORT")
    private Long positionSide;

            /** 保证金模式：1全仓；2逐仓 */
        @TableField("margin_mode")
        @Excel(name = "保证金模式：1全仓；2逐仓")
    private Long marginMode;

            /** 触发时间 */
        @TableField("trigger_time")
        @Excel(name = "触发时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date triggerTime;

            /** 触发价格类型：1最新价；2标记价(推荐)；3指数价 */
        @TableField("trigger_type")
        @Excel(name = "触发价格类型：1最新价；2标记价(推荐)；3指数价")
    private Long triggerType;

            /** 触发价格(当时用于判断强平的价格) */
        @TableField("trigger_price")
        @Excel(name = "触发价格(当时用于判断强平的价格)")
    private BigDecimal triggerPrice;

            /** 触发价格快照ID(u_price_tick.id) */
        @TableField("trigger_tick_id")
        @Excel(name = "触发价格快照ID(u_price_tick.id)")
    private Long triggerTickId;

            /** 强平前账户权益(可选，用于审计) */
        @TableField("equity_before")
        @Excel(name = "强平前账户权益(可选，用于审计)")
    private BigDecimal equityBefore;

            /** 触发时保证金率(可选，便于分析) */
        @TableField("margin_ratio")
        @Excel(name = "触发时保证金率(可选，便于分析)")
    private BigDecimal marginRatio;

            /** 触发时维持保证金率(MMR快照) */
        @TableField("mmr")
        @Excel(name = "触发时维持保证金率(MMR快照)")
    private BigDecimal mmr;

            /** 系统计算的强平价(触发时计算结果) */
        @TableField("liq_price_calc")
        @Excel(name = "系统计算的强平价(触发时计算结果)")
    private BigDecimal liqPriceCalc;

            /** 强平数量 */
        @TableField("liq_qty")
        @Excel(name = "强平数量")
    private BigDecimal liqQty;

            /** 强平执行均价(完成后回填) */
        @TableField("exec_price_avg")
        @Excel(name = "强平执行均价(完成后回填)")
    private BigDecimal execPriceAvg;

            /** 状态：0进行中；1完成；2失败 */
        @TableField("status")
        @Excel(name = "状态：0进行中；1完成；2失败")
    private Long status;

            /** 系统生成的平仓订单ID(u_order.id) */
        @TableField("system_order_id")
        @Excel(name = "系统生成的平仓订单ID(u_order.id)")
    private Long systemOrderId;

            /** 系统平仓成交ID(u_trade.id，可选仅记录首笔) */
        @TableField("system_trade_id")
        @Excel(name = "系统平仓成交ID(u_trade.id，可选仅记录首笔)")
    private Long systemTradeId;

            /** 坏账/穿仓金额(若出现，正数表示亏空) */
        @TableField("bad_debt")
        @Excel(name = "坏账/穿仓金额(若出现，正数表示亏空)")
    private BigDecimal badDebt;

            /** 保险基金覆盖金额(如有) */
        @TableField("insurance_cover")
        @Excel(name = "保险基金覆盖金额(如有)")
    private BigDecimal insuranceCover;

            /** 创建时间 */
        @TableField("create_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

            /** 更新时间 */
        @TableField("update_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

        }
