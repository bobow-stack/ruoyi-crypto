package com.ruoyi.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.app.domain.UserSession;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户会话(登录态/设备) Mapper 接口（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Mapper
public interface UserSessionMapper extends BaseMapper<UserSession> {
}
