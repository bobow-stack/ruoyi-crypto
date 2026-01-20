package com.ruoyi.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.app.domain.Announcement;
import com.ruoyi.app.mapper.AnnouncementMapper;
import com.ruoyi.app.service.IAnnouncementService;
import org.springframework.stereotype.Service;

/**
 * 公告 ServiceImpl（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements IAnnouncementService {
}
