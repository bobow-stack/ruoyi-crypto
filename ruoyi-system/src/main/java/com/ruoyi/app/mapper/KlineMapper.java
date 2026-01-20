package com.ruoyi.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.app.domain.Kline;
import org.apache.ibatis.annotations.Mapper;

/**
 * K线数据(大，建议分区/分) Mapper 接口（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Mapper
public interface KlineMapper extends BaseMapper<Kline> {
}
