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
 * 持仓止盈止损设置(更贴近App操作)对象 u_position_tp_sl
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@TableName("u_position_tp_sl")
public class PositionTpSl
        {
        private static final long serialVersionUID = 1L;

            /** 主键ID */
            @TableId(value = "id", type = IdType.INPUT)
    private Long id;

            /** 用户UID */
        @TableField("user_id")
        @Excel(name = "用户UID")
    private Long userId;

            /** 持仓ID(u_position.id) */
        @TableField("position_id")
        @Excel(name = "持仓ID(u_position.id)")
    private Long positionId;

            /** 合约ID */
        @TableField("contract_id")
        @Excel(name = "合约ID")
    private Long contractId;

            /** 持仓方向：2LONG；3SHORT */
        @TableField("position_side")
        @Excel(name = "持仓方向：2LONG；3SHORT")
    private Long positionSide;

            /** 止盈启用：0否；1是 */
        @TableField("tp_enabled")
        @Excel(name = "止盈启用：0否；1是")
    private Long tpEnabled;

            /** 止盈触发类型：1最新价；2标记价；3指数价 */
        @TableField("tp_trigger_type")
        @Excel(name = "止盈触发类型：1最新价；2标记价；3指数价")
    private Long tpTriggerType;

            /** 止盈触发条件：1>=；2<= */
        @TableField("tp_condition")
        @Excel(name = "止盈触发条件：1>=；2<=")
    private Long tpCondition;

            /** 止盈触发价 */
        @TableField("tp_price")
        @Excel(name = "止盈触发价")
    private BigDecimal tpPrice;

            /** 止盈数量(空或0表示全平；否则部分平仓) */
        @TableField("tp_qty")
        @Excel(name = "止盈数量(空或0表示全平；否则部分平仓)")
    private BigDecimal tpQty;

            /** 止损启用：0否；1是 */
        @TableField("sl_enabled")
        @Excel(name = "止损启用：0否；1是")
    private Long slEnabled;

            /** 止损触发类型：1最新价；2标记价；3指数价 */
        @TableField("sl_trigger_type")
        @Excel(name = "止损触发类型：1最新价；2标记价；3指数价")
    private Long slTriggerType;

            /** 止损触发条件：1>=；2<= */
        @TableField("sl_condition")
        @Excel(name = "止损触发条件：1>=；2<=")
    private Long slCondition;

            /** 止损触发价 */
        @TableField("sl_price")
        @Excel(name = "止损触发价")
    private BigDecimal slPrice;

            /** 止损数量(空或0表示全平；否则部分平仓) */
        @TableField("sl_qty")
        @Excel(name = "止损数量(空或0表示全平；否则部分平仓)")
    private BigDecimal slQty;

            /** 最近一次生成的止盈条件单ID(u_conditional_order.id) */
        @TableField("last_tp_conditional_id")
        @Excel(name = "最近一次生成的止盈条件单ID(u_conditional_order.id)")
    private Long lastTpConditionalId;

            /** 最近一次生成的止损条件单ID(u_conditional_order.id) */
        @TableField("last_sl_conditional_id")
        @Excel(name = "最近一次生成的止损条件单ID(u_conditional_order.id)")
    private Long lastSlConditionalId;

            /** 创建时间 */
        @TableField("create_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

            /** 更新时间 */
        @TableField("update_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

        }
