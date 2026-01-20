package com.ruoyi.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.app.domain.FeeConfig;
import com.ruoyi.app.mapper.FeeConfigMapper;
import com.ruoyi.app.service.IFeeConfigService;
import org.springframework.stereotype.Service;

/**
 * 手续费配置(支持全局/合约/VIP维度覆盖) ServiceImpl（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Service
public class FeeConfigServiceImpl extends ServiceImpl<FeeConfigMapper, FeeConfig> implements IFeeConfigService {
}
