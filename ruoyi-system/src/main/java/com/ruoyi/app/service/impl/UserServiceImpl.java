package com.ruoyi.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.app.domain.User;
import com.ruoyi.app.mapper.UserMapper;
import com.ruoyi.app.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * 交易所用户 ServiceImpl（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
