package com.hszn.nbmh.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.cms.api.entity.NbmhArticle;
import com.hszn.nbmh.cms.mapper.NbmhArticleMapper;
import com.hszn.nbmh.cms.service.INbmhArticleService;

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
 * 文章信息表 服务实现类
 * </p>
 *
 * @author MCR
 * @since 2022-08-20
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhArticleServiceImpl extends ServiceImpl<NbmhArticleMapper, NbmhArticle> implements INbmhArticleService {
    @Resource
    private NbmhArticleMapper nbmhArticleMapper;

    @Override
    public List<Integer> save(List<NbmhArticle> nbmhArticleList) {
        BeanUtils.validBean(nbmhArticleList, NbmhArticle.Save.class);

        return nbmhArticleList.stream().map(entity -> {

            Date createTime = new Date();
            entity.setCreateTime(createTime).setUpdateTime(createTime).setStatus(1);

            return nbmhArticleMapper.insert(entity);
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int update(List<NbmhArticle> nbmhArticleList) {
        BeanUtils.validBean(nbmhArticleList, NbmhArticle.Update.class);

        if (nbmhArticleList == null || nbmhArticleList.size() == 0) {
            return 0;
        }

        return nbmhArticleList.stream().map(entity -> {

            entity.setUpdateTime(new Date());

            return nbmhArticleMapper.update(entity, Wrappers.<NbmhArticle>lambdaUpdate().eq(NbmhArticle::getId, entity.getId()));
        }).reduce(0, Integer::sum);
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<NbmhArticle> query(NbmhArticle entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {

        Page<NbmhArticle> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<NbmhArticle> lambdaQueryWrapper = Wrappers.lambdaQuery(entity);

        return nbmhArticleMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NbmhArticle> list(NbmhArticle entity, List<OrderItem> orderItemList) {

        QueryWrapper<NbmhArticle> queryWrapper = Wrappers.query(entity);
        if (orderItemList != null && orderItemList.size() > 0) {
            orderItemList.forEach(t -> queryWrapper.orderBy(true, t.isAsc(), t.getColumn()));
        }

        return nbmhArticleMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void delete(List<Long> idList) {
        idList.forEach(id ->{

            NbmhArticle entity = this.getById(id);
            if(entity != null){
                entity.setStatus(-1).setUpdateTime(new Date());
                nbmhArticleMapper.updateById(entity);
            }
        });
    }

}
