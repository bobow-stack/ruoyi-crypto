package com.ruoyi.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.app.domain.UserMessage;
import com.ruoyi.app.mapper.UserMessageMapper;
import com.ruoyi.app.service.IUserMessageService;
import org.springframework.stereotype.Service;

/**
 * 站内信/通知 ServiceImpl（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Service
public class UserMessageServiceImpl extends ServiceImpl<UserMessageMapper, UserMessage> implements IUserMessageService {
}
