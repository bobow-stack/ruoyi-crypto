package com.ruoyi.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.app.domain.PriceTick;
import com.ruoyi.app.mapper.PriceTickMapper;
import com.ruoyi.app.service.IPriceTickService;
import org.springframework.stereotype.Service;

/**
 * 价格快照(币安推送落库，用于触发成交/强平审计；大建议分区/分) ServiceImpl（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Service
public class PriceTickServiceImpl extends ServiceImpl<PriceTickMapper, PriceTick> implements IPriceTickService {
}
