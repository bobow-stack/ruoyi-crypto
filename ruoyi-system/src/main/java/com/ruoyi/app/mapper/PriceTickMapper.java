package com.ruoyi.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.app.domain.PriceTick;
import org.apache.ibatis.annotations.Mapper;

/**
 * 价格快照(币安推送落库，用于触发成交/强平审计；大建议分区/分) Mapper 接口（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Mapper
public interface PriceTickMapper extends BaseMapper<PriceTick> {
}
