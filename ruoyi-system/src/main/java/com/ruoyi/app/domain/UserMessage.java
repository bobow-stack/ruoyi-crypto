package com.ruoyi.app.domain;

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
 * 站内信/通知对象 u_user_message
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@TableName("u_user_message")
public class UserMessage
        {
        private static final long serialVersionUID = 1L;

            /** 站内信ID(主键) */
            @TableId(value = "id", type = IdType.INPUT)
    private Long id;

            /** 用户UID */
        @TableField("user_id")
        @Excel(name = "用户UID")
    private Long userId;

            /** 消息类型：TRADE(成交)/FUNDING(资金费率)/RISK(风控)/SYSTEM(系统) */
        @TableField("msg_type")
        @Excel(name = "消息类型：TRADE(成交)/FUNDING(资金费率)/RISK(风控)/SYSTEM(系统)")
    private String msgType;

            /** 消息标题 */
        @TableField("title")
        @Excel(name = "消息标题")
    private String title;

            /** 消息内容(短文本；如需长文本可改TEXT) */
        @TableField("content")
        @Excel(name = "消息内容(短文本；如需长文本可改TEXT)")
    private String content;

            /** 是否已读：0未读；1已读 */
        @TableField("read_flag")
        @Excel(name = "是否已读：0未读；1已读")
    private Long readFlag;

            /** 关联对象类型：ORDER/TRADE/LIQUIDATION/LEDGER等(可选) */
        @TableField("ref_type")
        @Excel(name = "关联对象类型：ORDER/TRADE/LIQUIDATION/LEDGER等(可选)")
    private String refType;

            /** 关联对象ID(可选) */
        @TableField("ref_id")
        @Excel(name = "关联对象ID(可选)")
    private Long refId;

            /** 创建时间(发送时间) */
        @TableField("create_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

        }
