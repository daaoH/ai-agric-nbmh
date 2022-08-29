package com.hszn.nbmh.user.service.impl;

import com.hszn.nbmh.user.api.entity.SysPost;
import com.hszn.nbmh.user.mapper.SysPostMapper;
import com.hszn.nbmh.user.service.ISysPostService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * <p>
 * 岗位信息表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-08-16
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPost> implements ISysPostService {
    @Resource
    private SysPostMapper sysPostMapper;



}
