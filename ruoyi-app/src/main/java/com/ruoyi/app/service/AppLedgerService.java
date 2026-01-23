package com.ruoyi.app.service;

import com.ruoyi.app.domain.Account;
import com.ruoyi.app.domain.Balance;
import com.ruoyi.app.domain.Ledger;
import com.ruoyi.app.service.IAccountService;
import com.ruoyi.app.service.IBalanceService;
import com.ruoyi.app.service.ILedgerService;
import com.ruoyi.app.util.AppIdGenerator;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 统一记账服务：余额变动必须从这里进入。
 */
@Service
public class AppLedgerService
{
    private static final Long ACCOUNT_TYPE_CONTRACT = 1L;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IBalanceService balanceService;

    @Autowired
    private ILedgerService ledgerService;

    public List<Balance> listBalances(Long userId)
    {
        return balanceService.lambdaQuery()
                .eq(Balance::getUserId, userId)
                .eq(Balance::getAccountType, ACCOUNT_TYPE_CONTRACT)
                .list();
    }

    public Balance getBalance(Long userId, String asset)
    {
        if (StringUtils.isEmpty(asset))
        {
            return null;
        }
        Balance balance = balanceService.lambdaQuery()
                .eq(Balance::getUserId, userId)
                .eq(Balance::getAccountType, ACCOUNT_TYPE_CONTRACT)
                .eq(Balance::getAsset, asset.toUpperCase())
                .last("limit 1")
                .one();
        if (balance == null)
        {
            balance = new Balance();
            balance.setUserId(userId);
            balance.setAccountType(ACCOUNT_TYPE_CONTRACT);
            balance.setAsset(asset.toUpperCase());
            balance.setAvailable(BigDecimal.ZERO);
            balance.setFrozen(BigDecimal.ZERO);
        }
        return balance;
    }

    public List<Ledger> listLedger(Long userId, String bizType)
    {
        return ledgerService.lambdaQuery()
                .eq(Ledger::getUserId, userId)
                .eq(Ledger::getAccountType, ACCOUNT_TYPE_CONTRACT)
                .eq(StringUtils.isNotEmpty(bizType), Ledger::getBizType, bizType)
                .orderByDesc(Ledger::getId)
                .list();
    }

    /**
     * 记账入口：变更可用余额并写总账流水。
     */
    @Transactional(rollbackFor = Exception.class)
    public Ledger addBalance(Long userId, String asset, BigDecimal amount, String bizType, String memo, String refType, Long refId)
    {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) == 0)
        {
            throw new ServiceException("金额不能为空");
        }
        if (StringUtils.isEmpty(asset))
        {
            throw new ServiceException("资产不能为空");
        }

        Account account = ensureAccount(userId);
        Balance balance = ensureBalance(userId, asset.toUpperCase());

        BigDecimal available = balance.getAvailable() == null ? BigDecimal.ZERO : balance.getAvailable();
        BigDecimal frozen = balance.getFrozen() == null ? BigDecimal.ZERO : balance.getFrozen();
        BigDecimal before = available.add(frozen);
        BigDecimal after = before.add(amount);
        if (after.compareTo(BigDecimal.ZERO) < 0)
        {
            throw new ServiceException("余额不足");
        }

        balance.setAvailable(available.add(amount));
        balance.setUpdateTime(DateUtils.getNowDate());
        balanceService.updateById(balance);

        Ledger ledger = new Ledger();
        ledger.setId(AppIdGenerator.nextId());
        ledger.setUserId(userId);
        ledger.setAccountType(account.getAccountType());
        ledger.setAsset(balance.getAsset());
        ledger.setBizType(bizType);
        ledger.setAmount(amount);
        ledger.setBalanceBefore(before);
        ledger.setBalanceAfter(after);
        ledger.setRefType(refType);
        ledger.setRefId(refId);
        ledger.setMemo(memo);
        ledger.setTs(new Date());
        ledger.setCreateTime(DateUtils.getNowDate());
        ledgerService.save(ledger);
        return ledger;
    }

    private Account ensureAccount(Long userId)
    {
        Account account = accountService.lambdaQuery()
                .eq(Account::getUserId, userId)
                .eq(Account::getAccountType, ACCOUNT_TYPE_CONTRACT)
                .ne(Account::getDelFlag, "2")
                .last("limit 1")
                .one();
        if (account != null)
        {
            return account;
        }
        account = new Account();
        account.setId(AppIdGenerator.nextId());
        account.setUserId(userId);
        account.setAccountType(ACCOUNT_TYPE_CONTRACT);
        account.setStatus(0L);
        account.setDelFlag("0");
        account.setCreateTime(DateUtils.getNowDate());
        accountService.save(account);
        return account;
    }

    private Balance ensureBalance(Long userId, String asset)
    {
        Balance balance = balanceService.lambdaQuery()
                .eq(Balance::getUserId, userId)
                .eq(Balance::getAccountType, ACCOUNT_TYPE_CONTRACT)
                .eq(Balance::getAsset, asset)
                .last("limit 1")
                .one();
        if (balance != null)
        {
            return balance;
        }
        balance = new Balance();
        balance.setId(AppIdGenerator.nextId());
        balance.setUserId(userId);
        balance.setAccountType(ACCOUNT_TYPE_CONTRACT);
        balance.setAsset(asset);
        balance.setAvailable(BigDecimal.ZERO);
        balance.setFrozen(BigDecimal.ZERO);
        balance.setUpdateTime(DateUtils.getNowDate());
        balanceService.save(balance);
        return balance;
    }
}
