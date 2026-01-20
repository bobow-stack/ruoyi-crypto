package com.ruoyi.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.app.domain.User2fa;
import com.ruoyi.app.mapper.User2faMapper;
import com.ruoyi.app.service.IUser2faService;
import org.springframework.stereotype.Service;

/**
 * 用户2FA配置 ServiceImpl（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Service
public class User2faServiceImpl extends ServiceImpl<User2faMapper, User2fa> implements IUser2faService {
}
