package com.ruoyi.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.app.domain.LiquidationEvent;
import org.apache.ibatis.annotations.Mapper;

/**
 * 强平事件(以价格推送触发，字段更完整可审计) Mapper 接口（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Mapper
public interface LiquidationEventMapper extends BaseMapper<LiquidationEvent> {
}
