package com.ruoyi.app.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 登录认证(账号与密码) VO（用于接口入参/出参）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserAuthVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

        /** 主键ID */
    private Long id;

        /** 用户UID */
    private Long userId;

        /** 登录类型：1用户名；2邮箱；3手机号 */
    private Long loginType;

        /** 登录标识(用户名/邮箱/手机号，按login_type解释) */
    private String identifier;

        /** 密码Hash(建议bcrypt/argon2存储结果) */
    private String passwordHash;

        /** 密码盐(如算法需要；bcrypt一般不需要单独存) */
    private String passwordSalt;

        /** 密码版本号(改密后+1，用于强制失效旧token) */
    private Long pwdVersion;

        /** 连续登录失败次数(用于风控锁定) */
    private Long failCount;

        /** 锁定截止时间(未锁定为NULL) */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lockUntil;

        /** 删除标志：0正常；2删除 */
    private String delFlag;

        /** 创建时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

        /** 更新时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
