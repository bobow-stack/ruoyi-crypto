package com.ruoyi.app.controller;

import com.ruoyi.app.controller.model.MarketOrderRequest;
import com.ruoyi.app.controller.model.MarketOrderResult;
import com.ruoyi.app.service.AppTradeService;
import com.ruoyi.app.util.AppSecurityUtils;
import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "APP Trade")
@RestController
@RequestMapping("/app/trade")
public class AppTradeController
{
    @Autowired
    private AppTradeService tradeService;

    @ApiOperation("Place market order")
    @PostMapping("/order/market")
    public AjaxResult marketOrder(@RequestBody MarketOrderRequest request)
    {
        Long userId = AppSecurityUtils.getUserId();
        MarketOrderResult result = tradeService.placeMarketOrder(userId, request);
        return AjaxResult.success(result);
    }
}
