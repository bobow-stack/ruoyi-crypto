package com.ruoyi.app.controller;

import com.ruoyi.app.controller.model.AppLoginBody;
import com.ruoyi.app.security.AppTokenService;
import com.ruoyi.app.security.model.AppLoginUser;
import com.ruoyi.app.service.AppLoginService;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.app.controller.model.AppRegisterBody;
import com.ruoyi.app.controller.model.AppLoginBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * APP 登录/登出
 */
@Api(tags = "APP认证")
@RestController
@RequestMapping("/app/auth")
public class AppAuthController
{
    @Autowired
    private AppLoginService loginService;

    @Autowired
    private AppTokenService tokenService;

    /**
     * 注册
     */
    @Anonymous
    @ApiOperation("APP注册")
    @PostMapping("/register")
    public AjaxResult register(@RequestBody AppRegisterBody registerBody)
    {
        loginService.register(registerBody.getAccount(), registerBody.getPassword(), registerBody.getNickName());
        return AjaxResult.success();
    }

    /**
     * 登录
     */
    @Anonymous
    @ApiOperation("APP登录")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody AppLoginBody loginBody)
    {
        String token = loginService.login(loginBody.getAccount(), loginBody.getPassword());
        AjaxResult ajax = AjaxResult.success();
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 登出
     */
    @ApiOperation("APP登出")
    @ApiImplicitParam(name = "App-Authorization", value = "APP token", required = true, paramType = "header")
    @PostMapping("/logout")
    public AjaxResult logout(HttpServletRequest request)
    {
        AppLoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            tokenService.delLoginUser(loginUser.getToken());
        }
        return AjaxResult.success();
    }
}
