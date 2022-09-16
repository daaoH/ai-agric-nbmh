package com.hszn.nbmh.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.cms.api.entity.NbmhFeedback;
import com.hszn.nbmh.cms.mapper.NbmhFeedbackMapper;
import com.hszn.nbmh.cms.service.INbmhFeedbackService;

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
 * 意见反馈表 服务实现类
 * </p>
 *
 * @author pyq
 * @since 2022-09-03
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhFeedbackServiceImpl extends ServiceImpl<NbmhFeedbackMapper, NbmhFeedback> implements INbmhFeedbackService {
    @Resource
    private NbmhFeedbackMapper nbmhFeedbackMapper;

    @Override
    public List<Integer> save(List<NbmhFeedback> nbmhFeedbackList) {

        return nbmhFeedbackList.stream().map(entity -> {

            Date createTime = new Date();
            entity.setCreateTime(createTime).setUpdateTime(createTime).setStatus(1);

            return nbmhFeedbackMapper.insert(entity);
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int update(List<NbmhFeedback> nbmhFeedbackList) {

        if (nbmhFeedbackList == null || nbmhFeedbackList.size() == 0) {
            return 0;
        }

        return nbmhFeedbackList.stream().map(entity -> {

            entity.setUpdateTime(new Date());

            return nbmhFeedbackMapper.update(entity, Wrappers.<NbmhFeedback>lambdaUpdate().eq(NbmhFeedback::getId, entity.getId()));
        }).reduce(0, Integer::sum);
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<NbmhFeedback> query(NbmhFeedback entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {

        Page<NbmhFeedback> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<NbmhFeedback> lambdaQueryWrapper = Wrappers.lambdaQuery(entity);

        return nbmhFeedbackMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NbmhFeedback> list(NbmhFeedback entity, List<OrderItem> orderItemList) {

        QueryWrapper<NbmhFeedback> queryWrapper = Wrappers.query(entity);
        if (orderItemList != null && orderItemList.size() > 0) {
            orderItemList.forEach(t -> queryWrapper.orderBy(true, t.isAsc(), t.getColumn()));
        }

        return nbmhFeedbackMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void delete(List<Long> idList) {
        idList.forEach(id ->{

            NbmhFeedback entity = this.getById(id);
            if(entity != null){
                entity.setStatus(-1).setUpdateTime(new Date());
                nbmhFeedbackMapper.updateById(entity);
            }
        });
    }

}
