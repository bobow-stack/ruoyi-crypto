package com.ruoyi.app.service;

import com.ruoyi.app.controller.model.AppHomeOverview;
import com.ruoyi.app.domain.Announcement;
import com.ruoyi.app.domain.Balance;
import com.ruoyi.app.domain.Contract;
import com.ruoyi.app.domain.Position;
import com.ruoyi.app.domain.Ticker;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppHomeService
{
    private static final String DEFAULT_ASSET = "USDT";
    private static final Long POSITION_SIDE_LONG = 2L;

    @Autowired
    private AppLedgerService ledgerService;

    @Autowired
    private ITickerService tickerService;

    @Autowired
    private IContractService contractService;

    @Autowired
    private IPositionService positionService;

    @Autowired
    private IAnnouncementService announcementService;

    public AppHomeOverview getOverview(Long userId)
    {
        AppHomeOverview overview = new AppHomeOverview();
        overview.setAsset(buildAssetSummary(userId));
        overview.setTickers(buildHotTickers());
        overview.setPositions(buildPositionBrief(userId));
        overview.setAnnouncements(buildAnnouncements());
        return overview;
    }

    private AppHomeOverview.AssetSummary buildAssetSummary(Long userId)
    {
        List<Balance> balances = ledgerService.listBalances(userId);
        BigDecimal available = BigDecimal.ZERO;
        BigDecimal total = BigDecimal.ZERO;
        if (balances != null)
        {
            for (Balance balance : balances)
            {
                if (DEFAULT_ASSET.equalsIgnoreCase(balance.getAsset()))
                {
                    BigDecimal a = safe(balance.getAvailable());
                    BigDecimal f = safe(balance.getFrozen());
                    available = available.add(a);
                    total = total.add(a).add(f);
                }
            }
        }

        List<Position> positions = positionService.lambdaQuery()
                .eq(Position::getUserId, userId)
                .list();
        BigDecimal marginUsed = BigDecimal.ZERO;
        BigDecimal unrealizedPnl = BigDecimal.ZERO;
        Map<Long, Contract> contractMap = loadContracts(positions);
        if (positions != null)
        {
            for (Position position : positions)
            {
                marginUsed = marginUsed.add(safe(position.getIm()));
                Contract contract = contractMap.get(position.getContractId());
                unrealizedPnl = unrealizedPnl.add(calcUnrealizedPnl(position, contract));
            }
        }

        AppHomeOverview.AssetSummary summary = new AppHomeOverview.AssetSummary();
        summary.setAsset(DEFAULT_ASSET);
        summary.setTotalEquity(total.add(unrealizedPnl));
        summary.setAvailable(available);
        summary.setMarginUsed(marginUsed);
        summary.setUnrealizedPnl(unrealizedPnl);
        return summary;
    }

    private List<AppHomeOverview.TickerItem> buildHotTickers()
    {
        List<Ticker> list = tickerService.lambdaQuery()
                .orderByDesc(Ticker::getTurnover24h)
                .last("limit 10")
                .list();
        Map<Long, Contract> contractMap = loadContractsByTickers(list);
        List<AppHomeOverview.TickerItem> items = new ArrayList<>();
        if (list == null)
        {
            return items;
        }
        for (Ticker ticker : list)
        {
            Contract contract = contractMap.get(ticker.getContractId());
            if (contract == null)
            {
                continue;
            }
            AppHomeOverview.TickerItem item = new AppHomeOverview.TickerItem();
            item.setContractId(contract.getId());
            item.setSymbol(contract.getSymbol());
            item.setLastPrice(ticker.getLastPrice());
            item.setChange24h(ticker.getChange24h());
            item.setTurnover24h(ticker.getTurnover24h());
            items.add(item);
        }
        return items;
    }

    private List<AppHomeOverview.PositionItem> buildPositionBrief(Long userId)
    {
        List<Position> positions = positionService.lambdaQuery()
                .eq(Position::getUserId, userId)
                .list();
        Map<Long, Contract> contractMap = loadContracts(positions);
        List<AppHomeOverview.PositionItem> items = new ArrayList<>();
        if (positions == null)
        {
            return items;
        }
        for (Position position : positions)
        {
            Contract contract = contractMap.get(position.getContractId());
            if (contract == null)
            {
                continue;
            }
            AppHomeOverview.PositionItem item = new AppHomeOverview.PositionItem();
            item.setContractId(contract.getId());
            item.setSymbol(contract.getSymbol());
            item.setSide(position.getPositionSide());
            item.setLeverage(position.getLeverage());
            item.setQty(position.getQty());
            item.setEntryPrice(position.getEntryPrice());
            item.setMarkPrice(position.getMarkPrice());
            item.setUnrealizedPnl(calcUnrealizedPnl(position, contract));
            items.add(item);
        }
        return items;
    }

    private List<AppHomeOverview.AnnouncementItem> buildAnnouncements()
    {
        List<Announcement> list = announcementService.lambdaQuery()
                .eq(Announcement::getStatus, 0L)
                .eq(Announcement::getDelFlag, "0")
                .orderByDesc(Announcement::getPublishTime)
                .last("limit 3")
                .list();
        List<AppHomeOverview.AnnouncementItem> items = new ArrayList<>();
        if (list == null)
        {
            return items;
        }
        for (Announcement announcement : list)
        {
            AppHomeOverview.AnnouncementItem item = new AppHomeOverview.AnnouncementItem();
            item.setId(announcement.getId());
            item.setTitle(announcement.getTitle());
            item.setPublishTime(announcement.getPublishTime());
            items.add(item);
        }
        return items;
    }

    private Map<Long, Contract> loadContracts(List<Position> positions)
    {
        Map<Long, Contract> map = new HashMap<>();
        if (positions == null || positions.isEmpty())
        {
            return map;
        }
        List<Long> ids = new ArrayList<>();
        for (Position position : positions)
        {
            if (position.getContractId() != null)
            {
                ids.add(position.getContractId());
            }
        }
        if (ids.isEmpty())
        {
            return map;
        }
        List<Contract> contracts = contractService.listByIds(ids);
        if (contracts != null)
        {
            for (Contract contract : contracts)
            {
                map.put(contract.getId(), contract);
            }
        }
        return map;
    }

    private Map<Long, Contract> loadContractsByTickers(List<Ticker> tickers)
    {
        Map<Long, Contract> map = new HashMap<>();
        if (tickers == null || tickers.isEmpty())
        {
            return map;
        }
        List<Long> ids = new ArrayList<>();
        for (Ticker ticker : tickers)
        {
            if (ticker.getContractId() != null)
            {
                ids.add(ticker.getContractId());
            }
        }
        if (ids.isEmpty())
        {
            return map;
        }
        List<Contract> contracts = contractService.listByIds(ids);
        if (contracts != null)
        {
            for (Contract contract : contracts)
            {
                map.put(contract.getId(), contract);
            }
        }
        return map;
    }

    private BigDecimal calcUnrealizedPnl(Position position, Contract contract)
    {
        if (position == null)
        {
            return BigDecimal.ZERO;
        }
        BigDecimal markPrice = position.getMarkPrice();
        BigDecimal entryPrice = position.getEntryPrice();
        if (markPrice == null || entryPrice == null || contract == null)
        {
            return BigDecimal.ZERO;
        }
        BigDecimal qty = safe(position.getQty());
        BigDecimal contractSize = contract.getContractSize() == null ? BigDecimal.ONE : contract.getContractSize();
        BigDecimal diff;
        if (POSITION_SIDE_LONG.equals(position.getPositionSide()))
        {
            diff = markPrice.subtract(entryPrice);
        }
        else
        {
            diff = entryPrice.subtract(markPrice);
        }
        return diff.multiply(qty).multiply(contractSize);
    }

    private BigDecimal safe(BigDecimal value)
    {
        return value == null ? BigDecimal.ZERO : value;
    }
}
