package com.ruoyi.app.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Ticker最新快照(可替代Redis快照；也可只存Redis) VO（用于接口入参/出参）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TickerVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

        /** 主键ID */
    private Long id;

        /** 合约ID */
    private Long contractId;

        /** 最新成交价 */
    private BigDecimal lastPrice;

        /** 24H开盘价(或周期内开盘价) */
    private BigDecimal openPrice;

        /** 24H最高价(或周期内最高价) */
    private BigDecimal highPrice;

        /** 24H最低价(或周期内最低价) */
    private BigDecimal lowPrice;

        /** 24H成交量(数量口径) */
    private BigDecimal volume24h;

        /** 24H成交额/名义价值 */
    private BigDecimal turnover24h;

        /** 24H涨跌幅(如0.01=1%) */
    private BigDecimal change24h;

        /** 标记价格快照 */
    private BigDecimal markPrice;

        /** 指数价格快照 */
    private BigDecimal indexPrice;

        /** 行情时间(数据源时间) */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ts;

        /** 创建时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

        /** 更新时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
