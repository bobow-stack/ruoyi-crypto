package com.ruoyi.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.app.domain.Trade;
import com.ruoyi.app.mapper.TradeMapper;
import com.ruoyi.app.service.ITradeService;
import org.springframework.stereotype.Service;

/**
 * 成交明细(平台对手方模式，审计靠价格快照) ServiceImpl（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Service
public class TradeServiceImpl extends ServiceImpl<TradeMapper, Trade> implements ITradeService {
}
