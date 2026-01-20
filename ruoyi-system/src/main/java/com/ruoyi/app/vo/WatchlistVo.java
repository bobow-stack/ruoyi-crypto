package com.ruoyi.app.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户自选合约 VO（用于接口入参/出参）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WatchlistVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

        /** 自选记录ID(主键) */
    private Long id;

        /** 用户UID */
    private Long userId;

        /** 合约ID */
    private Long contractId;

        /** 创建时间(收藏时间) */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
