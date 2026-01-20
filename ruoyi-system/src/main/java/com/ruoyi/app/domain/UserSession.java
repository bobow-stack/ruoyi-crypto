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
 * 用户会话(登录态/设备)对象 u_user_session
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@TableName("u_user_session")
public class UserSession
        {
        private static final long serialVersionUID = 1L;

            /** 会话ID */
            @TableId(value = "id", type = IdType.INPUT)
    private Long id;

            /** 用户UID */
        @TableField("user_id")
        @Excel(name = "用户UID")
    private Long userId;

            /** 设备ID/设备指纹(可选，用于设备管理) */
        @TableField("device_id")
        @Excel(name = "设备ID/设备指纹(可选，用于设备管理)")
    private String deviceId;

            /** 设备名称(可选) */
        @TableField("device_name")
        @Excel(name = "设备名称(可选)")
    private String deviceName;

            /** 平台：iOS/Android/Web/Other */
        @TableField("platform")
        @Excel(name = "平台：iOS/Android/Web/Other")
    private String platform;

            /** 访问Token(建议存Hash，不建议存明文token) */
        @TableField("access_token")
        @Excel(name = "访问Token(建议存Hash，不建议存明文token)")
    private String accessToken;

            /** 刷新Token(建议存Hash) */
        @TableField("refresh_token")
        @Excel(name = "刷新Token(建议存Hash)")
    private String refreshToken;

            /** 会话过期时间(服务端判定) */
        @TableField("expired_time")
        @Excel(name = "会话过期时间(服务端判定)")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expiredTime;

            /** 登录IP */
        @TableField("ip")
        @Excel(name = "登录IP")
    private String ip;

            /** UA/设备信息 */
        @TableField("user_agent")
        @Excel(name = "UA/设备信息")
    private String userAgent;

            /** 会话状态：0有效；1失效(踢下线/改密等) */
        @TableField("status")
        @Excel(name = "会话状态：0有效；1失效(踢下线/改密等)")
    private Long status;

            /** 创建时间(登录时间) */
        @TableField("create_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

            /** 更新时间 */
        @TableField("update_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

        }
