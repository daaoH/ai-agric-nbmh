package com.hszn.nbmh.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.user.api.entity.NbmhAnimalDoctorDetail;
import com.hszn.nbmh.user.api.entity.NbmhUser;
import com.hszn.nbmh.user.api.entity.NbmhUserExtraInfo;
import com.hszn.nbmh.user.api.entity.NbmhUserFollowExpert;
import com.hszn.nbmh.user.api.params.out.NbmhUserFollowExpertInfo;
import com.hszn.nbmh.user.mapper.NbmhAnimalDoctorDetailMapper;
import com.hszn.nbmh.user.mapper.NbmhUserExtraInfoMapper;
import com.hszn.nbmh.user.mapper.NbmhUserFollowExpertMapper;
import com.hszn.nbmh.user.mapper.NbmhUserMapper;
import com.hszn.nbmh.user.service.INbmhUserFollowExpertService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 关注的专家记录表 服务实现类
 * </p>
 *
 * @author MCR
 * @since 2022-08-31
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhUserFollowExpertServiceImpl extends ServiceImpl<NbmhUserFollowExpertMapper, NbmhUserFollowExpert> implements INbmhUserFollowExpertService {

    @Resource
    private NbmhUserFollowExpertMapper nbmhUserFollowExpertMapper;

    @Resource
    private NbmhUserMapper nbmhUserMapper;

    @Resource
    private NbmhUserExtraInfoMapper nbmhUserExtraInfoMapper;

    @Resource
    private NbmhAnimalDoctorDetailMapper nbmhAnimalDoctorDetailMapper;

    @Override
    @Transactional
    public List<Integer> save(List<NbmhUserFollowExpert> nbmhUserFollowExpertList) {

        return nbmhUserFollowExpertList.stream().map(entity -> {

            Date createTime = new Date();
            entity.setCreateTime(createTime).setUpdateTime(createTime).setStatus(1);

            return nbmhUserFollowExpertMapper.insert(entity);
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int update(List<NbmhUserFollowExpert> nbmhUserFollowExpertList) {

        if (nbmhUserFollowExpertList == null || nbmhUserFollowExpertList.size() == 0) {
            return 0;
        }

        return nbmhUserFollowExpertList.stream().map(entity -> {

            entity.setUpdateTime(new Date());

            return nbmhUserFollowExpertMapper.update(entity, Wrappers.<NbmhUserFollowExpert>lambdaUpdate().eq(NbmhUserFollowExpert::getId, entity.getId()));
        }).reduce(0, Integer::sum);
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<NbmhUserFollowExpert> query(NbmhUserFollowExpert entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {

        Page<NbmhUserFollowExpert> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<NbmhUserFollowExpert> lambdaQueryWrapper = Wrappers.lambdaQuery(entity);

        return nbmhUserFollowExpertMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NbmhUserFollowExpertInfo> list(NbmhUserFollowExpert entity, List<OrderItem> orderItemList) {

        QueryWrapper<NbmhUserFollowExpert> queryWrapper = Wrappers.query(entity);
        if (orderItemList != null && orderItemList.size() > 0) {
            orderItemList.forEach(t -> queryWrapper.orderBy(true, t.isAsc(), t.getColumn()));
        }

        List<NbmhUserFollowExpert> userFollowExpertList = nbmhUserFollowExpertMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(userFollowExpertList)) {
            return null;
        }

        List<NbmhUserFollowExpertInfo> userFollowExpertInfoList = new ArrayList<>();
        for (NbmhUserFollowExpert item : userFollowExpertList) {

            NbmhUser nbmhUser = nbmhUserMapper.selectOne(Wrappers.query(NbmhUser.builder().id(item.getExpertId()).status(0).build()));
            if (ObjectUtils.isEmpty(nbmhUser)) {
                continue;
            }

            NbmhUserExtraInfo userExtraInfo = nbmhUserExtraInfoMapper.selectOne(Wrappers.query(NbmhUserExtraInfo.builder().userId(item.getExpertId()).type(2).status(0).build()));
            if (ObjectUtils.isEmpty(userExtraInfo)) {
                continue;
            }

            NbmhAnimalDoctorDetail doctorDetail = nbmhAnimalDoctorDetailMapper.selectOne(Wrappers.query(NbmhAnimalDoctorDetail.builder().userId(item.getExpertId()).status(0).build()));
            if (ObjectUtils.isEmpty(doctorDetail)) {
                continue;
            }

            userFollowExpertInfoList.add(NbmhUserFollowExpertInfo.builder().userId(item.getUserId()).userName(item.getUserName()).expertId(item.getExpertId()).expertName(item.getExpertName()).expertAvatar(nbmhUser.getAvatarUrl())
                    .doctorType(doctorDetail.getDoctorType()).admissions(doctorDetail.getAcceptOrderNum()).workYear(userExtraInfo.getWorkYear()).goodAnimalType(doctorDetail.getGoodAnimalType()).build());
        }

        return userFollowExpertInfoList;
    }

    @Override
    @Transactional
    public void delete(List<Long> idList) {
        idList.forEach(id -> {

            NbmhUserFollowExpert entity = this.getById(id);
            if (entity != null) {
                entity.setStatus(-1).setUpdateTime(new Date());
                nbmhUserFollowExpertMapper.updateById(entity);
            }
        });
    }

}
