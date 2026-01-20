package com.ruoyi.app.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 交易所用户 VO（用于接口入参/出参）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

        /** 用户ID(UID，建议雪花ID) */
    private Long id;

        /** 用户名(唯一，可用于登录；可选) */
    private String username;

        /** 用户昵称(App展示) */
    private String nickname;

        /** 头像URL */
    private String avatarUrl;

        /** 邮箱(唯一；可用于登录) */
    private String email;

        /** 手机号(唯一；可用于登录) */
    private String phone;

        /** 用户状态：0正常；1冻结/禁用 */
    private Long status;

        /** KYC等级(预留)：0未认证；1/2/3... */
    private Long kycLevel;

        /** VIP等级：0普通；1..n(影响费率/杠杆等) */
    private Long vipLevel;

        /** 邀请码(可选) */
    private String inviteCode;

        /** 邀请人UID(可选) */
    private Long referredBy;

        /** 最后登录时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;

        /** 最后登录IP */
    private String lastLoginIp;

        /** 删除标志：0正常；2删除(软删) */
    private String delFlag;

        /** 创建时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

        /** 更新时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

        /** 备注(运营/客服可见) */
    private String remark;

}
