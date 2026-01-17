package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.system.domain.User;
import com.ruoyi.system.mapper.UserMapper;
import com.ruoyi.system.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * 移动端APP用户 ServiceImpl（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
