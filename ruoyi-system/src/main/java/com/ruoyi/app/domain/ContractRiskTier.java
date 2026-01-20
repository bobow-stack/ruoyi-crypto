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
 * 合约风险限额/保证金档位对象 u_contract_risk_tier
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@TableName("u_contract_risk_tier")
public class ContractRiskTier
        {
        private static final long serialVersionUID = 1L;

            /** 主键ID */
            @TableId(value = "id", type = IdType.INPUT)
    private Long id;

            /** 合约ID */
        @TableField("contract_id")
        @Excel(name = "合约ID")
    private Long contractId;

            /** 风险档位序号(从1开始) */
        @TableField("tier")
        @Excel(name = "风险档位序号(从1开始)")
    private Long tier;

            /** 该档位最大名义价值上限(超过则需更高档位) */
        @TableField("max_notional")
        @Excel(name = "该档位最大名义价值上限(超过则需更高档位)")
    private BigDecimal maxNotional;

            /** 维持保证金率(MMR)，如0.005表示0.5% */
        @TableField("mmr")
        @Excel(name = "维持保证金率(MMR)，如0.005表示0.5%")
    private BigDecimal mmr;

            /** 初始保证金率(IMR，可选；不填则用1/leverage推导) */
        @TableField("imr")
        @Excel(name = "初始保证金率(IMR，可选；不填则用1/leverage推导)")
    private BigDecimal imr;

            /** 该档位最大杠杆 */
        @TableField("max_leverage")
        @Excel(name = "该档位最大杠杆")
    private Long maxLeverage;

            /** 删除标志：0正常；2删除 */
        @TableField("del_flag")
    private String delFlag;

            /** 创建时间 */
        @TableField("create_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

            /** 更新时间 */
        @TableField("update_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

        }
