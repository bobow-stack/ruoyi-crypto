package com.ruoyi.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * App端启动入口
 */
@SpringBootApplication(scanBasePackages = "com.ruoyi", exclude = { DataSourceAutoConfiguration.class })
public class RuoYiAppApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RuoYiAppApplication.class, args);
        System.out.println("RuoYi-App started successfully.");
    }
}
