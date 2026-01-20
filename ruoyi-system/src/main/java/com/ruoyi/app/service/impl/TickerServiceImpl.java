package com.ruoyi.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.app.domain.Ticker;
import com.ruoyi.app.mapper.TickerMapper;
import com.ruoyi.app.service.ITickerService;
import org.springframework.stereotype.Service;

/**
 * Ticker最新快照(可替代Redis快照；也可只存Redis) ServiceImpl（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Service
public class TickerServiceImpl extends ServiceImpl<TickerMapper, Ticker> implements ITickerService {
}
