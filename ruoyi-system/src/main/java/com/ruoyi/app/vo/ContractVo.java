package com.ruoyi.app.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 合约定义 VO（用于接口入参/出参）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ContractVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

        /** 合约ID */
    private Long id;

        /** 合约符号(唯一)：如 BTCUSDT */
    private String symbol;

        /** 基础资产：如 BTC */
    private String baseAsset;

        /** 计价资产：如 USDT */
    private String quoteAsset;

        /** 合约类型：1USDT本位；2币本位(预留) */
    private Long contractType;

        /** 合约状态：0可交易；1维护/下线 */
    private Long status;

        /** 价格精度(小数位数)，如2/3/4/5 */
    private Long priceScale;

        /** 数量精度(小数位数) */
    private Long qtyScale;

        /** 最小下单数量 */
    private BigDecimal minQty;

        /** 最小名义价值(可选，0表示不限制) */
    private BigDecimal minNotional;

        /** 合约面值/合约乘数(张价值)，如1或0.001 */
    private BigDecimal contractSize;

        /** 最大杠杆(展示/校验用) */
    private Long maxLeverage;

        /** 默认Maker费率(预留；你模式可用统一fee_rate) */
    private BigDecimal makerFeeRate;

        /** 默认Taker费率(预留；你模式可用统一fee_rate) */
    private BigDecimal takerFeeRate;

        /** 资金费率结算周期(小时)，如8 */
    private Long fundingInterval;

        /** 删除标志：0正常；2删除 */
    private String delFlag;

        /** 创建时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

        /** 更新时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

        /** 备注(运营配置说明) */
    private String remark;

}
