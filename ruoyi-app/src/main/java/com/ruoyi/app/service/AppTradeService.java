package com.ruoyi.app.service;

import com.ruoyi.app.controller.model.MarketOrderRequest;
import com.ruoyi.app.controller.model.MarketOrderResult;
import com.ruoyi.app.domain.Contract;
import com.ruoyi.app.domain.ContractRiskTier;
import com.ruoyi.app.domain.Order;
import com.ruoyi.app.domain.Position;
import com.ruoyi.app.domain.Trade;
import com.ruoyi.app.market.MarketDataProcessor;
import com.ruoyi.app.util.AppIdGenerator;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppTradeService
{
    private static final Long SIDE_BUY = 1L;
    private static final Long SIDE_SELL = 2L;

    private static final Long POSITION_SIDE_BOTH = 1L;
    private static final Long POSITION_SIDE_LONG = 2L;
    private static final Long POSITION_SIDE_SHORT = 3L;

    private static final Long MARGIN_MODE_CROSS = 1L;
    private static final Long MARGIN_MODE_ISOLATED = 2L;

    private static final Long ORDER_SOURCE_USER = 1L;
    private static final Long ORDER_TYPE_MARKET = 2L;
    private static final Long ORDER_STATUS_FILLED = 2L;
    private static final Long TIME_IN_FORCE_GTC = 1L;

    private static final Long EXEC_REASON_MARKET = 1L;

    private static final String DEFAULT_ASSET = "USDT";

    @Autowired
    private IContractService contractService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private ITradeService tradeService;

    @Autowired
    private IPositionService positionService;

    @Autowired
    private IContractRiskTierService riskTierService;

    @Autowired
    private AppLedgerService ledgerService;

    @Autowired
    private MarketDataProcessor marketDataProcessor;

    @Autowired
    private AppFeeRateService feeRateService;

    @Transactional(rollbackFor = Exception.class)
    public MarketOrderResult placeMarketOrder(Long userId, MarketOrderRequest request)
    {
        if (userId == null)
        {
            throw new ServiceException("userId is required");
        }
        if (request == null || request.getContractId() == null)
        {
            throw new ServiceException("contractId is required");
        }
        if (request.getSide() == null || (!SIDE_BUY.equals(request.getSide()) && !SIDE_SELL.equals(request.getSide())))
        {
            throw new ServiceException("side is invalid");
        }
        if (request.getQty() == null || request.getQty().compareTo(BigDecimal.ZERO) <= 0)
        {
            throw new ServiceException("qty must be positive");
        }

        Contract contract = contractService.getById(request.getContractId());
        if (contract == null || (contract.getStatus() != null && contract.getStatus() != 0L))
        {
            throw new ServiceException("contract not available");
        }

        BigDecimal price = marketDataProcessor.getMarkPrice(contract.getId());
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0)
        {
            throw new ServiceException("mark price not available");
        }

        Long leverage = request.getLeverage() == null ? 1L : request.getLeverage();
        if (leverage <= 0)
        {
            throw new ServiceException("leverage is invalid");
        }

        Long marginMode = request.getMarginMode() == null ? MARGIN_MODE_CROSS : request.getMarginMode();
        if (!MARGIN_MODE_CROSS.equals(marginMode) && !MARGIN_MODE_ISOLATED.equals(marginMode))
        {
            throw new ServiceException("marginMode is invalid");
        }

        BigDecimal qty = request.getQty();
        BigDecimal contractSize = contract.getContractSize() == null ? BigDecimal.ONE : contract.getContractSize();
        BigDecimal notional = calcNotional(price, qty, contractSize);
        ContractRiskTier tier = resolveRiskTier(contract.getId(), notional);
        if (tier != null && tier.getMaxLeverage() != null && leverage > tier.getMaxLeverage())
        {
            throw new ServiceException("leverage exceeds risk tier");
        }
        BigDecimal feeRate = feeRateService.resolveFeeRate(contract.getId());
        BigDecimal feeAmount = notional.multiply(feeRate);

        boolean reduceOnly = request.getReduceOnly() != null && request.getReduceOnly().equals(1L);
        Long openSide = SIDE_BUY.equals(request.getSide()) ? POSITION_SIDE_LONG : POSITION_SIDE_SHORT;

        Position position = getSinglePosition(userId, contract.getId());
        if (position == null)
        {
            if (reduceOnly)
            {
                throw new ServiceException("no position to reduce");
            }
            return openOrIncrease(userId, contract, request, price, qty, contractSize, notional, feeRate, feeAmount,
                    marginMode, leverage, openSide, tier);
        }

        if (position.getPositionSide() == null || position.getPositionSide().equals(openSide))
        {
            if (reduceOnly)
            {
                throw new ServiceException("reduceOnly not allowed for open");
            }
            if (position.getMarginMode() != null && !position.getMarginMode().equals(marginMode))
            {
                throw new ServiceException("marginMode mismatch");
            }
            if (position.getLeverage() != null && !position.getLeverage().equals(leverage))
            {
                throw new ServiceException("leverage mismatch");
            }
            return openOrIncrease(userId, contract, request, price, qty, contractSize, notional, feeRate, feeAmount,
                    marginMode, leverage, openSide, tier, position);
        }

        return closePosition(userId, contract, request, price, qty, contractSize, notional, feeRate, feeAmount, position);
    }

    private MarketOrderResult openOrIncrease(Long userId, Contract contract, MarketOrderRequest request, BigDecimal price,
            BigDecimal qty, BigDecimal contractSize, BigDecimal notional, BigDecimal feeRate, BigDecimal feeAmount,
            Long marginMode, Long leverage, Long openSide, ContractRiskTier tier)
    {
        return openOrIncrease(userId, contract, request, price, qty, contractSize, notional, feeRate, feeAmount,
                marginMode, leverage, openSide, tier, null);
    }

    private MarketOrderResult openOrIncrease(Long userId, Contract contract, MarketOrderRequest request, BigDecimal price,
            BigDecimal qty, BigDecimal contractSize, BigDecimal notional, BigDecimal feeRate, BigDecimal feeAmount,
            Long marginMode, Long leverage, Long openSide, ContractRiskTier tier, Position position)
    {
        BigDecimal margin = calcMargin(notional, leverage);
        Date now = DateUtils.getNowDate();
        String asset = contract.getQuoteAsset() == null ? DEFAULT_ASSET : contract.getQuoteAsset();

        Order order = buildOrder(userId, contract.getId(), request, price, qty, marginMode, leverage, feeRate, asset,
                false, now);
        orderService.save(order);

        Trade trade = buildTrade(userId, contract.getId(), order.getId(), price, qty, notional, feeRate, feeAmount,
                asset, now);
        tradeService.save(trade);

        applyLedger(userId, asset, margin.negate(), "OPEN_MARGIN", "open margin", "ORDER", order.getId());
        applyLedger(userId, asset, feeAmount.negate(), "TRADE_FEE", "trade fee", "TRADE", trade.getId());

        Position updated = position == null ? new Position() : position;
        boolean isNew = position == null;
        BigDecimal newQty = isNew ? qty : safe(position.getQty()).add(qty);
        BigDecimal entryPrice = isNew ? price : calcWeightedEntry(position, price, qty);

        BigDecimal mm = BigDecimal.ZERO;
        if (tier != null && tier.getMmr() != null)
        {
            mm = calcNotional(price, newQty, contractSize).multiply(tier.getMmr());
        }

        updated.setUserId(userId);
        updated.setContractId(contract.getId());
        updated.setPositionSide(openSide);
        updated.setMarginMode(marginMode);
        updated.setLeverage(leverage);
        updated.setQty(newQty);
        updated.setEntryPrice(entryPrice);
        if (MARGIN_MODE_ISOLATED.equals(marginMode))
        {
            updated.setIsolatedMargin(safe(updated.getIsolatedMargin()).add(margin));
        }
        updated.setMarkPrice(price);
        updated.setNotional(calcNotional(price, newQty, contractSize));
        updated.setIm(safe(updated.getIm()).add(margin));
        updated.setMm(mm);
        updated.setRealizedPnl(safe(updated.getRealizedPnl()));
        updated.setRiskTier(tier == null ? null : tier.getTier());
        updated.setUpdateTime(now);
        if (isNew)
        {
            updated.setId(AppIdGenerator.nextId());
            updated.setCreateTime(now);
            positionService.save(updated);
        }
        else
        {
            positionService.updateById(updated);
        }

        MarketOrderResult result = new MarketOrderResult();
        result.setOrderId(order.getId());
        result.setTradeId(trade.getId());
        result.setPositionId(updated.getId());
        result.setExecPrice(price);
        result.setFeeAmount(feeAmount);
        result.setPnl(BigDecimal.ZERO);
        return result;
    }

    private MarketOrderResult closePosition(Long userId, Contract contract, MarketOrderRequest request, BigDecimal price,
            BigDecimal qty, BigDecimal contractSize, BigDecimal notional, BigDecimal feeRate, BigDecimal feeAmount,
            Position position)
    {
        BigDecimal positionQty = safe(position.getQty());
        if (qty.compareTo(positionQty) > 0)
        {
            throw new ServiceException("close qty exceeds position");
        }

        Date now = DateUtils.getNowDate();
        String asset = contract.getQuoteAsset() == null ? DEFAULT_ASSET : contract.getQuoteAsset();

        Order order = buildOrder(userId, contract.getId(), request, price, qty, position.getMarginMode(),
                position.getLeverage(), feeRate, asset, true, now);
        orderService.save(order);

        Trade trade = buildTrade(userId, contract.getId(), order.getId(), price, qty, notional, feeRate, feeAmount,
                asset, now);
        tradeService.save(trade);

        BigDecimal pnl = calcPnl(position, price, qty, contractSize);
        BigDecimal releaseMargin = calcReleaseMargin(position, qty, positionQty);

        applyLedger(userId, asset, releaseMargin, "CLOSE_MARGIN", "close margin", "ORDER", order.getId());
        applyLedger(userId, asset, pnl, "CLOSE_PNL", "close pnl", "ORDER", order.getId());
        applyLedger(userId, asset, feeAmount.negate(), "TRADE_FEE", "trade fee", "TRADE", trade.getId());

        BigDecimal remaining = positionQty.subtract(qty);
        if (remaining.compareTo(BigDecimal.ZERO) == 0)
        {
            positionService.removeById(position.getId());
        }
        else
        {
            position.setQty(remaining);
            position.setMarkPrice(price);
            position.setNotional(calcNotional(price, remaining, contractSize));
            position.setRealizedPnl(safe(position.getRealizedPnl()).add(pnl));
            if (position.getIsolatedMargin() != null)
            {
                position.setIsolatedMargin(maxZero(position.getIsolatedMargin().subtract(releaseMargin)));
            }
            if (position.getIm() != null)
            {
                position.setIm(maxZero(position.getIm().subtract(releaseMargin)));
            }
            position.setUpdateTime(now);
            positionService.updateById(position);
        }

        MarketOrderResult result = new MarketOrderResult();
        result.setOrderId(order.getId());
        result.setTradeId(trade.getId());
        result.setPositionId(remaining.compareTo(BigDecimal.ZERO) == 0 ? null : position.getId());
        result.setExecPrice(price);
        result.setFeeAmount(feeAmount);
        result.setPnl(pnl);
        return result;
    }

    private Position getSinglePosition(Long userId, Long contractId)
    {
        List<Position> positions = positionService.lambdaQuery()
                .eq(Position::getUserId, userId)
                .eq(Position::getContractId, contractId)
                .list();
        if (positions == null || positions.isEmpty())
        {
            return null;
        }
        if (positions.size() > 1)
        {
            throw new ServiceException("multiple positions exist");
        }
        return positions.get(0);
    }

    private Order buildOrder(Long userId, Long contractId, MarketOrderRequest request, BigDecimal price, BigDecimal qty,
            Long marginMode, Long leverage, BigDecimal feeRate, String feeAsset, boolean reduceOnly, Date now)
    {
        Order order = new Order();
        order.setId(AppIdGenerator.nextId());
        order.setUserId(userId);
        order.setContractId(contractId);
        order.setClientOrderId(request.getClientOrderId());
        order.setSource(ORDER_SOURCE_USER);
        order.setSide(request.getSide());
        order.setPositionSide(POSITION_SIDE_BOTH);
        order.setMarginMode(marginMode);
        order.setLeverage(leverage);
        order.setOrderType(ORDER_TYPE_MARKET);
        order.setTimeInForce(TIME_IN_FORCE_GTC);
        order.setReduceOnly(reduceOnly ? 1L : 0L);
        order.setPrice(price);
        order.setQty(qty);
        order.setFilledQty(qty);
        order.setAvgPrice(price);
        order.setFeeRate(feeRate);
        order.setFeeAsset(feeAsset);
        order.setStatus(ORDER_STATUS_FILLED);
        order.setCreateTime(now);
        order.setUpdateTime(now);
        order.setDelFlag("0");
        return order;
    }

    private Trade buildTrade(Long userId, Long contractId, Long orderId, BigDecimal price, BigDecimal qty,
            BigDecimal notional, BigDecimal feeRate, BigDecimal feeAmount, String feeAsset, Date now)
    {
        Long priceTickId = marketDataProcessor.getLastTickId(contractId);
        if (priceTickId == null)
        {
            throw new ServiceException("price tick not available");
        }
        Trade trade = new Trade();
        trade.setId(AppIdGenerator.nextId());
        trade.setOrderId(orderId);
        trade.setUserId(userId);
        trade.setContractId(contractId);
        trade.setExecReason(EXEC_REASON_MARKET);
        trade.setExecPrice(price);
        trade.setQty(qty);
        trade.setTurnover(notional);
        trade.setFeeAsset(feeAsset);
        trade.setFeeRate(feeRate);
        trade.setFeeAmount(feeAmount);
        trade.setPriceTickId(priceTickId);
        trade.setTs(now);
        trade.setCreateTime(now);
        return trade;
    }

    private void applyLedger(Long userId, String asset, BigDecimal amount, String bizType, String memo, String refType,
            Long refId)
    {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) == 0)
        {
            return;
        }
        ledgerService.addBalance(userId, asset, amount, bizType, memo, refType, refId);
    }

    private BigDecimal calcNotional(BigDecimal price, BigDecimal qty, BigDecimal contractSize)
    {
        return price.multiply(qty).multiply(contractSize);
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

    private BigDecimal calcMargin(BigDecimal notional, Long leverage)
    {
        return notional.divide(new BigDecimal(leverage), 18, RoundingMode.HALF_UP);
    }

    private BigDecimal calcWeightedEntry(Position position, BigDecimal price, BigDecimal qty)
    {
        BigDecimal oldQty = safe(position.getQty());
        BigDecimal totalQty = oldQty.add(qty);
        BigDecimal oldCost = safe(position.getEntryPrice()).multiply(oldQty);
        BigDecimal newCost = price.multiply(qty);
        return oldCost.add(newCost).divide(totalQty, 18, RoundingMode.HALF_UP);
    }

    private BigDecimal calcPnl(Position position, BigDecimal price, BigDecimal qty, BigDecimal contractSize)
    {
        BigDecimal entry = safe(position.getEntryPrice());
        BigDecimal diff;
        if (POSITION_SIDE_LONG.equals(position.getPositionSide()))
        {
            diff = price.subtract(entry);
        }
        else
        {
            diff = entry.subtract(price);
        }
        return diff.multiply(qty).multiply(contractSize);
    }

    private BigDecimal calcReleaseMargin(Position position, BigDecimal closeQty, BigDecimal positionQty)
    {
        if (closeQty == null || positionQty == null || positionQty.compareTo(BigDecimal.ZERO) == 0)
        {
            return BigDecimal.ZERO;
        }
        BigDecimal ratio = closeQty.divide(positionQty, 18, RoundingMode.HALF_UP);
        BigDecimal base = safe(position.getIm());
        if (base.compareTo(BigDecimal.ZERO) == 0)
        {
            base = safe(position.getIsolatedMargin());
        }
        return base.multiply(ratio);
    }

    private BigDecimal safe(BigDecimal value)
    {
        return value == null ? BigDecimal.ZERO : value;
    }

    private BigDecimal maxZero(BigDecimal value)
    {
        if (value == null)
        {
            return BigDecimal.ZERO;
        }
        return value.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : value;
    }
}
