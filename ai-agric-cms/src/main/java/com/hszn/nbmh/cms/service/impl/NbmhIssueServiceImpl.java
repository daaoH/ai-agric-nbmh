package com.hszn.nbmh.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.cms.api.entity.NbmhIssue;
import com.hszn.nbmh.cms.mapper.NbmhIssueMapper;
import com.hszn.nbmh.cms.service.INbmhIssueService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 常见问题表 服务实现类
 * </p>
 *
 * @author pyq
 * @since 2022-09-05
 */
@Slf4j
@Service
@Transactional(rollbackFor=Exception.class)
public class NbmhIssueServiceImpl extends ServiceImpl<NbmhIssueMapper, NbmhIssue> implements INbmhIssueService {
    @Resource
    private NbmhIssueMapper nbmhIssueMapper;


    @Override
    public List<Integer> save(List<NbmhIssue> nbmhIssueList) {


        return nbmhIssueList.stream().map(entity -> {

            Date createTime=new Date();
            entity.setCreateTime(createTime).setUpdateTime(createTime).setStatus(0);

            return nbmhIssueMapper.insert(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public int update(List<NbmhIssue> nbmhIssueList) {

        if (nbmhIssueList == null || nbmhIssueList.size() == 0) {
            return 0;
        }

        return nbmhIssueList.stream().map(entity -> {

            entity.setUpdateTime(new Date());

            return nbmhIssueMapper.update(entity, Wrappers.<NbmhIssue>lambdaUpdate().eq(NbmhIssue::getId, entity.getId()));
        }).reduce(0, Integer::sum);
    }

    @Override
    public IPage<NbmhIssue> query(NbmhIssue entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {
        Page<NbmhIssue> page=new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<NbmhIssue> lambdaQueryWrapper=Wrappers.lambdaQuery(entity);

        return nbmhIssueMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    @Transactional(readOnly=true)
    public List<NbmhIssue> list(NbmhIssue entity, List<OrderItem> orderItemList) {
        QueryWrapper<NbmhIssue> queryWrapper=Wrappers.query(entity);
        if (orderItemList != null && orderItemList.size() > 0) {
            orderItemList.forEach(t -> queryWrapper.orderBy(true, t.isAsc(), t.getColumn()));
        }

        return nbmhIssueMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void delete(List<Long> idList) {
        idList.forEach(id -> {

            NbmhIssue entity=this.getById(id);
            if (entity != null) {
                entity.setStatus(-1).setUpdateTime(new Date());
                nbmhIssueMapper.updateById(entity);
            }
        });
    }
}
