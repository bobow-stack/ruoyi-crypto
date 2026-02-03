package com.ruoyi.app.controller;

import com.ruoyi.app.controller.model.AppHomeOverview;
import com.ruoyi.app.service.AppHomeService;
import com.ruoyi.app.util.AppSecurityUtils;
import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "APP首页")
@RestController
@RequestMapping("/app/home")
public class AppHomeController
{
    @Autowired
    private AppHomeService homeService;

    @ApiOperation("首页概览")
    @GetMapping("/overview")
    public AjaxResult overview()
    {
        Long userId = AppSecurityUtils.getUserId();
        AppHomeOverview data = homeService.getOverview(userId);
        return AjaxResult.success(data);
    }
}
