package com.ruoyi.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.app.domain.Order;
import com.ruoyi.app.mapper.OrderMapper;
import com.ruoyi.app.service.IOrderService;
import org.springframework.stereotype.Service;

/**
 * 订单(适配触发式成交/止盈止损/强平) ServiceImpl（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
}
