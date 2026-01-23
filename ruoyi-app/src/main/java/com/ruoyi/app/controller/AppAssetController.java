package com.ruoyi.app.controller;

import com.ruoyi.app.domain.Balance;
import com.ruoyi.app.domain.Ledger;
import com.ruoyi.app.service.AppLedgerService;
import com.ruoyi.app.util.AppSecurityUtils;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 资产与记账接口（APP）。
 */
@Api(tags = "APP资产")
@RestController
@RequestMapping("/app/asset")
public class AppAssetController extends BaseController
{
    @Autowired
    private AppLedgerService ledgerService;

    @ApiOperation("资产列表")
    @GetMapping("/balances")
    public AjaxResult balances()
    {
        Long userId = AppSecurityUtils.getUserId();
        List<Balance> list = ledgerService.listBalances(userId);
        return AjaxResult.success(list);
    }

    @ApiOperation("单币种余额")
    @GetMapping("/balance")
    public AjaxResult balance(@RequestParam("asset") String asset)
    {
        Long userId = AppSecurityUtils.getUserId();
        Balance balance = ledgerService.getBalance(userId, asset);
        return AjaxResult.success(balance);
    }

    @ApiOperation("流水分页")
    @GetMapping("/ledger")
    public TableDataInfo ledger(@RequestParam(value = "bizType", required = false) String bizType)
    {
        Long userId = AppSecurityUtils.getUserId();
        startPage();
        List<Ledger> list = ledgerService.listLedger(userId, bizType);
        return getDataTable(list);
    }

    @ApiOperation("模拟充值（仅测试）")
    @PostMapping("/recharge")
    public AjaxResult recharge(@RequestParam("asset") String asset, @RequestParam("amount") BigDecimal amount)
    {
        Long userId = AppSecurityUtils.getUserId();
        ledgerService.addBalance(userId, asset, amount, "TEST_DEPOSIT", "模拟充值", "MANUAL", null);
        return AjaxResult.success();
    }
}
