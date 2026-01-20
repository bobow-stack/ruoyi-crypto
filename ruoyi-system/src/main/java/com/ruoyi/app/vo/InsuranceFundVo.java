package com.ruoyi.app.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 保险基金流水 VO（用于接口入参/出参）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class InsuranceFundVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

        /** 主键ID */
    private Long id;

        /** 资产币种(一般USDT) */
    private String asset;

        /** 变动金额：正=入；负=出 */
    private BigDecimal changeAmount;

        /** 变动后余额快照(用于快速对账) */
    private BigDecimal balanceAfter;

        /** 业务类型：LIQUIDATION_PROFIT/BAD_DEBT/TRANSFER等 */
    private String bizType;

        /** 关联业务ID(如强平ID/流水ID等，可选) */
    private Long refId;

        /** 业务发生时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ts;

        /** 创建时间(入库时间) */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
