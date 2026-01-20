package com.ruoyi.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.app.domain.UserSession;
import com.ruoyi.app.mapper.UserSessionMapper;
import com.ruoyi.app.service.IUserSessionService;
import org.springframework.stereotype.Service;

/**
 * 用户会话(登录态/设备) ServiceImpl（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Service
public class UserSessionServiceImpl extends ServiceImpl<UserSessionMapper, UserSession> implements IUserSessionService {
}
