package com.ruoyi.app.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 合约风险限额/保证金档位 VO（用于接口入参/出参）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ContractRiskTierVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

        /** 主键ID */
    private Long id;

        /** 合约ID */
    private Long contractId;

        /** 风险档位序号(从1开始) */
    private Long tier;

        /** 该档位最大名义价值上限(超过则需更高档位) */
    private BigDecimal maxNotional;

        /** 维持保证金率(MMR)，如0.005表示0.5% */
    private BigDecimal mmr;

        /** 初始保证金率(IMR，可选；不填则用1/leverage推导) */
    private BigDecimal imr;

        /** 该档位最大杠杆 */
    private Long maxLeverage;

        /** 删除标志：0正常；2删除 */
    private String delFlag;

        /** 创建时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

        /** 更新时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
