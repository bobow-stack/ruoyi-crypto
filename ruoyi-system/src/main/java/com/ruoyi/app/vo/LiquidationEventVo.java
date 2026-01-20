package com.ruoyi.app.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 强平事件(以价格推送触发，字段更完整可审计) VO（用于接口入参/出参）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LiquidationEventVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

        /** 强平事件ID(主键) */
    private Long id;

        /** 用户UID */
    private Long userId;

        /** 合约ID */
    private Long contractId;

        /** 持仓ID(u_position.id) */
    private Long positionId;

        /** 方向：2LONG；3SHORT */
    private Long positionSide;

        /** 保证金模式：1全仓；2逐仓 */
    private Long marginMode;

        /** 触发时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date triggerTime;

        /** 触发价格类型：1最新价；2标记价(推荐)；3指数价 */
    private Long triggerType;

        /** 触发价格(当时用于判断强平的价格) */
    private BigDecimal triggerPrice;

        /** 触发价格快照ID(u_price_tick.id) */
    private Long triggerTickId;

        /** 强平前账户权益(可选，用于审计) */
    private BigDecimal equityBefore;

        /** 触发时保证金率(可选，便于分析) */
    private BigDecimal marginRatio;

        /** 触发时维持保证金率(MMR快照) */
    private BigDecimal mmr;

        /** 系统计算的强平价(触发时计算结果) */
    private BigDecimal liqPriceCalc;

        /** 强平数量 */
    private BigDecimal liqQty;

        /** 强平执行均价(完成后回填) */
    private BigDecimal execPriceAvg;

        /** 状态：0进行中；1完成；2失败 */
    private Long status;

        /** 系统生成的平仓订单ID(u_order.id) */
    private Long systemOrderId;

        /** 系统平仓成交ID(u_trade.id，可选仅记录首笔) */
    private Long systemTradeId;

        /** 坏账/穿仓金额(若出现，正数表示亏空) */
    private BigDecimal badDebt;

        /** 保险基金覆盖金额(如有) */
    private BigDecimal insuranceCover;

        /** 创建时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

        /** 更新时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
