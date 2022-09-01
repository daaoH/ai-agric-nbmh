package com.hszn.nbmh.prevent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.common.core.utils.BeanUtils;
import com.hszn.nbmh.prevent.api.entity.NbmhVideoLesson;
import com.hszn.nbmh.prevent.mapper.NbmhVideoLessonMapper;
import com.hszn.nbmh.prevent.service.INbmhVideoLessonService;
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
 * 视频课堂 服务实现类
 * </p>
 *
 * @author MCR
 * @since 2022-08-31
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhVideoLessonServiceImpl extends ServiceImpl<NbmhVideoLessonMapper, NbmhVideoLesson> implements INbmhVideoLessonService {
    @Resource
    private NbmhVideoLessonMapper nbmhVideoLessonMapper;

    @Override
    @Transactional
    public List<Integer> save(List<NbmhVideoLesson> nbmhVideoLessonList) {
        BeanUtils.validBean(nbmhVideoLessonList, NbmhVideoLesson.Save.class);

        return nbmhVideoLessonList.stream().map(entity -> {

            Date createTime = new Date();
            entity.setCreateTime(createTime).setUpdateTime(createTime).setStatus(1);

            return nbmhVideoLessonMapper.insert(entity);
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int update(List<NbmhVideoLesson> nbmhVideoLessonList) {
        BeanUtils.validBean(nbmhVideoLessonList, NbmhVideoLesson.Update.class);

        if (CollectionUtils.isEmpty(nbmhVideoLessonList)) {
            return 0;
        }

        return nbmhVideoLessonList.stream().map(entity -> {

            entity.setUpdateTime(new Date());

            return nbmhVideoLessonMapper.update(entity, Wrappers.<NbmhVideoLesson>lambdaUpdate().eq(NbmhVideoLesson::getId, entity.getId()));
        }).reduce(0, Integer::sum);
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<NbmhVideoLesson> query(NbmhVideoLesson entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {

        Page<NbmhVideoLesson> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<NbmhVideoLesson> lambdaQueryWrapper = Wrappers.lambdaQuery(entity);

        return nbmhVideoLessonMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NbmhVideoLesson> list(NbmhVideoLesson entity, List<OrderItem> orderItemList) {

        QueryWrapper<NbmhVideoLesson> queryWrapper = Wrappers.query(entity);
        if (orderItemList != null && orderItemList.size() > 0) {
            orderItemList.forEach(t -> queryWrapper.orderBy(true, t.isAsc(), t.getColumn()));
        }

        return nbmhVideoLessonMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void delete(List<Long> idList) {
        idList.forEach(id -> {

            NbmhVideoLesson entity = this.getById(id);
            if (entity != null) {
                entity.setStatus(-1).setUpdateTime(new Date());
                nbmhVideoLessonMapper.updateById(entity);
            }
        });
    }

}
