package com.ruoyi.app.security.model;

import com.alibaba.fastjson2.annotation.JSONField;

import java.io.Serializable;
import java.util.Collection;

import com.ruoyi.app.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * APP 端登录用户信息
 */
public class AppLoginUser implements UserDetails, Serializable
{
    private static final long serialVersionUID = 1L;

    private Long userId;

    private String token;

    private Long loginTime;

    private Long expireTime;

    private String ipaddr;

    private String loginLocation;

    private String browser;

    private String os;

    private User user;

    public AppLoginUser()
    {
    }

    public AppLoginUser(User user)
    {
        this.user = user;
        this.userId = user != null ? user.getId() : null;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public Long getLoginTime()
    {
        return loginTime;
    }

    public void setLoginTime(Long loginTime)
    {
        this.loginTime = loginTime;
    }

    public Long getExpireTime()
    {
        return expireTime;
    }

    public void setExpireTime(Long expireTime)
    {
        this.expireTime = expireTime;
    }

    public String getIpaddr()
    {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr)
    {
        this.ipaddr = ipaddr;
    }

    public String getLoginLocation()
    {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation)
    {
        this.loginLocation = loginLocation;
    }

    public String getBrowser()
    {
        return browser;
    }

    public void setBrowser(String browser)
    {
        this.browser = browser;
    }

    public String getOs()
    {
        return os;
    }

    public void setOs(String os)
    {
        this.os = os;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
        this.userId = user != null ? user.getId() : null;
    }

    @JSONField(serialize = false)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return null;
    }

    @JSONField(serialize = false)
    @Override
    public String getPassword()
    {
        return null;
    }

    @Override
    public String getUsername()
    {
        if (user == null)
        {
            return null;
        }
        if (user.getUsername() != null)
        {
            return user.getUsername();
        }
        if (user.getEmail() != null)
        {
            return user.getEmail();
        }
        return user.getPhone();
    }

    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
