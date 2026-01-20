package com.ruoyi.app.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户会话(登录态/设备) VO（用于接口入参/出参）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserSessionVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

        /** 会话ID */
    private Long id;

        /** 用户UID */
    private Long userId;

        /** 设备ID/设备指纹(可选，用于设备管理) */
    private String deviceId;

        /** 设备名称(可选) */
    private String deviceName;

        /** 平台：iOS/Android/Web/Other */
    private String platform;

        /** 访问Token(建议存Hash，不建议存明文token) */
    private String accessToken;

        /** 刷新Token(建议存Hash) */
    private String refreshToken;

        /** 会话过期时间(服务端判定) */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expiredTime;

        /** 登录IP */
    private String ip;

        /** UA/设备信息 */
    private String userAgent;

        /** 会话状态：0有效；1失效(踢下线/改密等) */
    private Long status;

        /** 创建时间(登录时间) */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

        /** 更新时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
