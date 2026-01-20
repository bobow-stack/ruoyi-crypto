package com.ruoyi.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.app.domain.Balance;
import com.ruoyi.app.mapper.BalanceMapper;
import com.ruoyi.app.service.IBalanceService;
import org.springframework.stereotype.Service;

/**
 * 余额(强一致更新；配套总账流水) ServiceImpl（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Service
public class BalanceServiceImpl extends ServiceImpl<BalanceMapper, Balance> implements IBalanceService {
}
