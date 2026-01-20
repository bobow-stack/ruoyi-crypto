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
 * Ticker最新快照(可替代Redis快照；也可只存Redis)对象 u_ticker
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@TableName("u_ticker")
public class Ticker
        {
        private static final long serialVersionUID = 1L;

            /** 主键ID */
            @TableId(value = "id", type = IdType.INPUT)
    private Long id;

            /** 合约ID */
        @TableField("contract_id")
        @Excel(name = "合约ID")
    private Long contractId;

            /** 最新成交价 */
        @TableField("last_price")
        @Excel(name = "最新成交价")
    private BigDecimal lastPrice;

            /** 24H开盘价(或周期内开盘价) */
        @TableField("open_price")
        @Excel(name = "24H开盘价(或周期内开盘价)")
    private BigDecimal openPrice;

            /** 24H最高价(或周期内最高价) */
        @TableField("high_price")
        @Excel(name = "24H最高价(或周期内最高价)")
    private BigDecimal highPrice;

            /** 24H最低价(或周期内最低价) */
        @TableField("low_price")
        @Excel(name = "24H最低价(或周期内最低价)")
    private BigDecimal lowPrice;

            /** 24H成交量(数量口径) */
        @TableField("volume_24h")
        @Excel(name = "24H成交量(数量口径)")
    private BigDecimal volume24h;

            /** 24H成交额/名义价值 */
        @TableField("turnover_24h")
        @Excel(name = "24H成交额/名义价值")
    private BigDecimal turnover24h;

            /** 24H涨跌幅(如0.01=1%) */
        @TableField("change_24h")
        @Excel(name = "24H涨跌幅(如0.01=1%)")
    private BigDecimal change24h;

            /** 标记价格快照 */
        @TableField("mark_price")
        @Excel(name = "标记价格快照")
    private BigDecimal markPrice;

            /** 指数价格快照 */
        @TableField("index_price")
        @Excel(name = "指数价格快照")
    private BigDecimal indexPrice;

            /** 行情时间(数据源时间) */
        @TableField("ts")
        @Excel(name = "行情时间(数据源时间)")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ts;

            /** 创建时间 */
        @TableField("create_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

            /** 更新时间 */
        @TableField("update_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

        }
