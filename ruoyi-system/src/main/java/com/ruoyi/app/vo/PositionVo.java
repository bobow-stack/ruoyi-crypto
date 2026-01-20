package com.ruoyi.app.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 当前持仓(一用户一合约一方向一条记录) VO（用于接口入参/出参）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PositionVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

        /** 持仓ID(主键) */
    private Long id;

        /** 用户UID */
    private Long userId;

        /** 合约ID */
    private Long contractId;

        /** 持仓方向：2LONG；3SHORT(建议对冲统一LONG/SHORT；单向也可拆) */
    private Long positionSide;

        /** 保证金模式：1全仓；2逐仓 */
    private Long marginMode;

        /** 杠杆倍数(持仓快照) */
    private Long leverage;

        /** 持仓数量(正数；方向由position_side表达) */
    private BigDecimal qty;

        /** 开仓均价/持仓均价 */
    private BigDecimal entryPrice;

        /** 逐仓保证金(全仓模式可为0) */
    private BigDecimal isolatedMargin;

        /** 未实现盈亏(缓存字段，用于快速展示) */
    private BigDecimal unrealizedPnl;

        /** 强平价(缓存计算结果) */
    private BigDecimal liqPrice;

        /** 最新标记价缓存(展示/快速计算) */
    private BigDecimal markPrice;

        /** 风险档位(对应合约档位tier) */
    private Long riskTier;

        /** 创建时间(首次持仓建立) */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

        /** 更新时间(仓位/参数变化) */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
