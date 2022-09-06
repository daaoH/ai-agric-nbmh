package com.hszn.nbmh.admin.service.impl;

import com.hszn.nbmh.admin.api.entity.SysUserPost;
import com.hszn.nbmh.admin.mapper.SysUserPostMapper;
import com.hszn.nbmh.admin.service.ISysUserPostService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * <p>
 * 用户与岗位关联表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-09-06
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserPostServiceImpl extends ServiceImpl<SysUserPostMapper, SysUserPost> implements ISysUserPostService {
    @Resource
    private SysUserPostMapper sysUserPostMapper;



}
