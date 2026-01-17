package com.ruoyi.system.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 移动端APP用户 VO（用于接口入参/出参）
 *
 * @author ruoyi
 * @date 2026-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

        /** 用户ID */
    private Long userId;

        /** 登录账号（可选，支持用户名登录） */
    private String username;

        /** 用户昵称 */
    private String nickName;

        /** 手机号码（主要登录方式，唯一） */
    private String phone;

        /** 用户邮箱（可选） */
    private String email;

        /** 密码（BCrypt加密） */
    private String password;

        /** 用户性别（0男 1女 2未知） */
    private String sex;

        /** 头像地址（OSS或本地路径） */
    private String avatar;

        /** 帐号状态（0正常 1停用） */
    private String status;

        /** 最后登录IP */
    private String loginIp;

        /** 最后登录时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginDate;

        /** 设备推送Token（如极光、个推） */
    private String deviceToken;

        /** 微信openid（第三方登录绑定） */
    private String openidWechat;

        /** 微信unionid（多端统一标识） */
    private String unionid;

        /** QQ openid */
    private String openidQq;

        /** 注册方式（phone/wechat/qq/password） */
    private String registerType;

        /** 创建时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

        /** 更新时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

        /** 备注 */
    private String remark;

}
