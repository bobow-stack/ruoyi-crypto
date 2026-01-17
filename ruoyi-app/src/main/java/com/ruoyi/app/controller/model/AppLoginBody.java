package com.ruoyi.app.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * APP 端登录请求体
 */
@ApiModel("APP登录请求")
public class AppLoginBody
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
}
