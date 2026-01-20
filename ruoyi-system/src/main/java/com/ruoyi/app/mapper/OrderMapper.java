package com.ruoyi.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.app.domain.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单(适配触发式成交/止盈止损/强平) Mapper 接口（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
