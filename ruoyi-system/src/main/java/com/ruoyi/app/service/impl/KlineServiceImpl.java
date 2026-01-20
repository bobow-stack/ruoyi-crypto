package com.ruoyi.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.app.domain.Kline;
import com.ruoyi.app.mapper.KlineMapper;
import com.ruoyi.app.service.IKlineService;
import org.springframework.stereotype.Service;

/**
 * K线数据(大，建议分区/分) ServiceImpl（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Service
public class KlineServiceImpl extends ServiceImpl<KlineMapper, Kline> implements IKlineService {
}
