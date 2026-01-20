package com.ruoyi.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.app.domain.Trade;
import org.apache.ibatis.annotations.Mapper;

/**
 * 成交明细(平台对手方模式，审计靠价格快照) Mapper 接口（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Mapper
public interface TradeMapper extends BaseMapper<Trade> {
}
