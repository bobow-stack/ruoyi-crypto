package com.ruoyi.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.app.domain.Transfer;
import com.ruoyi.app.mapper.TransferMapper;
import com.ruoyi.app.service.ITransferService;
import org.springframework.stereotype.Service;

/**
 * 账户划转记录(为后续现货预留) ServiceImpl（MyBatis-Plus）
 *
 * @author ruoyi
 * @date 2026-01-17
 */
@Service
public class TransferServiceImpl extends ServiceImpl<TransferMapper, Transfer> implements ITransferService {
}
