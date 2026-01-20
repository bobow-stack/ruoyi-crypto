package com.ruoyi.app.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 公告 VO（用于接口入参/出参）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AnnouncementVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

        /** 公告ID(主键) */
    private Long id;

        /** 公告标题 */
    private String title;

        /** 公告内容(富文本/Markdown/HTML均可) */
    private String content;

        /** 状态：0发布；1下线/隐藏 */
    private Long status;

        /** 发布时间(发布时写入) */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;

        /** 创建时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

        /** 更新时间 */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

        /** 删除标志：0正常；2删除 */
    private String delFlag;

}
