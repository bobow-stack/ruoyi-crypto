package com.ruoyi.app.service;

import com.ruoyi.app.domain.Contract;
import com.ruoyi.app.domain.ContractRiskTier;
import com.ruoyi.app.domain.LiquidationEvent;
import com.ruoyi.app.domain.Order;
import com.ruoyi.app.domain.Position;
import com.ruoyi.app.domain.Trade;
import com.ruoyi.app.market.MarketDataProcessor;
import com.ruoyi.app.util.AppIdGenerator;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import java.math.BigDecimal;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppLiquidationService
{
    private static final Long SIDE_BUY = 1L;
    private static final Long SIDE_SELL = 2L;

    private static final Long POSITION_SIDE_LONG = 2L;
    private static final Long POSITION_SIDE_SHORT = 3L;

    private static final Long ORDER_SOURCE_SYSTEM = 3L;
    private static final Long ORDER_TYPE_MARKET = 2L;
    private static final Long ORDER_STATUS_FILLED = 2L;
    private static final Long TIME_IN_FORCE_GTC = 1L;

    private static final Long EXEC_REASON_LIQUIDATION = 5L;

    private static final String DEFAULT_ASSET = "USDT";

    @Autowired
    private IOrderService orderService;

    @Autowired
    private ITradeService tradeService;

    @Autowired
    private IPositionService positionService;

    @Autowired
    private ILiquidationEventService liquidationEventService;

    @Autowired
    private AppLedgerService ledgerService;

    @Autowired
    private AppFeeRateService feeRateService;

    @Autowired
    private MarketDataProcessor marketDataProcessor;

    @Transactional(rollbackFor = Exception.class)
    public void liquidate(Position position, Contract contract, ContractRiskTier tier, BigDecimal markPrice,
            BigDecimal liqPrice)
    {
        if (position == null || contract == null || markPrice == null)
        {
            return;
        }
        Long tickId = marketDataProcessor.getLastTickId(contract.getId());
        if (tickId == null)
        {
            throw new ServiceException("price tick not available");
        }
        if (existsProcessingEvent(position.getId()))
        {
            return;
        }

        Date now = DateUtils.getNowDate();
        LiquidationEvent event = new LiquidationEvent();
        event.setId(AppIdGenerator.nextId());
        event.setUserId(position.getUserId());
        event.setContractId(position.getContractId());
        event.setPositionId(position.getId());
        event.setPositionSide(position.getPositionSide());
        event.setMarginMode(position.getMarginMode());
        event.setTriggerTime(now);
        event.setTriggerType(2L);
        event.setTriggerPrice(markPrice);
        event.setTriggerTickId(tickId);
        event.setMmr(tier == null ? null : tier.getMmr());
        event.setLiqPriceCalc(liqPrice);
        event.setLiqQty(position.getQty());
        event.setStatus(0L);
        event.setCreateTime(now);
        event.setUpdateTime(now);
        liquidationEventService.save(event);

        try
        {
            executeLiquidation(position, contract, markPrice, tickId, event);
            event.setStatus(1L);
            event.setUpdateTime(DateUtils.getNowDate());
            liquidationEventService.updateById(event);
        }
        catch (Exception ex)
        {
            event.setStatus(2L);
            event.setUpdateTime(DateUtils.getNowDate());
            liquidationEventService.updateById(event);
            throw ex;
        }
    }

    private void executeLiquidation(Position position, Contract contract, BigDecimal price, Long tickId,
            LiquidationEvent event)
    {
        BigDecimal qty = safe(position.getQty());
        if (qty.compareTo(BigDecimal.ZERO) <= 0)
        {
            return;
        }
        BigDecimal contractSize = contract.getContractSize() == null ? BigDecimal.ONE : contract.getContractSize();
        BigDecimal notional = price.multiply(qty).multiply(contractSize);
        BigDecimal feeRate = feeRateService.resolveFeeRate(contract.getId());
        BigDecimal feeAmount = notional.multiply(feeRate);
        String asset = contract.getQuoteAsset() == null ? DEFAULT_ASSET : contract.getQuoteAsset();

        Order order = new Order();
        order.setId(AppIdGenerator.nextId());
        order.setUserId(position.getUserId());
        order.setContractId(position.getContractId());
        order.setSource(ORDER_SOURCE_SYSTEM);
        order.setSide(POSITION_SIDE_LONG.equals(position.getPositionSide()) ? SIDE_SELL : SIDE_BUY);
        order.setPositionSide(1L);
        order.setMarginMode(position.getMarginMode());
        order.setLeverage(position.getLeverage());
        order.setOrderType(ORDER_TYPE_MARKET);
        order.setTimeInForce(TIME_IN_FORCE_GTC);
        order.setReduceOnly(1L);
        order.setPrice(price);
        order.setQty(qty);
        order.setFilledQty(qty);
        order.setAvgPrice(price);
        order.setFeeRate(feeRate);
        order.setFeeAsset(asset);
        order.setStatus(ORDER_STATUS_FILLED);
        order.setCreateTime(DateUtils.getNowDate());
        order.setUpdateTime(DateUtils.getNowDate());
        order.setDelFlag("0");
        orderService.save(order);

        Trade trade = new Trade();
        trade.setId(AppIdGenerator.nextId());
        trade.setOrderId(order.getId());
        trade.setUserId(position.getUserId());
        trade.setContractId(position.getContractId());
        trade.setExecReason(EXEC_REASON_LIQUIDATION);
        trade.setExecPrice(price);
        trade.setQty(qty);
        trade.setTurnover(notional);
        trade.setFeeAsset(asset);
        trade.setFeeRate(feeRate);
        trade.setFeeAmount(feeAmount);
        trade.setPriceTickId(tickId);
        trade.setTs(DateUtils.getNowDate());
        trade.setCreateTime(DateUtils.getNowDate());
        tradeService.save(trade);

        BigDecimal pnl = calcPnl(position, price, qty, contractSize);
        BigDecimal releaseMargin = safe(position.getIsolatedMargin());
        applyLedger(position.getUserId(), asset, releaseMargin, "CLOSE_MARGIN", "liquidation margin",
                "ORDER", order.getId());
        applyLedger(position.getUserId(), asset, pnl, "CLOSE_PNL", "liquidation pnl", "ORDER",
                order.getId());
        applyLedger(position.getUserId(), asset, feeAmount.negate(), "TRADE_FEE", "trade fee",
                "TRADE", trade.getId());

        positionService.removeById(position.getId());

        event.setSystemOrderId(order.getId());
        event.setSystemTradeId(trade.getId());
        event.setExecPriceAvg(price);
    }

    private boolean existsProcessingEvent(Long positionId)
    {
        return liquidationEventService.lambdaQuery()
                .eq(LiquidationEvent::getPositionId, positionId)
                .eq(LiquidationEvent::getStatus, 0L)
                .last("limit 1")
                .one() != null;
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

    private BigDecimal safe(BigDecimal value)
    {
        return value == null ? BigDecimal.ZERO : value;
    }
}
