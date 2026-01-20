package com.ruoyi.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.app.domain.UserAuth;
import com.ruoyi.app.mapper.UserAuthMapper;
import com.ruoyi.app.service.IUserAuthService;
import org.springframework.stereotype.Service;

/**
 * 登录认证(账号与密码) ServiceImpl（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements IUserAuthService {
}
