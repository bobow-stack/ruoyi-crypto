package com.ruoyi.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.app.domain.UserContractSetting;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户-合约维度设置(杠杆/模式/档位) Mapper 接口（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Mapper
public interface UserContractSettingMapper extends BaseMapper<UserContractSetting> {
}
