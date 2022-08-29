package com.hszn.nbmh.user.service.impl;

import com.hszn.nbmh.user.api.entity.SysLog;
import com.hszn.nbmh.user.mapper.SysLogMapper;
import com.hszn.nbmh.user.service.ISysLogService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-08-17
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {
    @Resource
    private SysLogMapper sysLogMapper;



}
