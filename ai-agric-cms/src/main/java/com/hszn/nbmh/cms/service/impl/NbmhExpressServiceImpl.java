package com.hszn.nbmh.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.cms.api.entity.NbmhAppealRecord;
import com.hszn.nbmh.cms.api.entity.NbmhExpress;
import com.hszn.nbmh.cms.mapper.NbmhExpressMapper;
import com.hszn.nbmh.cms.service.INbmhExpressService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.common.core.utils.BeanUtils;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 快递公司表 服务实现类
 * </p>
 *
 * @author pyq
 * @since 2022-09-13
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhExpressServiceImpl extends ServiceImpl<NbmhExpressMapper, NbmhExpress> implements INbmhExpressService {
    @Resource
    private NbmhExpressMapper nbmhExpressMapper;

    @Override
    public List<Integer> save(List<NbmhExpress> nbmhExpressList) {
        BeanUtils.validBean(nbmhExpressList, NbmhExpress.Save.class);

        return nbmhExpressList.stream().map(entity -> {

            Date createTime = new Date();
            entity.setCreateTime(createTime).setUpdateTime(createTime).setStatus(1);

            return nbmhExpressMapper.insert(entity);
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int update(List<NbmhExpress> nbmhExpressList) {
        BeanUtils.validBean(nbmhExpressList, NbmhExpress.Update.class);

        if (nbmhExpressList == null || nbmhExpressList.size() == 0) {
            return 0;
        }

        return nbmhExpressList.stream().map(entity -> {

            entity.setUpdateTime(new Date());

            return nbmhExpressMapper.update(entity, Wrappers.<NbmhExpress>lambdaUpdate().eq(NbmhExpress::getId, entity.getId()));
        }).reduce(0, Integer::sum);
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<NbmhExpress> query(NbmhExpress entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {

        Page<NbmhExpress> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<NbmhExpress> lambdaQueryWrapper = Wrappers.lambdaQuery(entity);

        return nbmhExpressMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NbmhExpress> list(NbmhExpress entity, List<OrderItem> orderItemList) {

        QueryWrapper<NbmhExpress> queryWrapper = Wrappers.query(entity);
        if (orderItemList != null && orderItemList.size() > 0) {
            orderItemList.forEach(t -> queryWrapper.orderBy(true, t.isAsc(), t.getColumn()));
        }

        return nbmhExpressMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void delete(List<Long> idList) {
        idList.forEach(id -> {

            NbmhExpress entity = this.getById(id);
            if (entity != null) {
                entity.setStatus(-1).setUpdateTime(new Date());
                nbmhExpressMapper.updateById(entity);
            }
        });
    }

}


