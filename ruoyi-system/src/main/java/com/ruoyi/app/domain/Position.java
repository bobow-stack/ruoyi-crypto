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
 * 当前持仓(一用户一合约一方向一条记录)对象 u_position
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@TableName("u_position")
public class Position
        {
        private static final long serialVersionUID = 1L;

            /** 持仓ID(主键) */
            @TableId(value = "id", type = IdType.INPUT)
    private Long id;

            /** 用户UID */
        @TableField("user_id")
        @Excel(name = "用户UID")
    private Long userId;

            /** 合约ID */
        @TableField("contract_id")
        @Excel(name = "合约ID")
    private Long contractId;

            /** 持仓方向：2LONG；3SHORT(建议对冲统一LONG/SHORT；单向也可拆) */
        @TableField("position_side")
        @Excel(name = "持仓方向：2LONG；3SHORT(建议对冲统一LONG/SHORT；单向也可拆)")
    private Long positionSide;

            /** 保证金模式：1全仓；2逐仓 */
        @TableField("margin_mode")
        @Excel(name = "保证金模式：1全仓；2逐仓")
    private Long marginMode;

            /** 杠杆倍数(持仓快照) */
        @TableField("leverage")
        @Excel(name = "杠杆倍数(持仓快照)")
    private Long leverage;

            /** 持仓数量(正数；方向由position_side表达) */
        @TableField("qty")
        @Excel(name = "持仓数量(正数；方向由position_side表达)")
    private BigDecimal qty;

            /** 开仓均价/持仓均价 */
        @TableField("entry_price")
        @Excel(name = "开仓均价/持仓均价")
    private BigDecimal entryPrice;

            /** 逐仓保证金(全仓模式可为0) */
        @TableField("isolated_margin")
        @Excel(name = "逐仓保证金(全仓模式可为0)")
    private BigDecimal isolatedMargin;

            /** 未实现盈亏(缓存字段，用于快速展示) */
        @TableField("unrealized_pnl")
        @Excel(name = "未实现盈亏(缓存字段，用于快速展示)")
    private BigDecimal unrealizedPnl;

            /** 强平价(缓存计算结果) */
        @TableField("liq_price")
        @Excel(name = "强平价(缓存计算结果)")
    private BigDecimal liqPrice;

            /** 最新标记价缓存(展示/快速计算) */
        @TableField("mark_price")
        @Excel(name = "最新标记价缓存(展示/快速计算)")
    private BigDecimal markPrice;

            /** 风险档位(对应合约档位tier) */
        @TableField("risk_tier")
        @Excel(name = "风险档位(对应合约档位tier)")
    private Long riskTier;

            /** 创建时间(首次持仓建立) */
        @TableField("create_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

            /** 更新时间(仓位/参数变化) */
        @TableField("update_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

        }
