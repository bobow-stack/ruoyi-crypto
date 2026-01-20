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
 * 余额(强一致更新；配套总账流水)对象 u_balance
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@TableName("u_balance")
public class Balance
        {
        private static final long serialVersionUID = 1L;

            /** 余额记录ID */
            @TableId(value = "id", type = IdType.INPUT)
    private Long id;

            /** 用户UID */
        @TableField("user_id")
        @Excel(name = "用户UID")
    private Long userId;

            /** 账户类型：1合约(本期)；2现货(预留) */
        @TableField("account_type")
        @Excel(name = "账户类型：1合约(本期)；2现货(预留)")
    private Long accountType;

            /** 资产币种：如USDT */
        @TableField("asset")
        @Excel(name = "资产币种：如USDT")
    private String asset;

            /** 可用余额(可下单/可转出) */
        @TableField("available")
        @Excel(name = "可用余额(可下单/可转出)")
    private BigDecimal available;

            /** 冻结余额(挂单冻结/提现冻结等) */
        @TableField("frozen")
        @Excel(name = "冻结余额(挂单冻结/提现冻结等)")
    private BigDecimal frozen;

            /** 更新时间(余额变动时间) */
        @TableField("update_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

        }
