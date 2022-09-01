package com.hszn.nbmh.prevent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.common.core.utils.BeanUtils;
import com.hszn.nbmh.prevent.api.entity.NbmhAnimalLibrary;
import com.hszn.nbmh.prevent.mapper.NbmhAnimalLibraryMapper;
import com.hszn.nbmh.prevent.service.INbmhAnimalLibraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 动物基因库/病例库 服务实现类
 * </p>
 *
 * @author MCR
 * @since 2022-08-31
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhAnimalLibraryServiceImpl extends ServiceImpl<NbmhAnimalLibraryMapper, NbmhAnimalLibrary> implements INbmhAnimalLibraryService {
    @Resource
    private NbmhAnimalLibraryMapper nbmhAnimalLibraryMapper;

    @Override
    @Transactional
    public List<Integer> save(List<NbmhAnimalLibrary> nbmhAnimalLibraryList) {
        BeanUtils.validBean(nbmhAnimalLibraryList, NbmhAnimalLibrary.Save.class);

        return nbmhAnimalLibraryList.stream().map(entity -> {

            Date createTime = new Date();
            entity.setCreateTime(createTime).setUpdateTime(createTime).setStatus(1);

            return nbmhAnimalLibraryMapper.insert(entity);
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int update(List<NbmhAnimalLibrary> nbmhAnimalLibraryList) {
        BeanUtils.validBean(nbmhAnimalLibraryList, NbmhAnimalLibrary.Update.class);

        if (CollectionUtils.isEmpty(nbmhAnimalLibraryList)) {
            return 0;
        }

        return nbmhAnimalLibraryList.stream().map(entity -> {

            entity.setUpdateTime(new Date());

            return nbmhAnimalLibraryMapper.update(entity, Wrappers.<NbmhAnimalLibrary>lambdaUpdate().eq(NbmhAnimalLibrary::getId, entity.getId()));
        }).reduce(0, Integer::sum);
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<NbmhAnimalLibrary> query(NbmhAnimalLibrary entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {

        Page<NbmhAnimalLibrary> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<NbmhAnimalLibrary> lambdaQueryWrapper = Wrappers.lambdaQuery(entity);

        return nbmhAnimalLibraryMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NbmhAnimalLibrary> list(NbmhAnimalLibrary entity, List<OrderItem> orderItemList) {

        QueryWrapper<NbmhAnimalLibrary> queryWrapper = Wrappers.query(entity);
        if (orderItemList != null && orderItemList.size() > 0) {
            orderItemList.forEach(t -> queryWrapper.orderBy(true, t.isAsc(), t.getColumn()));
        }

        return nbmhAnimalLibraryMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void delete(List<Long> idList) {
        idList.forEach(id -> {

            NbmhAnimalLibrary entity = this.getById(id);
            if (entity != null) {
                entity.setStatus(-1).setUpdateTime(new Date());
                nbmhAnimalLibraryMapper.updateById(entity);
            }
        });
    }

}
