package com.hszn.nbmh.prevent.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.common.core.exception.ServiceException;
import com.hszn.nbmh.common.core.utils.BeanUtils;
import com.hszn.nbmh.prevent.api.entity.AnimalJsonEntity;
import com.hszn.nbmh.prevent.api.entity.NbmhAnimal;
import com.hszn.nbmh.prevent.api.entity.NbmhFarm;
import com.hszn.nbmh.prevent.api.params.out.NbmhFarmResult;
import com.hszn.nbmh.prevent.mapper.NbmhFarmMapper;
import com.hszn.nbmh.prevent.service.INbmhFarmService;
import com.hszn.nbmh.user.api.entity.NbmhUser;
import com.hszn.nbmh.user.api.feign.RemoteUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 养殖场信息表 服务实现类
 * </p>
 *
 * @author MCR
 * @since 2022-08-16
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhFarmServiceImpl extends ServiceImpl<NbmhFarmMapper, NbmhFarm> implements INbmhFarmService {
    @Resource
    private NbmhFarmMapper nbmhFarmMapper;

    @Resource
    private RemoteUserService remoteUserService;

    @Override
    @Transactional
    public List<Integer> save(List<NbmhFarm> nbmhFarmList) {
        BeanUtils.validBean(nbmhFarmList, NbmhFarm.Save.class);

        return nbmhFarmList.stream().map(entity -> {

            Date createTime = new Date();
            entity.setCreateTime(createTime).setUpdateTime(createTime).setStatus(1);

            return nbmhFarmMapper.insert(entity);

        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int update(@NotEmpty List<NbmhFarm> nbmhFarmList) {
        BeanUtils.validBean(nbmhFarmList, NbmhFarm.Update.class);

        if (nbmhFarmList == null || nbmhFarmList.size() == 0) {
            return 0;
        }

        return nbmhFarmList.stream().map(entity -> {

            entity.setUpdateTime(new Date());

            return nbmhFarmMapper.update(entity, Wrappers.<NbmhFarm>lambdaUpdate().eq(NbmhFarm::getId, entity.getId()));
        }).reduce(0, Integer::sum);
    }

    @Override
    public void updateAnimalJson(List<NbmhAnimal> animalList, int updateType) {

        animalList.forEach(animal -> updateAnimalJson(animal, updateType));
    }

    @Override
    public int updateAnimalJson(NbmhAnimal animal, int updateType) {

        NbmhFarm nbmhFarm = nbmhFarmMapper.selectById(animal.getFarmId());
        if (nbmhFarm == null) {
            throw new ServiceException("养殖场信息不存在");
        }

        List<AnimalJsonEntity> animalJsonList = JSONArray.parseArray(nbmhFarm.getFarmAnimalJson(), AnimalJsonEntity.class);
        for (AnimalJsonEntity animalJson : animalJsonList) {
            if (animalJson.getAnimalType() != animal.getType()) {
                continue;
            }

            if (updateType == 0) {
                animalJson.setCount(animalJson.getCount() - 1);
            } else {
                animalJson.setCount(animalJson.getCount() + 1);
            }
        }

        nbmhFarm.setFarmAnimalJson(JSON.toJSONString(animalJsonList));

        return nbmhFarmMapper.updateById(nbmhFarm);
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<NbmhFarm> query(NbmhFarm entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {

        Page<NbmhFarm> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<NbmhFarm> lambdaQueryWrapper = Wrappers.lambdaQuery(entity);

        return nbmhFarmMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NbmhFarm> list(NbmhFarm entity, List<OrderItem> orderItemList) {

        QueryWrapper<NbmhFarm> queryWrapper = Wrappers.query(entity);
        if (orderItemList != null && orderItemList.size() > 0) {
            orderItemList.forEach(t -> queryWrapper.orderBy(true, t.isAsc(), t.getColumn()));
        }

        return nbmhFarmMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void delete(List<Long> idList) {
        idList.forEach(id -> {

            NbmhFarm entity = this.getById(id);
            if (entity != null) {
                entity.setStatus(-1).setUpdateTime(new Date());
                nbmhFarmMapper.updateById(entity);
            }
        });
    }

    @Override
    @Transactional(readOnly = true)
    public List<NbmhFarmResult> listByPreventStationId(Long preventStationId) {

        QueryWrapper<NbmhFarm> queryWrapper = Wrappers.query(NbmhFarm.builder().preventStationId(preventStationId).build());
        List<NbmhFarm> farmList = nbmhFarmMapper.selectList(queryWrapper);

        if (CollectionUtils.isEmpty(farmList)) {
            return null;
        }

        List<NbmhFarmResult> farmResultList = new ArrayList<>();
        for (NbmhFarm item : farmList) {
            NbmhFarmResult nbmhFarmResult = NbmhFarmResult.builder().id(item.getId()).farmerId(item.getFarmerId()).farmName(item.getFarmName()).farmAddress(item.getFarmAddress()).preventStationId(item.getPreventStationId())
                    .manageScope(item.getManageScope()).farmScale(item.getFarmScale()).manageYear(item.getManageYear()).teamNum(item.getTeamNum()).farmAnimalJson(item.getFarmAnimalJson()).latitude(item.getLatitude())
                    .longitude(item.getLongitude()).userId(item.getUserId()).userName(item.getUserName()).createTime(item.getCreateTime()).updateTime(item.getUpdateTime()).status(item.getStatus()).build();

            NbmhUser nbmhUser = remoteUserService.getById(item.getFarmerId()).getData();
            if (ObjectUtils.isEmpty(nbmhUser)) {
                continue;
            }

            farmResultList.add(nbmhFarmResult.setFarmerName(nbmhUser.getUserName()).setFarmerPhone(nbmhUser.getPhone()));
        }

        return farmResultList;
    }

}
