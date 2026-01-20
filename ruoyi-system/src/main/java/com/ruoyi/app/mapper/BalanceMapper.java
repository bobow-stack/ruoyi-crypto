package com.ruoyi.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.app.domain.Balance;
import org.apache.ibatis.annotations.Mapper;

/**
 * 余额(强一致更新；配套总账流水) Mapper 接口（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Mapper
public interface BalanceMapper extends BaseMapper<Balance> {
}
