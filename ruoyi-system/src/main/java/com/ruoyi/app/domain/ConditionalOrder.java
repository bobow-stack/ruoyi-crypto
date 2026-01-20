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
 * 条件单(止盈止损/条件触发)对象 u_conditional_order
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@TableName("u_conditional_order")
public class ConditionalOrder
        {
        private static final long serialVersionUID = 1L;

            /** 条件单ID(主键) */
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

            /** 绑定类型：1普通条件单；2绑定持仓TP/SL(推荐用2做止盈止损) */
        @TableField("bind_type")
        @Excel(name = "绑定类型：1普通条件单；2绑定持仓TP/SL(推荐用2做止盈止损)")
    private Long bindType;

            /** 绑定的持仓ID(u_position.id)，bind_type=2时必填 */
        @TableField("position_id")
        @Excel(name = "绑定的持仓ID(u_position.id)，bind_type=2时必填")
    private Long positionId;

            /** 持仓方向：1BOTH；2LONG；3SHORT(用于对冲/单向) */
        @TableField("position_side")
        @Excel(name = "持仓方向：1BOTH；2LONG；3SHORT(用于对冲/单向)")
    private Long positionSide;

            /** 触发类型：1最新价；2标记价；3指数价(强平/风险建议标记价) */
        @TableField("trigger_type")
        @Excel(name = "触发类型：1最新价；2标记价；3指数价(强平/风险建议标记价)")
    private Long triggerType;

            /** 触发条件：1>=；2<= */
        @TableField("trigger_condition")
        @Excel(name = "触发条件：1>=；2<=")
    private Long triggerCondition;

            /** 触发价格 */
        @TableField("trigger_price")
        @Excel(name = "触发价格")
    private BigDecimal triggerPrice;

            /** 触发动作：1触发后下市价单；2触发后下限价单；3触发后直接按规则成交(可选) */
        @TableField("action_type")
        @Excel(name = "触发动作：1触发后下市价单；2触发后下限价单；3触发后直接按规则成交(可选)")
    private Long actionType;

            /** 触发后订单方向：1买；2卖(止盈止损通常是平仓方向) */
        @TableField("order_side")
        @Excel(name = "触发后订单方向：1买；2卖(止盈止损通常是平仓方向)")
    private Long orderSide;

            /** 只减仓：0否；1是(止盈止损默认1) */
        @TableField("reduce_only")
        @Excel(name = "只减仓：0否；1是(止盈止损默认1)")
    private Long reduceOnly;

            /** 触发数量(0表示全平/按持仓全量；按你们规则定义) */
        @TableField("qty")
        @Excel(name = "触发数量(0表示全平/按持仓全量；按你们规则定义)")
    private BigDecimal qty;

            /** 触发后限价单价格(action_type=2时使用；市价为空) */
        @TableField("order_price")
        @Excel(name = "触发后限价单价格(action_type=2时使用；市价为空)")
    private BigDecimal orderPrice;

            /** 状态：0待触发；1已触发；2已取消；3已过期；4触发失败 */
        @TableField("status")
        @Excel(name = "状态：0待触发；1已触发；2已取消；3已过期；4触发失败")
    private Long status;

            /** 失败原因(触发失败时记录：余额不足/仓位不足/风控拒绝等) */
        @TableField("fail_reason")
        @Excel(name = "失败原因(触发失败时记录：余额不足/仓位不足/风控拒绝等)")
    private String failReason;

            /** 触发所用价格快照ID(u_price_tick.id) */
        @TableField("triggered_tick_id")
        @Excel(name = "触发所用价格快照ID(u_price_tick.id)")
    private Long triggeredTickId;

            /** 触发时间 */
        @TableField("triggered_time")
        @Excel(name = "触发时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date triggeredTime;

            /** 触发后生成的订单ID(u_order.id) */
        @TableField("generated_order_id")
        @Excel(name = "触发后生成的订单ID(u_order.id)")
    private Long generatedOrderId;

            /** 过期时间(可选，空表示不过期) */
        @TableField("expire_time")
        @Excel(name = "过期时间(可选，空表示不过期)")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expireTime;

            /** 创建时间 */
        @TableField("create_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

            /** 更新时间 */
        @TableField("update_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

            /** 删除标志：0正常；2删除 */
        @TableField("del_flag")
    private String delFlag;

        }
