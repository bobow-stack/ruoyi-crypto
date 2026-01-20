package com.ruoyi.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.app.domain.Transfer;
import org.apache.ibatis.annotations.Mapper;

/**
 * 账户划转记录(为后续现货预留) Mapper 接口（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Mapper
public interface TransferMapper extends BaseMapper<Transfer> {
}
