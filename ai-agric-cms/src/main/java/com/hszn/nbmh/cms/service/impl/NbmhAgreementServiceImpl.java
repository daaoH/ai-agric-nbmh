package com.hszn.nbmh.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.cms.api.entity.NbmhAgreement;
import com.hszn.nbmh.cms.mapper.NbmhAgreementMapper;
import com.hszn.nbmh.cms.service.INbmhAgreementService;

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
 * 用户协议表 服务实现类
 * </p>
 *
 * @author MCR
 * @since 2022-08-20
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhAgreementServiceImpl extends ServiceImpl<NbmhAgreementMapper, NbmhAgreement> implements INbmhAgreementService {
    @Resource
    private NbmhAgreementMapper nbmhAgreementMapper;

    @Override
    public List<Integer> save(List<NbmhAgreement> nbmhAgreementList) {
        BeanUtils.validBean(nbmhAgreementList, NbmhAgreement.Save.class);

        return nbmhAgreementList.stream().map(entity -> {

            Date createTime = new Date();
            entity.setCreateTime(createTime).setUpdateTime(createTime).setStatus(1);

            return nbmhAgreementMapper.insert(entity);
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int update(List<NbmhAgreement> nbmhAgreementList) {
        BeanUtils.validBean(nbmhAgreementList, NbmhAgreement.Update.class);

        if (nbmhAgreementList == null || nbmhAgreementList.size() == 0) {
            return 0;
        }

        return nbmhAgreementList.stream().map(entity -> {

            entity.setUpdateTime(new Date());

            return nbmhAgreementMapper.update(entity, Wrappers.<NbmhAgreement>lambdaUpdate().eq(NbmhAgreement::getId, entity.getId()));
        }).reduce(0, Integer::sum);
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<NbmhAgreement> query(NbmhAgreement entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {

        Page<NbmhAgreement> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<NbmhAgreement> lambdaQueryWrapper = Wrappers.lambdaQuery(entity);

        return nbmhAgreementMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NbmhAgreement> list(NbmhAgreement entity, List<OrderItem> orderItemList) {

        QueryWrapper<NbmhAgreement> queryWrapper = Wrappers.query(entity);
        if (orderItemList != null && orderItemList.size() > 0) {
            orderItemList.forEach(t -> queryWrapper.orderBy(true, t.isAsc(), t.getColumn()));
        }

        return nbmhAgreementMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void delete(List<Long> idList) {
        idList.forEach(id ->{

            NbmhAgreement entity = this.getById(id);
            if(entity != null){
                entity.setStatus(-1).setUpdateTime(new Date());
                nbmhAgreementMapper.updateById(entity);
            }
        });
    }

}
