package com.hszn.nbmh.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.cms.api.entity.NbmhArticleType;
import com.hszn.nbmh.cms.mapper.NbmhArticleTypeMapper;
import com.hszn.nbmh.cms.service.INbmhArticleTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章类型表 服务实现类
 * </p>
 *
 * @author MCR
 * @since 2022-08-20
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhArticleTypeServiceImpl extends ServiceImpl<NbmhArticleTypeMapper, NbmhArticleType> implements INbmhArticleTypeService {

    @Resource
    private NbmhArticleTypeMapper nbmhArticleTypeMapper;

    @Override
    public List<Integer> save(List<NbmhArticleType> nbmhArticleTypeList) {

        return nbmhArticleTypeList.stream().map(entity -> {

            Date createTime = new Date();
            entity.setCreateTime(createTime).setUpdateTime(createTime).setStatus(1);

            return nbmhArticleTypeMapper.insert(entity);
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public NbmhArticleType getByTypeId(Long typeId) {

        return nbmhArticleTypeMapper.selectOne(Wrappers.query(NbmhArticleType.builder().typeId(typeId).build()));
    }

    @Override
    @Transactional
    public int update(List<NbmhArticleType> nbmhArticleTypeList) {

        if (nbmhArticleTypeList == null || nbmhArticleTypeList.size() == 0) {
            return 0;
        }

        return nbmhArticleTypeList.stream().map(entity -> {

            entity.setUpdateTime(new Date());

            return nbmhArticleTypeMapper.update(entity, Wrappers.<NbmhArticleType>lambdaUpdate().eq(NbmhArticleType::getTypeId, entity.getTypeId()));
        }).reduce(0, Integer::sum);
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<NbmhArticleType> query(NbmhArticleType entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {

        Page<NbmhArticleType> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<NbmhArticleType> lambdaQueryWrapper = Wrappers.lambdaQuery(entity);

        return nbmhArticleTypeMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NbmhArticleType> list(NbmhArticleType entity, List<OrderItem> orderItemList) {

        QueryWrapper<NbmhArticleType> queryWrapper = Wrappers.query(entity);
        if (orderItemList != null && orderItemList.size() > 0) {
            orderItemList.forEach(t -> queryWrapper.orderBy(true, t.isAsc(), t.getColumn()));
        }

        return nbmhArticleTypeMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void delete(List<Long> idList) {
        idList.forEach(id -> {

            NbmhArticleType entity = nbmhArticleTypeMapper.selectOne(Wrappers.query(NbmhArticleType.builder().typeId(id).build()));
            if (entity != null) {
                entity.setStatus(-1).setUpdateTime(new Date());
                nbmhArticleTypeMapper.update(entity, Wrappers.<NbmhArticleType>lambdaUpdate().eq(NbmhArticleType::getTypeId, entity.getTypeId()));
            }
        });
    }

}
