package com.hszn.nbmh.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.cms.api.entity.NbmhAd;
import com.hszn.nbmh.cms.mapper.NbmhAdMapper;
import com.hszn.nbmh.cms.service.INbmhAdService;
import com.hszn.nbmh.common.core.utils.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 广告表 服务实现类
 * </p>
 *
 * @author MCR
 * @since 2022-08-20
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhAdServiceImpl extends ServiceImpl<NbmhAdMapper, NbmhAd> implements INbmhAdService {
    
    @Resource
    private NbmhAdMapper nbmhAdMapper;

    @Override
    public List<Integer> save(List<NbmhAd> nbmhAdList) {
        BeanUtils.validBean(nbmhAdList, NbmhAd.Save.class);

        return nbmhAdList.stream().map(entity -> {

            Date createTime = new Date();
            entity.setCreateTime(createTime).setUpdateTime(createTime).setStatus(1);

            return nbmhAdMapper.insert(entity);
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int update(List<NbmhAd> nbmhAdList) {
        BeanUtils.validBean(nbmhAdList, NbmhAd.Update.class);

        if (nbmhAdList == null || nbmhAdList.size() == 0) {
            return 0;
        }

        return nbmhAdList.stream().map(entity -> {

            entity.setUpdateTime(new Date());

            return nbmhAdMapper.update(entity, Wrappers.<NbmhAd>lambdaUpdate().eq(NbmhAd::getId, entity.getId()));
        }).reduce(0, Integer::sum);
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<NbmhAd> query(NbmhAd entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {

        Page<NbmhAd> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<NbmhAd> lambdaQueryWrapper = Wrappers.lambdaQuery(entity);

        return nbmhAdMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NbmhAd> list(NbmhAd entity, List<OrderItem> orderItemList) {

        QueryWrapper<NbmhAd> queryWrapper = Wrappers.query(entity);
        if (orderItemList != null && orderItemList.size() > 0) {
            orderItemList.forEach(t -> queryWrapper.orderBy(true, t.isAsc(), t.getColumn()));
        }

        return nbmhAdMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void delete(List<Long> idList) {
        idList.forEach(id ->{

            NbmhAd entity = this.getById(id);
            if(entity != null){
                entity.setStatus(-1).setUpdateTime(new Date());
                nbmhAdMapper.updateById(entity);
            }
        });
    }

}
