package com.ruoyi.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.app.domain.PriceTick;

/**
 * 价格快照(币安推送落库，用于触发成交/强平审计；大建议分区/分) Service（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
public interface IPriceTickService extends IService<PriceTick> {
}
