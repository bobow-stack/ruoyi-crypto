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
 * 总账流水(资金变动必落库，用于对账与审计)对象 u_ledger
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@TableName("u_ledger")
public class Ledger
        {
        private static final long serialVersionUID = 1L;

            /** 流水ID(总账流水主键) */
            @TableId(value = "id", type = IdType.INPUT)
    private Long id;

            /** 用户UID */
        @TableField("user_id")
        @Excel(name = "用户UID")
    private Long userId;

            /** 账户类型：1合约；2现货(预留) */
        @TableField("account_type")
        @Excel(name = "账户类型：1合约；2现货(预留)")
    private Long accountType;

            /** 资产币种：如USDT */
        @TableField("asset")
        @Excel(name = "资产币种：如USDT")
    private String asset;

            /** 业务类型：TRADE_FEE/FUNDING/ORDER_FREEZE/ORDER_UNFREEZE/OPEN_MARGIN/CLOSE_PNL/LIQUIDATION/TRANSFER... */
        @TableField("biz_type")
        @Excel(name = "业务类型：TRADE_FEE/FUNDING/ORDER_FREEZE/ORDER_UNFREEZE/OPEN_MARGIN/CLOSE_PNL/LIQUIDATION/TRANSFER...")
    private String bizType;

            /** 变动金额：正=增加；负=减少 */
        @TableField("amount")
        @Excel(name = "变动金额：正=增加；负=减少")
    private BigDecimal amount;

            /** 变动前余额(定义口径：通常指available+frozen总额或指定口径) */
        @TableField("balance_before")
        @Excel(name = "变动前余额(定义口径：通常指available+frozen总额或指定口径)")
    private BigDecimal balanceBefore;

            /** 变动后余额(与before同口径) */
        @TableField("balance_after")
        @Excel(name = "变动后余额(与before同口径)")
    private BigDecimal balanceAfter;

            /** 关联对象类型：ORDER/TRADE/POSITION/LIQUIDATION/TRANSFER... */
        @TableField("ref_type")
        @Excel(name = "关联对象类型：ORDER/TRADE/POSITION/LIQUIDATION/TRANSFER...")
    private String refType;

            /** 关联对象ID(如订单ID/成交ID/强平ID等) */
        @TableField("ref_id")
        @Excel(name = "关联对象ID(如订单ID/成交ID/强平ID等)")
    private Long refId;

            /** 备注说明(如风控原因/系统描述) */
        @TableField("memo")
        @Excel(name = "备注说明(如风控原因/系统描述)")
    private String memo;

            /** 业务发生时间(不是入库时间) */
        @TableField("ts")
        @Excel(name = "业务发生时间(不是入库时间)")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ts;

            /** 创建时间(入库时间) */
        @TableField("create_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

        }
