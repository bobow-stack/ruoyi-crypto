package com.ruoyi.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.app.domain.FeeConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * 手续费配置(支持全局/合约/VIP维度覆盖) Mapper 接口（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Mapper
public interface FeeConfigMapper extends BaseMapper<FeeConfig> {
}
