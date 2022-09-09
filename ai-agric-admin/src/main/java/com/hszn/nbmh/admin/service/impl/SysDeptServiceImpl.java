package com.hszn.nbmh.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.admin.api.entity.SysDept;
import com.hszn.nbmh.admin.mapper.SysDeptMapper;
import com.hszn.nbmh.admin.service.ISysDeptService;
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
 * 部门管理 服务实现类
 * </p>
 *
 * @author MCR
 * @since 2022-09-08
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {
    @Resource
    private SysDeptMapper sysDeptMapper;

    @Override
    @Transactional
    public List<Integer> save(List<SysDept> sysDeptList) {

        return sysDeptList.stream().map(entity -> {

            Date createTime = new Date();
            entity.setCreateTime(createTime).setUpdateTime(createTime).setStatus(1);

            return sysDeptMapper.insert(entity);
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int update(List<SysDept> sysDeptList) {

        if (CollectionUtils.isEmpty(sysDeptList)) {
            return 0;
        }

        return sysDeptList.stream().map(entity -> {

            entity.setUpdateTime(new Date());

            return sysDeptMapper.update(entity, Wrappers.<SysDept>lambdaUpdate().eq(SysDept::getDeptId, entity.getDeptId()));
        }).reduce(0, Integer::sum);
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<SysDept> query(SysDept entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {

        Page<SysDept> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<SysDept> lambdaQueryWrapper = Wrappers.lambdaQuery(entity);

        return sysDeptMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysDept> list(SysDept entity, List<OrderItem> orderItemList) {

        QueryWrapper<SysDept> queryWrapper = Wrappers.query(entity);
        if (orderItemList != null && orderItemList.size() > 0) {
            orderItemList.forEach(t -> queryWrapper.orderBy(true, t.isAsc(), t.getColumn()));
        }

        return sysDeptMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void delete(List<Long> idList) {

        idList.forEach(id -> {

            SysDept entity = this.getById(id);
            if (entity != null) {
                entity.setStatus(-1).setUpdateTime(new Date());
                sysDeptMapper.updateById(entity);
            }
        });
    }

}
