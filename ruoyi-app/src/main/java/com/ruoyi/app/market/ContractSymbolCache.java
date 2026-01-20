package com.ruoyi.app.market;

import com.ruoyi.app.domain.Contract;
import com.ruoyi.app.service.IContractService;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 合约符号缓存（仅启用合约）。
 * 数据库存大写，订阅流使用小写。
 */
@Component
public class ContractSymbolCache
{
    @Autowired
    private IContractService contractService;

    private final Map<String, Contract> symbolMap = new ConcurrentHashMap<>();

    private final Map<Long, Contract> idMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void load()
    {
        reload();
    }

    public synchronized void reload()
    {
        List<Contract> list = contractService.lambdaQuery()
                .eq(Contract::getStatus, 0L)
                .ne(Contract::getDelFlag, "1")
                .list();
        // 统一重建，保证视图一致
        symbolMap.clear();
        idMap.clear();
        for (Contract contract : list)
        {
            if (contract.getSymbol() == null)
            {
                continue;
            }
            String symbol = contract.getSymbol().toUpperCase();
            contract.setSymbol(symbol);
            symbolMap.put(symbol, contract);
            idMap.put(contract.getId(), contract);
        }
    }

    public Map<String, Contract> getSymbolMap()
    {
        return Collections.unmodifiableMap(symbolMap);
    }

    public List<String> getActiveSymbolsUpper()
    {
        return symbolMap.keySet().stream().sorted().collect(Collectors.toList());
    }

    public List<String> getActiveSymbolsLower()
    {
        // Binance stream 名称要求小写
        return symbolMap.keySet().stream().sorted().map(String::toLowerCase).collect(Collectors.toList());
    }

    public Contract getBySymbol(String symbol)
    {
        if (symbol == null)
        {
            return null;
        }
        return symbolMap.get(symbol.toUpperCase());
    }
}
