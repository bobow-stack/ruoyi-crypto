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
 * 账户(按账户类型分合约/现货)对象 u_account
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@TableName("u_account")
public class Account
        {
        private static final long serialVersionUID = 1L;

            /** 账户ID */
            @TableId(value = "id", type = IdType.INPUT)
    private Long id;

            /** 用户UID */
        @TableField("user_id")
        @Excel(name = "用户UID")
    private Long userId;

            /** 账户类型：1合约；2现货(预留) */
        @TableField("account_type")
        @Excel(name = "账户类型：1合约；2现货(预留)")
    private Long accountType;

            /** 账户状态：0正常；1冻结/禁用 */
        @TableField("status")
        @Excel(name = "账户状态：0正常；1冻结/禁用")
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
