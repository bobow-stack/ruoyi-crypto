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
 * 用户2FA配置对象 u_user_2fa
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@TableName("u_user_2fa")
public class User2fa
        {
        private static final long serialVersionUID = 1L;

            /** 主键ID */
            @TableId(value = "id", type = IdType.INPUT)
    private Long id;

            /** 用户UID */
        @TableField("user_id")
        @Excel(name = "用户UID")
    private Long userId;

            /** 2FA类型：1TOTP(Google Authenticator) */
        @TableField("type")
        @Excel(name = "2FA类型：1TOTP(Google Authenticator)")
    private Long type;

            /** TOTP密钥(加密后存储，不可明文) */
        @TableField("secret_encrypted")
        @Excel(name = "TOTP密钥(加密后存储，不可明文)")
    private String secretEncrypted;

            /** 是否启用：0未启用；1启用 */
        @TableField("enabled")
        @Excel(name = "是否启用：0未启用；1启用")
    private Long enabled;

            /** 启用时间(启用后写入) */
        @TableField("enabled_time")
        @Excel(name = "启用时间(启用后写入)")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date enabledTime;

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
