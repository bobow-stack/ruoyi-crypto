package com.ruoyi.app.service;

import com.ruoyi.app.security.AppTokenService;
import com.ruoyi.app.security.model.AppLoginUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.system.domain.User;
import com.ruoyi.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * APP 登录校验
 */
@Component
public class AppLoginService
{
    @Autowired
    private IUserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AppTokenService tokenService;

    public void register(String account, String password, String nickName)
    {
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(password))
        {
            throw new ServiceException("账号或密码不能为空");
        }
        User exist = userService.lambdaQuery()
                .eq(User::getPhone, account)
                .or()
                .eq(User::getUsername, account)
                .last("limit 1")
                .one();
        if (exist != null)
        {
            throw new ServiceException("账号已存在");
        }

        User user = new User();
        user.setUsername(account);
        user.setPhone(account);
        user.setNickName(StringUtils.isNotBlank(nickName) ? nickName : account);
        user.setPassword(passwordEncoder.encode(password));
        user.setStatus("0");
        user.setRegisterType("password");
        user.setCreateTime(DateUtils.getNowDate());
        userService.save(user);
    }

    public String login(String account, String password)
    {
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(password))
        {
            throw new ServiceException("账号或密码不能为空");
        }

        User user = userService.lambdaQuery()
                .eq(User::getPhone, account)
                .or()
                .eq(User::getUsername, account)
                .last("limit 1")
                .one();
        if (user == null)
        {
            throw new ServiceException("账号或密码错误");
        }
        if ("1".equals(user.getStatus()))
        {
            throw new ServiceException("账号已停用");
        }
        if (!passwordEncoder.matches(password, user.getPassword()))
        {
            throw new ServiceException("账号或密码错误");
        }

        user.setLoginIp(IpUtils.getIpAddr());
        user.setLoginDate(DateUtils.getNowDate());
        userService.updateById(user);

        AppLoginUser loginUser = new AppLoginUser(user);
        return tokenService.createToken(loginUser);
    }
}
