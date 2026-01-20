package com.ruoyi.app.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 余额(强一致更新；配套总账流水) VO（用于接口入参/出参）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BalanceVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

        /** 余额记录ID */
    private Long id;

        /** 用户UID */
    private Long userId;

        /** 账户类型：1合约(本期)；2现货(预留) */
    private Long accountType;

        /** 资产币种：如USDT */
    private String asset;

        /** 可用余额(可下单/可转出) */
    private BigDecimal available;

        /** 冻结余额(挂单冻结/提现冻结等) */
    private BigDecimal frozen;

        /** 更新时间(余额变动时间) */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
