package com.ruoyi.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.app.domain.Ledger;
import org.apache.ibatis.annotations.Mapper;

/**
 * 总账流水(资金变动必落库，用于对账与审计) Mapper 接口（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Mapper
public interface LedgerMapper extends BaseMapper<Ledger> {
}
