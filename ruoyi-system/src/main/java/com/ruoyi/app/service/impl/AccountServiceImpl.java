package com.ruoyi.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.app.domain.Account;
import com.ruoyi.app.mapper.AccountMapper;
import com.ruoyi.app.service.IAccountService;
import org.springframework.stereotype.Service;

/**
 * 账户(按账户类型分合约/现货) ServiceImpl（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {
}
