package com.hszn.nbmh.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.admin.api.entity.SysPost;
import com.hszn.nbmh.admin.mapper.SysPostMapper;
import com.hszn.nbmh.admin.service.ISysPostService;
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
 * 岗位信息表 服务实现类
 * </p>
 *
 * @author MCR
 * @since 2022-09-08
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPost> implements ISysPostService {
    @Resource
    private SysPostMapper sysPostMapper;

    @Override
    @Transactional
    public List<Integer> save(List<SysPost> sysPostList) {

        return sysPostList.stream().map(entity -> {

            Date createTime = new Date();
            entity.setCreateTime(createTime).setUpdateTime(createTime).setStatus(1);

            return sysPostMapper.insert(entity);
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int update(List<SysPost> sysPostList) {

        if (CollectionUtils.isEmpty(sysPostList)) {
            return 0;
        }

        return sysPostList.stream().map(entity -> {

            entity.setUpdateTime(new Date());

            return sysPostMapper.update(entity, Wrappers.<SysPost>lambdaUpdate().eq(SysPost::getId, entity.getId()));
        }).reduce(0, Integer::sum);
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<SysPost> query(SysPost entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {

        Page<SysPost> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<SysPost> lambdaQueryWrapper = Wrappers.lambdaQuery(entity);

        return sysPostMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysPost> list(SysPost entity, List<OrderItem> orderItemList) {

        QueryWrapper<SysPost> queryWrapper = Wrappers.query(entity);
        if (orderItemList != null && orderItemList.size() > 0) {
            orderItemList.forEach(t -> queryWrapper.orderBy(true, t.isAsc(), t.getColumn()));
        }

        return sysPostMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void delete(List<Long> idList) {

        idList.forEach(id -> {

            SysPost entity = this.getById(id);
            if (entity != null) {
                entity.setStatus(-1).setUpdateTime(new Date());
                sysPostMapper.updateById(entity);
            }
        });
    }

}
