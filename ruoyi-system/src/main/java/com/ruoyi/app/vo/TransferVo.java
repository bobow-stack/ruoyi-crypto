package com.ruoyi.app.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 账户划转记录(为后续现货预留) VO（用于接口入参/出参）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TransferVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

        /** 划转记录ID */
    private Long id;

        /** 用户UID */
    private Long userId;

        /** 转出账户类型：1合约；2现货 */
    private Long fromType;

        /** 转入账户类型：1合约；2现货 */
    private Long toType;

        /** 资产币种：如USDT */
    private String asset;

        /** 划转金额(正数) */
    private BigDecimal amount;

        /** 状态：0处理中；1成功；2失败 */
    private Long status;

        /** 失败原因(失败时记录) */
    private String failReason;

        /** 关联总账流水ID(u_ledger.id，可选) */
    private Long refLedgerId;

        /** 创建时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

        /** 更新时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
