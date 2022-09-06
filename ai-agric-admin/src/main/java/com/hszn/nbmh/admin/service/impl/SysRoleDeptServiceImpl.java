package com.hszn.nbmh.admin.service.impl;

import com.hszn.nbmh.admin.api.entity.SysRoleDept;
import com.hszn.nbmh.admin.mapper.SysRoleDeptMapper;
import com.hszn.nbmh.admin.service.ISysRoleDeptService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * <p>
 * 角色与部门对应关系 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-09-06
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleDeptServiceImpl extends ServiceImpl<SysRoleDeptMapper, SysRoleDept> implements ISysRoleDeptService {
    @Resource
    private SysRoleDeptMapper sysRoleDeptMapper;



}
