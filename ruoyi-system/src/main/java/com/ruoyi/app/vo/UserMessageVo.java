package com.ruoyi.app.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 站内信/通知 VO（用于接口入参/出参）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserMessageVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

        /** 站内信ID(主键) */
    private Long id;

        /** 用户UID */
    private Long userId;

        /** 消息类型：TRADE(成交)/FUNDING(资金费率)/RISK(风控)/SYSTEM(系统) */
    private String msgType;

        /** 消息标题 */
    private String title;

        /** 消息内容(短文本；如需长文本可改TEXT) */
    private String content;

        /** 是否已读：0未读；1已读 */
    private Long readFlag;

        /** 关联对象类型：ORDER/TRADE/LIQUIDATION/LEDGER等(可选) */
    private String refType;

        /** 关联对象ID(可选) */
    private Long refId;

        /** 创建时间(发送时间) */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
