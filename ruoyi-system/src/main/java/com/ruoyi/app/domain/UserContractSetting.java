package com.ruoyi.app.domain;

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
 * 用户-合约维度设置(杠杆/模式/档位)对象 u_user_contract_setting
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@TableName("u_user_contract_setting")
public class UserContractSetting
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

            /** 保证金模式：1全仓；2逐仓 */
        @TableField("margin_mode")
        @Excel(name = "保证金模式：1全仓；2逐仓")
    private Long marginMode;

            /** 持仓模式：1单向；2对冲(Hedge) */
        @TableField("position_mode")
        @Excel(name = "持仓模式：1单向；2对冲(Hedge)")
    private Long positionMode;

            /** 当前杠杆倍数 */
        @TableField("leverage")
        @Excel(name = "当前杠杆倍数")
    private Long leverage;

            /** 当前风险档位(对应contract_risk_tier.tier) */
        @TableField("risk_tier")
        @Excel(name = "当前风险档位(对应contract_risk_tier.tier)")
    private Long riskTier;

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
