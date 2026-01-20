package com.ruoyi.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.app.domain.ConditionalOrder;
import com.ruoyi.app.mapper.ConditionalOrderMapper;
import com.ruoyi.app.service.IConditionalOrderService;
import org.springframework.stereotype.Service;

/**
 * 条件单(止盈止损/条件触发) ServiceImpl（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Service
public class ConditionalOrderServiceImpl extends ServiceImpl<ConditionalOrderMapper, ConditionalOrder> implements IConditionalOrderService {
}
