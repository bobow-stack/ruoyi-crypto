package com.ruoyi.app.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 资金费率历史/结算点 VO（用于接口入参/出参）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FundingRateVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

        /** 主键ID */
    private Long id;

        /** 合约ID */
    private Long contractId;

        /** 资金费率(如0.0001=0.01%) */
    private BigDecimal fundingRate;

        /** 资金费率结算时间点 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fundingTime;

        /** 预测费率(可选，用于展示下次费率) */
    private BigDecimal predictedRate;

        /** 创建时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
