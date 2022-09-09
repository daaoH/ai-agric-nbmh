package com.hszn.nbmh.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.admin.api.entity.SysUserRole;
import com.hszn.nbmh.admin.mapper.SysUserRoleMapper;
import com.hszn.nbmh.admin.service.ISysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-09-06
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    @Transactional
    public List<Integer> save(List<SysUserRole> sysUserRoleList) {

        return sysUserRoleList.stream().map(sysUserRoleMapper::insert).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<SysUserRole> query(SysUserRole entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {

        Page<SysUserRole> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<SysUserRole> lambdaQueryWrapper = Wrappers.lambdaQuery(entity);

        return sysUserRoleMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysUserRole> list(SysUserRole entity, List<OrderItem> orderItemList) {

        QueryWrapper<SysUserRole> queryWrapper = Wrappers.query(entity);
        if (orderItemList != null && orderItemList.size() > 0) {
            orderItemList.forEach(t -> queryWrapper.orderBy(true, t.isAsc(), t.getColumn()));
        }

        return sysUserRoleMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void delete(List<SysUserRole> sysRoleList) {

        sysRoleList.forEach(entity -> sysUserRoleMapper.delete(Wrappers.query(entity)));
    }

}
