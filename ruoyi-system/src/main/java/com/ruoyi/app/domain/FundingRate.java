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
 * 资金费率历史/结算点对象 u_funding_rate
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@TableName("u_funding_rate")
public class FundingRate
        {
        private static final long serialVersionUID = 1L;

            /** 主键ID */
            @TableId(value = "id", type = IdType.INPUT)
    private Long id;

            /** 合约ID */
        @TableField("contract_id")
        @Excel(name = "合约ID")
    private Long contractId;

            /** 资金费率(如0.0001=0.01%) */
        @TableField("funding_rate")
        @Excel(name = "资金费率(如0.0001=0.01%)")
    private BigDecimal fundingRate;

            /** 资金费率结算时间点 */
        @TableField("funding_time")
        @Excel(name = "资金费率结算时间点")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fundingTime;

            /** 预测费率(可选，用于展示下次费率) */
        @TableField("predicted_rate")
        @Excel(name = "预测费率(可选，用于展示下次费率)")
    private BigDecimal predictedRate;

            /** 创建时间 */
        @TableField("create_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

        }
