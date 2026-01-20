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
 * 登录认证(账号与密码)对象 u_user_auth
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@TableName("u_user_auth")
public class UserAuth
        {
        private static final long serialVersionUID = 1L;

            /** 主键ID */
            @TableId(value = "id", type = IdType.INPUT)
    private Long id;

            /** 用户UID */
        @TableField("user_id")
        @Excel(name = "用户UID")
    private Long userId;

            /** 登录类型：1用户名；2邮箱；3手机号 */
        @TableField("login_type")
        @Excel(name = "登录类型：1用户名；2邮箱；3手机号")
    private Long loginType;

            /** 登录标识(用户名/邮箱/手机号，按login_type解释) */
        @TableField("identifier")
        @Excel(name = "登录标识(用户名/邮箱/手机号，按login_type解释)")
    private String identifier;

            /** 密码Hash(建议bcrypt/argon2存储结果) */
        @TableField("password_hash")
        @Excel(name = "密码Hash(建议bcrypt/argon2存储结果)")
    private String passwordHash;

            /** 密码盐(如算法需要；bcrypt一般不需要单独存) */
        @TableField("password_salt")
        @Excel(name = "密码盐(如算法需要；bcrypt一般不需要单独存)")
    private String passwordSalt;

            /** 密码版本号(改密后+1，用于强制失效旧token) */
        @TableField("pwd_version")
        @Excel(name = "密码版本号(改密后+1，用于强制失效旧token)")
    private Long pwdVersion;

            /** 连续登录失败次数(用于风控锁定) */
        @TableField("fail_count")
        @Excel(name = "连续登录失败次数(用于风控锁定)")
    private Long failCount;

            /** 锁定截止时间(未锁定为NULL) */
        @TableField("lock_until")
        @Excel(name = "锁定截止时间(未锁定为NULL)")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lockUntil;

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
