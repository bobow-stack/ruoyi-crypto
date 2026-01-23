package com.ruoyi.app.service;

import com.ruoyi.app.domain.Contract;
import com.ruoyi.app.domain.ContractRiskTier;
import com.ruoyi.app.domain.Position;
import com.ruoyi.app.market.MarketDataProcessor;
import com.ruoyi.common.utils.DateUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AppRiskEngine
{
    private static final Long MARGIN_MODE_ISOLATED = 2L;
    private static final Long POSITION_SIDE_LONG = 2L;
    private static final Long POSITION_SIDE_SHORT = 3L;

    @Autowired
    private IPositionService positionService;

    @Autowired
    private IContractService contractService;

    @Autowired
    private IContractRiskTierService riskTierService;

    @Autowired
    private MarketDataProcessor marketDataProcessor;

    @Autowired
    private AppLiquidationService liquidationService;

    @Scheduled(fixedDelayString = "${app.risk.scanIntervalMillis:1000}")
    public void scan()
    {
        List<Position> positions = positionService.lambdaQuery()
                .eq(Position::getMarginMode, MARGIN_MODE_ISOLATED)
                .gt(Position::getQty, BigDecimal.ZERO)
                .list();
        if (positions == null || positions.isEmpty())
        {
            return;
        }
        for (Position position : positions)
        {
            refreshPosition(position);
        }
    }

    private void refreshPosition(Position position)
    {
        Contract contract = contractService.getById(position.getContractId());
        if (contract == null)
        {
            return;
        }
        BigDecimal markPrice = marketDataProcessor.getMarkPrice(contract.getId());
        if (markPrice == null)
        {
            return;
        }
        BigDecimal qty = safe(position.getQty());
        if (qty.compareTo(BigDecimal.ZERO) <= 0)
        {
            return;
        }
        BigDecimal contractSize = contract.getContractSize() == null ? BigDecimal.ONE : contract.getContractSize();
        BigDecimal notional = markPrice.multiply(qty).multiply(contractSize);
        ContractRiskTier tier = resolveRiskTier(contract.getId(), notional);
        BigDecimal mmr = tier == null ? BigDecimal.ZERO : safe(tier.getMmr());
        BigDecimal mm = notional.multiply(mmr);
        BigDecimal im = safe(position.getIsolatedMargin());
        BigDecimal liqPrice = calcLiqPrice(position, im, mm, qty, contractSize);

        position.setMarkPrice(markPrice);
        position.setNotional(notional);
        position.setIm(im);
        position.setMm(mm);
        position.setRiskTier(tier == null ? null : tier.getTier());
        position.setLiqPrice(liqPrice);
        position.setUpdateTime(DateUtils.getNowDate());
        positionService.updateById(position);

        if (shouldLiquidate(position, markPrice, liqPrice))
        {
            liquidationService.liquidate(position, contract, tier, markPrice, liqPrice);
        }
    }

    private boolean shouldLiquidate(Position position, BigDecimal markPrice, BigDecimal liqPrice)
    {
        if (liqPrice == null || markPrice == null)
        {
            return false;
        }
        if (POSITION_SIDE_LONG.equals(position.getPositionSide()))
        {
            return markPrice.compareTo(liqPrice) <= 0;
        }
        if (POSITION_SIDE_SHORT.equals(position.getPositionSide()))
        {
            return markPrice.compareTo(liqPrice) >= 0;
        }
        return false;
    }

    private BigDecimal calcLiqPrice(Position position, BigDecimal margin, BigDecimal mm, BigDecimal qty,
            BigDecimal contractSize)
    {
        if (qty.compareTo(BigDecimal.ZERO) == 0)
        {
            return null;
        }
        BigDecimal entry = safe(position.getEntryPrice());
        BigDecimal denom = qty.multiply(contractSize);
        if (denom.compareTo(BigDecimal.ZERO) == 0)
        {
            return null;
        }
        BigDecimal offset = mm.subtract(margin).divide(denom, 18, RoundingMode.HALF_UP);
        if (POSITION_SIDE_LONG.equals(position.getPositionSide()))
        {
            return entry.add(offset);
        }
        if (POSITION_SIDE_SHORT.equals(position.getPositionSide()))
        {
            return entry.subtract(offset);
        }
        return null;
    }

    private ContractRiskTier resolveRiskTier(Long contractId, BigDecimal notional)
    {
        List<ContractRiskTier> tiers = riskTierService.lambdaQuery()
                .eq(ContractRiskTier::getContractId, contractId)
                .eq(ContractRiskTier::getDelFlag, "0")
                .orderByAsc(ContractRiskTier::getMaxNotional)
                .list();
        if (tiers == null || tiers.isEmpty())
        {
            return null;
        }
        for (ContractRiskTier tier : tiers)
        {
            if (tier.getMaxNotional() != null && notional.compareTo(tier.getMaxNotional()) <= 0)
            {
                return tier;
            }
        }
        return tiers.get(tiers.size() - 1);
    }

    private BigDecimal safe(BigDecimal value)
    {
        return value == null ? BigDecimal.ZERO : value;
    }
}
