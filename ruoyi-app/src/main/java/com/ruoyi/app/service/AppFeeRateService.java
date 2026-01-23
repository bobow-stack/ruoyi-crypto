package com.ruoyi.app.service;

import com.ruoyi.app.domain.FeeConfig;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppFeeRateService
{
    private static final BigDecimal DEFAULT_FEE_RATE = new BigDecimal("0.0004");

    @Autowired
    private IFeeConfigService feeConfigService;

    public BigDecimal resolveFeeRate(Long contractId)
    {
        FeeConfig config = feeConfigService.lambdaQuery()
                .eq(FeeConfig::getStatus, 0L)
                .eq(FeeConfig::getDelFlag, "0")
                .eq(FeeConfig::getContractId, contractId)
                .last("limit 1")
                .one();
        if (config == null)
        {
            config = feeConfigService.lambdaQuery()
                    .eq(FeeConfig::getStatus, 0L)
                    .eq(FeeConfig::getDelFlag, "0")
                    .isNull(FeeConfig::getContractId)
                    .last("limit 1")
                    .one();
        }
        if (config == null || config.getFeeRate() == null)
        {
            return DEFAULT_FEE_RATE;
        }
        return config.getFeeRate();
    }
}
