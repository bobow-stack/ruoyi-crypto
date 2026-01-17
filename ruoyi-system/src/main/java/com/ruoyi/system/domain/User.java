package com.ruoyi.system.domain;

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
 * 移动端APP用户对象 u_user
 *
 * @author ruoyi
 * @date 2026-01-07
 */
@Data
@TableName("u_user")
public class User
        {
        private static final long serialVersionUID = 1L;

            /** 用户ID */
            @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

            /** 登录账号（可选，支持用户名登录） */
        @TableField("username")
        @Excel(name = "登录账号（可选，支持用户名登录）")
    private String username;

            /** 用户昵称 */
        @TableField("nick_name")
        @Excel(name = "用户昵称")
    private String nickName;

            /** 手机号码（主要登录方式，唯一） */
        @TableField("phone")
        @Excel(name = "手机号码（主要登录方式，唯一）")
    private String phone;

            /** 用户邮箱（可选） */
        @TableField("email")
        @Excel(name = "用户邮箱（可选）")
    private String email;

            /** 密码（BCrypt加密） */
        @TableField("password")
        @Excel(name = "密码（BCrypt加密）")
    private String password;

            /** 用户性别（0男 1女 2未知） */
        @TableField("sex")
        @Excel(name = "用户性别（0男 1女 2未知）")
    private String sex;

            /** 头像地址（OSS或本地路径） */
        @TableField("avatar")
        @Excel(name = "头像地址（OSS或本地路径）")
    private String avatar;

            /** 帐号状态（0正常 1停用） */
        @TableField("status")
        @Excel(name = "帐号状态（0正常 1停用）")
    private String status;

            /** 最后登录IP */
        @TableField("login_ip")
        @Excel(name = "最后登录IP")
    private String loginIp;

            /** 最后登录时间 */
        @TableField("login_date")
        @Excel(name = "最后登录时间")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginDate;

            /** 设备推送Token（如极光、个推） */
        @TableField("device_token")
        @Excel(name = "设备推送Token（如极光、个推）")
    private String deviceToken;

            /** 微信openid（第三方登录绑定） */
        @TableField("openid_wechat")
        @Excel(name = "微信openid（第三方登录绑定）")
    private String openidWechat;

            /** 微信unionid（多端统一标识） */
        @TableField("unionid")
        @Excel(name = "微信unionid（多端统一标识）")
    private String unionid;

            /** QQ openid */
        @TableField("openid_qq")
        @Excel(name = "QQ openid")
    private String openidQq;

            /** 注册方式（phone/wechat/qq/password） */
        @TableField("register_type")
        @Excel(name = "注册方式（phone/wechat/qq/password）")
    private String registerType;

            /** 创建时间 */
        @TableField("create_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

            /** 更新时间 */
        @TableField("update_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

            /** 备注 */
        @TableField("remark")
        @Excel(name = "备注")
    private String remark;

        }
