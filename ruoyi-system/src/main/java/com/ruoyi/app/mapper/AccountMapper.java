package com.ruoyi.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.app.domain.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * 账户(按账户类型分合约/现货) Mapper 接口（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
