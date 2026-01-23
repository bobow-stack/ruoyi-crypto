package com.ruoyi.app.util;

/**
 * APP 端雪花 ID 生成器。
 */
public class AppIdGenerator
{
    private static final Snowflake SNOWFLAKE = new Snowflake(
            getWorkerId(), getDatacenterId());

    private AppIdGenerator()
    {
    }

    public static long nextId()
    {
        return SNOWFLAKE.nextId();
    }

    private static long getWorkerId()
    {
        String value = System.getProperty("app.workerId");
        if (value == null || value.isEmpty())
        {
            return 1L;
        }
        return Long.parseLong(value);
    }

    private static long getDatacenterId()
    {
        String value = System.getProperty("app.datacenterId");
        if (value == null || value.isEmpty())
        {
            return 1L;
        }
        return Long.parseLong(value);
    }

    /**
     * 简化雪花算法实现（41位时间戳 + 5位机房 + 5位机器 + 12位序列）。
     */
    private static class Snowflake
    {
        private static final long EPOCH = 1704067200000L;

        private static final long WORKER_ID_BITS = 5L;
        private static final long DATACENTER_ID_BITS = 5L;
        private static final long SEQUENCE_BITS = 12L;

        private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);
        private static final long MAX_DATACENTER_ID = ~(-1L << DATACENTER_ID_BITS);

        private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
        private static final long DATACENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
        private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATACENTER_ID_BITS;

        private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

        private final long workerId;
        private final long datacenterId;
        private long sequence = 0L;
        private long lastTimestamp = -1L;

        private Snowflake(long workerId, long datacenterId)
        {
            if (workerId > MAX_WORKER_ID || workerId < 0)
            {
                throw new IllegalArgumentException("workerId out of range");
            }
            if (datacenterId > MAX_DATACENTER_ID || datacenterId < 0)
            {
                throw new IllegalArgumentException("datacenterId out of range");
            }
            this.workerId = workerId;
            this.datacenterId = datacenterId;
        }

        private synchronized long nextId()
        {
            long timestamp = currentTime();
            if (timestamp < lastTimestamp)
            {
                throw new IllegalStateException("Clock moved backwards");
            }
            if (timestamp == lastTimestamp)
            {
                sequence = (sequence + 1) & SEQUENCE_MASK;
                if (sequence == 0L)
                {
                    timestamp = waitNextMillis(lastTimestamp);
                }
            }
            else
            {
                sequence = 0L;
            }
            lastTimestamp = timestamp;
            return ((timestamp - EPOCH) << TIMESTAMP_LEFT_SHIFT)
                    | (datacenterId << DATACENTER_ID_SHIFT)
                    | (workerId << WORKER_ID_SHIFT)
                    | sequence;
        }

        private long waitNextMillis(long lastTimestamp)
        {
            long timestamp = currentTime();
            while (timestamp <= lastTimestamp)
            {
                timestamp = currentTime();
            }
            return timestamp;
        }

        private long currentTime()
        {
            return System.currentTimeMillis();
        }
    }
}
