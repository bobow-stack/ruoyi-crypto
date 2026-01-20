package com.ruoyi.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.app.domain.Position;
import com.ruoyi.app.mapper.PositionMapper;
import com.ruoyi.app.service.IPositionService;
import org.springframework.stereotype.Service;

/**
 * 当前持仓(一用户一合约一方向一条记录) ServiceImpl（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements IPositionService {
}
