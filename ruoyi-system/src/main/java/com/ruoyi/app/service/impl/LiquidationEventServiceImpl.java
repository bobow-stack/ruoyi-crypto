package com.ruoyi.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.app.domain.LiquidationEvent;
import com.ruoyi.app.mapper.LiquidationEventMapper;
import com.ruoyi.app.service.ILiquidationEventService;
import org.springframework.stereotype.Service;

/**
 * 强平事件(以价格推送触发，字段更完整可审计) ServiceImpl（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Service
public class LiquidationEventServiceImpl extends ServiceImpl<LiquidationEventMapper, LiquidationEvent> implements ILiquidationEventService {
}
