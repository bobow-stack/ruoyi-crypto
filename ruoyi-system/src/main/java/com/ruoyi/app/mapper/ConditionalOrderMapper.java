package com.ruoyi.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.app.domain.ConditionalOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * 条件单(止盈止损/条件触发) Mapper 接口（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Mapper
public interface ConditionalOrderMapper extends BaseMapper<ConditionalOrder> {
}
