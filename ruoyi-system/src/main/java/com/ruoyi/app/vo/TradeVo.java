package com.ruoyi.app.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 成交明细(平台对手方模式，审计靠价格快照) VO（用于接口入参/出参）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TradeVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

        /** 成交ID(主键) */
    private Long id;

        /** 订单ID(u_order.id) */
    private Long orderId;

        /** 用户UID */
    private Long userId;

        /** 合约ID */
    private Long contractId;

        /** 成交原因：1用户市价；2用户限价触发；3止盈触发；4止损触发；5强平成交；6管理员成交(预留) */
    private Long execReason;

        /** 成交价(基于币安价格源规则得出) */
    private BigDecimal execPrice;

        /** 成交数量 */
    private BigDecimal qty;

        /** 成交名义价值(可选：exec_price*qty*contract_size) */
    private BigDecimal turnover;

        /** 手续费币种 */
    private String feeAsset;

        /** 手续费率快照 */
    private BigDecimal feeRate;

        /** 手续费金额(正数表示扣费) */
    private BigDecimal feeAmount;

        /** 成交使用的价格快照ID(u_price_tick.id)，用于审计 */
    private Long priceTickId;

        /** 成交时间(业务时间) */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ts;

        /** 入库时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
