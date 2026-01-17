package com.ruoyi.app.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * APP 端注册请求体
 */
@ApiModel("APP注册请求")
public class AppRegisterBody
{
    /**
     * 登录账号（手机号或用户名）
     */
    @ApiModelProperty(value = "账号（手机号或用户名）", required = true)
    private String account;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    /**
     * 昵称（可选）
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }
}
