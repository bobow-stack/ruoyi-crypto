package com.ruoyi.app.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单(适配触发式成交/止盈止损/强平) VO（用于接口入参/出参）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

        /** 订单ID(主键) */
    private Long id;

        /** 用户UID */
    private Long userId;

        /** 合约ID */
    private Long contractId;

        /** 客户端幂等ID(同用户唯一；防重复下单) */
    private String clientOrderId;

        /** 订单来源：1用户下单；2条件单触发；3系统强平；4管理员(预留) */
    private Long source;

        /** 方向：1买(BUY)；2卖(SELL) */
    private Long side;

        /** 持仓方向：1BOTH(单向)；2LONG；3SHORT */
    private Long positionSide;

        /** 保证金模式：1全仓；2逐仓 */
    private Long marginMode;

        /** 杠杆倍数(下单时快照) */
    private Long leverage;

        /** 订单类型：1限价(LIMIT)；2市价(MARKET)；3触发市价(TRIGGER_MARKET)；4触发限价(TRIGGER_LIMIT) */
    private Long orderType;

        /** 有效方式：1GTC；2IOC；3FOK(触发单一般用GTC) */
    private Long timeInForce;

        /** 只减仓：0否；1是(止盈止损/平仓常用) */
    private Long reduceOnly;

        /** 只挂单(PostOnly)：0否；1是(你模式一般不需要，可保留) */
    private Long postOnly;

        /** 委托价格(限价/触发限价必填；市价为空) */
    private BigDecimal price;

        /** 委托数量 */
    private BigDecimal qty;

        /** 已成交数量(多数一次成交，仍保留) */
    private BigDecimal filledQty;

        /** 成交均价(成交后写入) */
    private BigDecimal avgPrice;

        /** 触发类型：1最新价；2标记价；3指数价(强平建议用标记价) */
    private Long triggerType;

        /** 触发条件：1>=；2<= */
    private Long triggerCondition;

        /** 触发价格(满足条件则激活/成交) */
    private BigDecimal triggerPrice;

        /** 触发所使用的价格快照ID(u_price_tick.id)，用于审计回放 */
    private Long triggerTickId;

        /** 触发激活时间(从条件单变成可执行单的时间) */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activatedTime;

        /** 冻结保证金(开仓/逐仓等；平仓一般为0) */
    private BigDecimal freezeMargin;

        /** 手续费币种(一般USDT) */
    private String feeAsset;

        /** 手续费率快照(成交时确定，存这里便于审计) */
    private BigDecimal feeRate;

        /** 订单状态：0NEW；1部分成交；2全部成交；3已撤销；4已拒绝；5已过期；6已触发待执行(可选) */
    private Long status;

        /** 拒单原因(状态=REJECTED时) */
    private String rejectReason;

        /** 关联条件单ID(u_conditional_order.id)，来源=2时常用 */
    private Long relatedConditionalId;

        /** 关联持仓ID(u_position.id)(止盈止损/强平平仓时常用) */
    private Long relatedPositionId;

        /** 创建时间(下单时间) */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

        /** 更新时间(状态变化时间) */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

        /** 删除标志：0正常；2删除(一般不删订单，仅归档) */
    private String delFlag;

}
