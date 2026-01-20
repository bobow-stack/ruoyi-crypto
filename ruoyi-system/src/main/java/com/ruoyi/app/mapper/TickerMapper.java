package com.ruoyi.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.app.domain.Ticker;
import org.apache.ibatis.annotations.Mapper;

/**
 * Ticker最新快照(可替代Redis快照；也可只存Redis) Mapper 接口（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Mapper
public interface TickerMapper extends BaseMapper<Ticker> {
}
