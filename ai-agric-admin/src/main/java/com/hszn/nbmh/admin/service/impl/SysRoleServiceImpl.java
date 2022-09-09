package com.hszn.nbmh.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.admin.api.entity.SysRole;
import com.hszn.nbmh.admin.mapper.SysRoleMapper;
import com.hszn.nbmh.admin.service.ISysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author MCR
 * @since 2022-09-08
 */
@Slf4j
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    @Transactional
    public List<Integer> save(List<SysRole> sysRoleList) {

        return sysRoleList.stream().map(entity -> {

            Date createTime = new Date();
            entity.setCreateTime(createTime).setUpdateTime(createTime).setStatus(1);

            return sysRoleMapper.insert(entity);
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int update(List<SysRole> sysRoleList) {

        if (CollectionUtils.isEmpty(sysRoleList)) {
            return 0;
        }

        return sysRoleList.stream().map(entity -> {

            entity.setUpdateTime(new Date());

            return sysRoleMapper.update(entity, Wrappers.<SysRole>lambdaUpdate().eq(SysRole::getId, entity.getId()));
        }).reduce(0, Integer::sum);
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<SysRole> query(SysRole entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {

        Page<SysRole> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<SysRole> lambdaQueryWrapper = Wrappers.lambdaQuery(entity);

        return sysRoleMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysRole> list(SysRole entity, List<OrderItem> orderItemList) {

        QueryWrapper<SysRole> queryWrapper = Wrappers.query(entity);
        if (orderItemList != null && orderItemList.size() > 0) {
            orderItemList.forEach(t -> queryWrapper.orderBy(true, t.isAsc(), t.getColumn()));
        }

        return sysRoleMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void delete(List<Long> idList) {

        idList.forEach(id -> {

            SysRole entity = this.getById(id);
            if (entity != null) {
                entity.setStatus(-1).setUpdateTime(new Date());
                sysRoleMapper.updateById(entity);
            }
        });
    }

}
