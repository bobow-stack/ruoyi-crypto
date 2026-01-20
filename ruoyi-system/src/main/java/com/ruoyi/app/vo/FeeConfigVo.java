package com.ruoyi.app.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 手续费配置(支持全局/合约/VIP维度覆盖) VO（用于接口入参/出参）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FeeConfigVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

        /** 主键ID */
    private Long id;

        /** 作用范围：1全局；2合约；3VIP等级；4合约+VIP */
    private Long scopeType;

        /** 合约ID(scope包含合约时填写) */
    private Long contractId;

        /** VIP等级(scope包含VIP时填写) */
    private Long vipLevel;

        /** 手续费率(你模式可用统一费率；如需maker/taker可扩展) */
    private BigDecimal feeRate;

        /** 状态：0启用；1停用 */
    private Long status;

        /** 删除标志：0正常；2删除 */
    private String delFlag;

        /** 创建时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

        /** 更新时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
