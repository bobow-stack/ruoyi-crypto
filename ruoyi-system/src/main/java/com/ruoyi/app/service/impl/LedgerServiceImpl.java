package com.ruoyi.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.app.domain.Ledger;
import com.ruoyi.app.mapper.LedgerMapper;
import com.ruoyi.app.service.ILedgerService;
import org.springframework.stereotype.Service;

/**
 * 总账流水(资金变动必落库，用于对账与审计) ServiceImpl（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Service
public class LedgerServiceImpl extends ServiceImpl<LedgerMapper, Ledger> implements ILedgerService {
}
