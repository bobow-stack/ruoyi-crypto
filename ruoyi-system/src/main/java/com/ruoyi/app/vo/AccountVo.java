package com.ruoyi.app.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 账户(按账户类型分合约/现货) VO（用于接口入参/出参）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

        /** 账户ID */
    private Long id;

        /** 用户UID */
    private Long userId;

        /** 账户类型：1合约；2现货(预留) */
    private Long accountType;

        /** 账户状态：0正常；1冻结/禁用 */
    private Long status;

        /** 删除标志：0正常；2删除 */
    private String delFlag;

        /** 创建时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

        /** 更新时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
