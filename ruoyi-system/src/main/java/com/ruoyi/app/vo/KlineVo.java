package com.ruoyi.app.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * K线数据(大，建议分区/分) VO（用于接口入参/出参）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KlineVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

        /** 主键ID */
    private Long id;

        /** 合约ID */
    private Long contractId;

        /** K线周期(分钟)：1/5/15/60/240/1440... */
    private Long intervalMin;

        /** K线开始时间(周期起点) */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date openTime;

        /** 开盘价 */
    private BigDecimal openPrice;

        /** 最高价 */
    private BigDecimal highPrice;

        /** 最低价 */
    private BigDecimal lowPrice;

        /** 收盘价 */
    private BigDecimal closePrice;

        /** 成交量(数量口径) */
    private BigDecimal volume;

        /** 成交额/名义价值 */
    private BigDecimal turnover;

        /** 成交笔数(可选) */
    private Long tradeCount;

        /** 创建时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
