package com.ruoyi.app.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 资金费率结算明细 VO（用于接口入参/出参）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FundingSettlementVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

        /** 主键ID */
    private Long id;

        /** 用户UID */
    private Long userId;

        /** 合约ID */
    private Long contractId;

        /** 持仓方向：2LONG；3SHORT */
    private Long positionSide;

        /** 资金费率结算时间点 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fundingTime;

        /** 结算费率 */
    private BigDecimal fundingRate;

        /** 结算时仓位数量(用于审计) */
    private BigDecimal positionQty;

        /** 结算金额：正=收取；负=支付 */
    private BigDecimal amount;

        /** 结算币种(一般USDT) */
    private String asset;

        /** 对应总账流水ID(u_ledger.id)，便于对账 */
    private Long refLedgerId;

        /** 创建时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
