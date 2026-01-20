package com.ruoyi.app.market;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Simple long ID generator (time-based).
 */
public class MarketIdGenerator
{
    private MarketIdGenerator()
    {
    }

    public static long nextId()
    {
        long now = System.currentTimeMillis();
        int rand = ThreadLocalRandom.current().nextInt(1000);
        return now * 1000 + rand;
    }
}
