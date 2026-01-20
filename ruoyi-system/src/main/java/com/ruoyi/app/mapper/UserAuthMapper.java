package com.ruoyi.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.app.domain.UserAuth;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登录认证(账号与密码) Mapper 接口（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Mapper
public interface UserAuthMapper extends BaseMapper<UserAuth> {
}
