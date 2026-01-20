package com.ruoyi.app.service;

import com.ruoyi.app.domain.User;
import com.ruoyi.app.domain.UserAuth;
import com.ruoyi.app.security.AppTokenService;
import com.ruoyi.app.security.model.AppLoginUser;
import com.ruoyi.app.service.IUserAuthService;
import com.ruoyi.app.service.IUserService;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * APP 登录校验
 */
@Component
public class AppLoginService
{
    private static final long LOGIN_TYPE_USERNAME = 1L;
    private static final long LOGIN_TYPE_EMAIL = 2L;
    private static final long LOGIN_TYPE_PHONE = 3L;

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserAuthService userAuthService;

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
        long loginType = parseLoginType(account);
        UserAuth existAuth = userAuthService.lambdaQuery()
                .eq(UserAuth::getLoginType, loginType)
                .eq(UserAuth::getIdentifier, account)
                .ne(UserAuth::getDelFlag, "1")
                .last("limit 1")
                .one();
        if (existAuth != null)
        {
            throw new ServiceException("账号已存在");
        }

        User user = new User();
        user.setId(generateId());
        if (LOGIN_TYPE_EMAIL == loginType)
        {
            user.setEmail(account);
        }
        else if (LOGIN_TYPE_PHONE == loginType)
        {
            user.setPhone(account);
        }
        else
        {
            user.setUsername(account);
        }
        user.setNickname(StringUtils.isNotBlank(nickName) ? nickName : account);
        user.setStatus(0L);
        user.setDelFlag("0");
        user.setCreateTime(DateUtils.getNowDate());
        userService.save(user);

        UserAuth auth = new UserAuth();
        auth.setId(generateId());
        auth.setUserId(user.getId());
        auth.setLoginType(loginType);
        auth.setIdentifier(account);
        auth.setPasswordHash(passwordEncoder.encode(password));
        auth.setPwdVersion(1L);
        auth.setFailCount(0L);
        auth.setDelFlag("0");
        auth.setCreateTime(DateUtils.getNowDate());
        userAuthService.save(auth);
    }

    public String login(String account, String password)
    {
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(password))
        {
            throw new ServiceException("账号或密码不能为空");
        }

        long loginType = parseLoginType(account);
        UserAuth auth = userAuthService.lambdaQuery()
                .eq(UserAuth::getLoginType, loginType)
                .eq(UserAuth::getIdentifier, account)
                .ne(UserAuth::getDelFlag, "1")
                .last("limit 1")
                .one();
        if (auth == null)
        {
            throw new ServiceException("账号或密码错误");
        }
        if (auth.getLockUntil() != null && auth.getLockUntil().after(new Date()))
        {
            throw new ServiceException("账号已被锁定");
        }
        User user = userService.getById(auth.getUserId());
        if (user == null || "1".equals(user.getDelFlag()))
        {
            throw new ServiceException("账号或密码错误");
        }
        if (user.getStatus() != null && user.getStatus() != 0L)
        {
            throw new ServiceException("账号已停用");
        }
        if (!passwordEncoder.matches(password, auth.getPasswordHash()))
        {
            throw new ServiceException("账号或密码错误");
        }

        user.setLastLoginIp(IpUtils.getIpAddr());
        user.setLastLoginTime(DateUtils.getNowDate());
        userService.updateById(user);

        AppLoginUser loginUser = new AppLoginUser(user);
        return tokenService.createToken(loginUser);
    }

    private long parseLoginType(String account)
    {
        if (account.contains("@"))
        {
            return LOGIN_TYPE_EMAIL;
        }
        if (account.matches("^\\d{6,20}$"))
        {
            return LOGIN_TYPE_PHONE;
        }
        return LOGIN_TYPE_USERNAME;
    }

    private long generateId()
    {
        long now = System.currentTimeMillis();
        int rand = ThreadLocalRandom.current().nextInt(1000);
        return now * 1000 + rand;
    }
}
