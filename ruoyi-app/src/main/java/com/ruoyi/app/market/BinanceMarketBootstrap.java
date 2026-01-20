package com.ruoyi.app.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Start Binance market connection on app boot.
 */
@Component
public class BinanceMarketBootstrap implements CommandLineRunner
{
    @Autowired
    private BinanceWsClient wsClient;

    @Override
    public void run(String... args)
    {
        wsClient.start();
    }
}
