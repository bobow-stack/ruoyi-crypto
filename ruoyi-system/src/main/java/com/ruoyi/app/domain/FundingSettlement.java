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
 * 资金费率结算明细对象 u_funding_settlement
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@TableName("u_funding_settlement")
public class FundingSettlement
        {
        private static final long serialVersionUID = 1L;

            /** 主键ID */
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

            /** 持仓方向：2LONG；3SHORT */
        @TableField("position_side")
        @Excel(name = "持仓方向：2LONG；3SHORT")
    private Long positionSide;

            /** 资金费率结算时间点 */
        @TableField("funding_time")
        @Excel(name = "资金费率结算时间点")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fundingTime;

            /** 结算费率 */
        @TableField("funding_rate")
        @Excel(name = "结算费率")
    private BigDecimal fundingRate;

            /** 结算时仓位数量(用于审计) */
        @TableField("position_qty")
        @Excel(name = "结算时仓位数量(用于审计)")
    private BigDecimal positionQty;

            /** 结算金额：正=收取；负=支付 */
        @TableField("amount")
        @Excel(name = "结算金额：正=收取；负=支付")
    private BigDecimal amount;

            /** 结算币种(一般USDT) */
        @TableField("asset")
        @Excel(name = "结算币种(一般USDT)")
    private String asset;

            /** 对应总账流水ID(u_ledger.id)，便于对账 */
        @TableField("ref_ledger_id")
        @Excel(name = "对应总账流水ID(u_ledger.id)，便于对账")
    private Long refLedgerId;

            /** 创建时间 */
        @TableField("create_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

        }
