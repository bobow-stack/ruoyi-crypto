package com.ruoyi.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.app.domain.UserContractSetting;
import com.ruoyi.app.mapper.UserContractSettingMapper;
import com.ruoyi.app.service.IUserContractSettingService;
import org.springframework.stereotype.Service;

/**
 * 用户-合约维度设置(杠杆/模式/档位) ServiceImpl（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Service
public class UserContractSettingServiceImpl extends ServiceImpl<UserContractSettingMapper, UserContractSetting> implements IUserContractSettingService {
}
