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
 * 保险基金流水对象 u_insurance_fund
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@TableName("u_insurance_fund")
public class InsuranceFund
        {
        private static final long serialVersionUID = 1L;

            /** 主键ID */
            @TableId(value = "id", type = IdType.INPUT)
    private Long id;

            /** 资产币种(一般USDT) */
        @TableField("asset")
        @Excel(name = "资产币种(一般USDT)")
    private String asset;

            /** 变动金额：正=入；负=出 */
        @TableField("change_amount")
        @Excel(name = "变动金额：正=入；负=出")
    private BigDecimal changeAmount;

            /** 变动后余额快照(用于快速对账) */
        @TableField("balance_after")
        @Excel(name = "变动后余额快照(用于快速对账)")
    private BigDecimal balanceAfter;

            /** 业务类型：LIQUIDATION_PROFIT/BAD_DEBT/TRANSFER等 */
        @TableField("biz_type")
        @Excel(name = "业务类型：LIQUIDATION_PROFIT/BAD_DEBT/TRANSFER等")
    private String bizType;

            /** 关联业务ID(如强平ID/流水ID等，可选) */
        @TableField("ref_id")
        @Excel(name = "关联业务ID(如强平ID/流水ID等，可选)")
    private Long refId;

            /** 业务发生时间 */
        @TableField("ts")
        @Excel(name = "业务发生时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ts;

            /** 创建时间(入库时间) */
        @TableField("create_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

        }
