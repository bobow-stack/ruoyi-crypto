package com.ruoyi.app.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户2FA配置 VO（用于接口入参/出参）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User2faVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

        /** 主键ID */
    private Long id;

        /** 用户UID */
    private Long userId;

        /** 2FA类型：1TOTP(Google Authenticator) */
    private Long type;

        /** TOTP密钥(加密后存储，不可明文) */
    private String secretEncrypted;

        /** 是否启用：0未启用；1启用 */
    private Long enabled;

        /** 启用时间(启用后写入) */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date enabledTime;

        /** 删除标志：0正常；2删除 */
    private String delFlag;

        /** 创建时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

        /** 更新时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
