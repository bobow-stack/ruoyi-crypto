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
 * 账户划转记录(为后续现货预留)对象 u_transfer
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@TableName("u_transfer")
public class Transfer
        {
        private static final long serialVersionUID = 1L;

            /** 划转记录ID */
            @TableId(value = "id", type = IdType.INPUT)
    private Long id;

            /** 用户UID */
        @TableField("user_id")
        @Excel(name = "用户UID")
    private Long userId;

            /** 转出账户类型：1合约；2现货 */
        @TableField("from_type")
        @Excel(name = "转出账户类型：1合约；2现货")
    private Long fromType;

            /** 转入账户类型：1合约；2现货 */
        @TableField("to_type")
        @Excel(name = "转入账户类型：1合约；2现货")
    private Long toType;

            /** 资产币种：如USDT */
        @TableField("asset")
        @Excel(name = "资产币种：如USDT")
    private String asset;

            /** 划转金额(正数) */
        @TableField("amount")
        @Excel(name = "划转金额(正数)")
    private BigDecimal amount;

            /** 状态：0处理中；1成功；2失败 */
        @TableField("status")
        @Excel(name = "状态：0处理中；1成功；2失败")
    private Long status;

            /** 失败原因(失败时记录) */
        @TableField("fail_reason")
        @Excel(name = "失败原因(失败时记录)")
    private String failReason;

            /** 关联总账流水ID(u_ledger.id，可选) */
        @TableField("ref_ledger_id")
        @Excel(name = "关联总账流水ID(u_ledger.id，可选)")
    private Long refLedgerId;

            /** 创建时间 */
        @TableField("create_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

            /** 更新时间 */
        @TableField("update_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

        }
