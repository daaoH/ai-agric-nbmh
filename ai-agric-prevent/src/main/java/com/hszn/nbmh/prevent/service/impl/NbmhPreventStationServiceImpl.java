package com.hszn.nbmh.prevent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.common.core.utils.BeanUtils;
import com.hszn.nbmh.prevent.api.entity.NbmhPreventStation;
import com.hszn.nbmh.prevent.mapper.NbmhPreventStationMapper;
import com.hszn.nbmh.prevent.service.INbmhPreventStationService;
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
 * 防疫站信息表 服务实现类
 * </p>
 *
 * @author MCR
 * @since 2022-08-15
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhPreventStationServiceImpl extends ServiceImpl<NbmhPreventStationMapper, NbmhPreventStation> implements INbmhPreventStationService {
    @Resource
    private NbmhPreventStationMapper nbmhPreventStationMapper;

    @Override
    @Transactional
    public List<Integer> save(List<NbmhPreventStation> nbmhPreventStationList) {
        BeanUtils.validBean(nbmhPreventStationList, NbmhPreventStation.Save.class);

        return nbmhPreventStationList.stream().map(entity -> {

            Date createTime = new Date();
            entity.setCreateTime(createTime).setUpdateTime(createTime).setStatus(1);

            return nbmhPreventStationMapper.insert(entity);
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int update(List<NbmhPreventStation> nbmhPreventStationList) {
        BeanUtils.validBean(nbmhPreventStationList, NbmhPreventStation.Update.class);

        if (CollectionUtils.isEmpty(nbmhPreventStationList)) {
            return 0;
        }

        return nbmhPreventStationList.stream().map(entity -> {

            entity.setUpdateTime(new Date());

            return nbmhPreventStationMapper.update(entity, Wrappers.<NbmhPreventStation>lambdaUpdate().eq(NbmhPreventStation::getId, entity.getId()));
        }).reduce(0, Integer::sum);
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<NbmhPreventStation> query(NbmhPreventStation entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {

        Page<NbmhPreventStation> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<NbmhPreventStation> lambdaQueryWrapper = Wrappers.lambdaQuery(entity);

        return nbmhPreventStationMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NbmhPreventStation> list(NbmhPreventStation entity, List<OrderItem> orderItemList) {

        QueryWrapper<NbmhPreventStation> queryWrapper = Wrappers.query(entity);
        if (orderItemList != null && orderItemList.size() > 0) {
            orderItemList.forEach(t -> queryWrapper.orderBy(true, t.isAsc(), t.getColumn()));
        }

        return nbmhPreventStationMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void delete(List<Long> idList) {
        idList.forEach(id -> {

            NbmhPreventStation entity = this.getById(id);
            if (entity != null) {
                entity.setStatus(-1).setUpdateTime(new Date());
                nbmhPreventStationMapper.updateById(entity);
            }
        });
    }

}
