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
 * 合约定义对象 u_contract
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@TableName("u_contract")
public class Contract
        {
        private static final long serialVersionUID = 1L;

            /** 合约ID */
            @TableId(value = "id", type = IdType.INPUT)
    private Long id;

            /** 合约符号(唯一)：如 BTCUSDT */
        @TableField("symbol")
        @Excel(name = "合约符号(唯一)：如 BTCUSDT")
    private String symbol;

            /** 基础资产：如 BTC */
        @TableField("base_asset")
        @Excel(name = "基础资产：如 BTC")
    private String baseAsset;

            /** 计价资产：如 USDT */
        @TableField("quote_asset")
        @Excel(name = "计价资产：如 USDT")
    private String quoteAsset;

            /** 合约类型：1USDT本位；2币本位(预留) */
        @TableField("contract_type")
        @Excel(name = "合约类型：1USDT本位；2币本位(预留)")
    private Long contractType;

            /** 合约状态：0可交易；1维护/下线 */
        @TableField("status")
        @Excel(name = "合约状态：0可交易；1维护/下线")
    private Long status;

            /** 价格精度(小数位数)，如2/3/4/5 */
        @TableField("price_scale")
        @Excel(name = "价格精度(小数位数)，如2/3/4/5")
    private Long priceScale;

            /** 数量精度(小数位数) */
        @TableField("qty_scale")
        @Excel(name = "数量精度(小数位数)")
    private Long qtyScale;

            /** 最小下单数量 */
        @TableField("min_qty")
        @Excel(name = "最小下单数量")
    private BigDecimal minQty;

            /** 最小名义价值(可选，0表示不限制) */
        @TableField("min_notional")
        @Excel(name = "最小名义价值(可选，0表示不限制)")
    private BigDecimal minNotional;

            /** 合约面值/合约乘数(张价值)，如1或0.001 */
        @TableField("contract_size")
        @Excel(name = "合约面值/合约乘数(张价值)，如1或0.001")
    private BigDecimal contractSize;

            /** 最大杠杆(展示/校验用) */
        @TableField("max_leverage")
        @Excel(name = "最大杠杆(展示/校验用)")
    private Long maxLeverage;

            /** 默认Maker费率(预留；你模式可用统一fee_rate) */
        @TableField("maker_fee_rate")
        @Excel(name = "默认Maker费率(预留；你模式可用统一fee_rate)")
    private BigDecimal makerFeeRate;

            /** 默认Taker费率(预留；你模式可用统一fee_rate) */
        @TableField("taker_fee_rate")
        @Excel(name = "默认Taker费率(预留；你模式可用统一fee_rate)")
    private BigDecimal takerFeeRate;

            /** 资金费率结算周期(小时)，如8 */
        @TableField("funding_interval")
        @Excel(name = "资金费率结算周期(小时)，如8")
    private Long fundingInterval;

            /** 删除标志：0正常；2删除 */
        @TableField("del_flag")
    private String delFlag;

            /** 创建时间 */
        @TableField("create_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

            /** 更新时间 */
        @TableField("update_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

            /** 备注(运营配置说明) */
        @TableField("remark")
        @Excel(name = "备注(运营配置说明)")
    private String remark;

        }
