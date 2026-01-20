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
 * 公告对象 u_announcement
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@TableName("u_announcement")
public class Announcement
        {
        private static final long serialVersionUID = 1L;

            /** 公告ID(主键) */
            @TableId(value = "id", type = IdType.INPUT)
    private Long id;

            /** 公告标题 */
        @TableField("title")
        @Excel(name = "公告标题")
    private String title;

            /** 公告内容(富文本/Markdown/HTML均可) */
        @TableField("content")
        @Excel(name = "公告内容(富文本/Markdown/HTML均可)")
    private String content;

            /** 状态：0发布；1下线/隐藏 */
        @TableField("status")
        @Excel(name = "状态：0发布；1下线/隐藏")
    private Long status;

            /** 发布时间(发布时写入) */
        @TableField("publish_time")
        @Excel(name = "发布时间(发布时写入)")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;

            /** 创建时间 */
        @TableField("create_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

            /** 更新时间 */
        @TableField("update_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

            /** 删除标志：0正常；2删除 */
        @TableField("del_flag")
    private String delFlag;

        }
