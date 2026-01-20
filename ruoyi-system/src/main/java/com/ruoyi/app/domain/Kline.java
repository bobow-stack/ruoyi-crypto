package com.ruoyi.app.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.IdType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * K线数据(大，建议分区/分)对象 u_kline
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@TableName("u_kline")
public class Kline
        {
        private static final long serialVersionUID = 1L;

            /** 主键ID */
            @TableId(value = "id", type = IdType.INPUT)
    private Long id;

            /** 合约ID */
        @TableField("contract_id")
        @Excel(name = "合约ID")
    private Long contractId;

            /** K线周期(分钟)：1/5/15/60/240/1440... */
        @TableField("interval_min")
        @Excel(name = "K线周期(分钟)：1/5/15/60/240/1440...")
    private Long intervalMin;

            /** K线开始时间(周期起点) */
        @TableField("open_time")
        @Excel(name = "K线开始时间(周期起点)")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date openTime;

            /** 开盘价 */
        @TableField("open_price")
        @Excel(name = "开盘价")
    private BigDecimal openPrice;

            /** 最高价 */
        @TableField("high_price")
        @Excel(name = "最高价")
    private BigDecimal highPrice;

            /** 最低价 */
        @TableField("low_price")
        @Excel(name = "最低价")
    private BigDecimal lowPrice;

            /** 收盘价 */
        @TableField("close_price")
        @Excel(name = "收盘价")
    private BigDecimal closePrice;

            /** 成交量(数量口径) */
        @TableField("volume")
        @Excel(name = "成交量(数量口径)")
    private BigDecimal volume;

            /** 成交额/名义价值 */
        @TableField("turnover")
        @Excel(name = "成交额/名义价值")
    private BigDecimal turnover;

            /** 成交笔数(可选) */
        @TableField("trade_count")
        @Excel(name = "成交笔数(可选)")
    private Long tradeCount;

            /** 创建时间 */
        @TableField("create_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

        }
