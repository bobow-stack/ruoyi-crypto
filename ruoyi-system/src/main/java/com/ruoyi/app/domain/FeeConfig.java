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
 * 手续费配置(支持全局/合约/VIP维度覆盖)对象 u_fee_config
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@TableName("u_fee_config")
public class FeeConfig
        {
        private static final long serialVersionUID = 1L;

            /** 主键ID */
            @TableId(value = "id", type = IdType.INPUT)
    private Long id;

            /** 作用范围：1全局；2合约；3VIP等级；4合约+VIP */
        @TableField("scope_type")
        @Excel(name = "作用范围：1全局；2合约；3VIP等级；4合约+VIP")
    private Long scopeType;

            /** 合约ID(scope包含合约时填写) */
        @TableField("contract_id")
        @Excel(name = "合约ID(scope包含合约时填写)")
    private Long contractId;

            /** VIP等级(scope包含VIP时填写) */
        @TableField("vip_level")
        @Excel(name = "VIP等级(scope包含VIP时填写)")
    private Long vipLevel;

            /** 手续费率(你模式可用统一费率；如需maker/taker可扩展) */
        @TableField("fee_rate")
        @Excel(name = "手续费率(你模式可用统一费率；如需maker/taker可扩展)")
    private BigDecimal feeRate;

            /** 状态：0启用；1停用 */
        @TableField("status")
        @Excel(name = "状态：0启用；1停用")
    private Long status;

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
