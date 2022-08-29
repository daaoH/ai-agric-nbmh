package com.hszn.nbmh.prevent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.common.core.exception.ServiceException;
import com.hszn.nbmh.common.core.utils.BeanUtils;
import com.hszn.nbmh.common.core.utils.SnowFlakeIdUtil;
import com.hszn.nbmh.prevent.api.entity.NbmhAnimal;
import com.hszn.nbmh.prevent.api.entity.NbmhButcherReport;
import com.hszn.nbmh.prevent.api.entity.NbmhInspect;
import com.hszn.nbmh.prevent.api.entity.NbmhTradeReport;
import com.hszn.nbmh.prevent.mapper.NbmhAnimalMapper;
import com.hszn.nbmh.prevent.mapper.NbmhTradeReportMapper;
import com.hszn.nbmh.prevent.service.INbmhFarmService;
import com.hszn.nbmh.prevent.service.INbmhInspectService;
import com.hszn.nbmh.prevent.service.INbmhTradeReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 活体交易信息表 服务实现类
 * </p>
 *
 * @author MCR
 * @since 2022-08-15
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class NbmhTradeReportServiceImpl extends ServiceImpl<NbmhTradeReportMapper, NbmhTradeReport> implements INbmhTradeReportService {
    @Resource
    private NbmhTradeReportMapper nbmhTradeReportMapper;

    private final NbmhAnimalMapper animalMapper;

    private final INbmhInspectService inspectService;

    @Autowired
    private INbmhFarmService nbmhFarmService;


    @Override
    @Transactional
    public List<Integer> save(List<NbmhTradeReport> nbmhTradeReportList) {

        BeanUtils.validBean(nbmhTradeReportList, NbmhTradeReport.Save.class);

        return nbmhTradeReportList.stream().map(entity -> {

            Date createTime = new Date();
            entity.setReportNumber(String.valueOf(new SnowFlakeIdUtil(1, 0).nextId())).setCreateTime(createTime).setUpdateTime(createTime).setStatus(1);

            int ret = nbmhTradeReportMapper.insert(entity);

            handleAnimalData(entity);

            return ret;
        }).collect(Collectors.toList());
    }

    private void handleAnimalData(NbmhTradeReport entity) {
        List<NbmhAnimal> animalList = new ArrayList<>();
        List<NbmhInspect> nbmhInspectList = new ArrayList<>();

        if (entity.getAnimalId() == null || entity.getAnimalId().isEmpty()) {
            throw new ServiceException("动物Id为空，数据保存错误");
        }

        List<String> animalIdList = Arrays.asList(entity.getAnimalId().split(","));

        animalIdList.forEach(animalId -> {

            NbmhAnimal animal = animalMapper.selectById(animalId);
            if (animal == null) {
                throw new ServiceException("动物信息未找到");
            }

            animalList.add(animal);
            nbmhInspectList.add(NbmhInspect.builder().animalId(Long.valueOf(animalId)).animalType(entity.getAnimalType()).earNo(animal.getEarNo())
                    .buyerName(entity.getBuyerName()).buyerPhone(entity.getBuyerPhone()).buyerCard(entity.getBuyerCard())
                    .userId(entity.getFarmerId()).userName(entity.getFarmerName()).stationId(entity.getPreventStationId()).animalStatus(entity.getStatus()).build());
        });

        inspectService.saveBatch(nbmhInspectList);

        nbmhFarmService.updateAnimalJson(animalList, 0);
    }


    @Override
    @Transactional
    public int update(List<NbmhTradeReport> nbmhTradeReportList) {
        BeanUtils.validBean(nbmhTradeReportList, NbmhButcherReport.Update.class);

        if (nbmhTradeReportList == null || nbmhTradeReportList.size() == 0) {
            return 0;
        }

        return nbmhTradeReportList.stream().map(entity -> {

            entity.setUpdateTime(new Date());

            return nbmhTradeReportMapper.update(entity, Wrappers.<NbmhTradeReport>lambdaUpdate().eq(NbmhTradeReport::getId, entity.getId()));
        }).reduce(0, Integer::sum);
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<NbmhTradeReport> query(NbmhTradeReport entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {

        Page<NbmhTradeReport> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<NbmhTradeReport> lambdaQueryWrapper = Wrappers.lambdaQuery(entity);

        return nbmhTradeReportMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NbmhTradeReport> list(NbmhTradeReport entity, List<OrderItem> orderItemList) {

        QueryWrapper<NbmhTradeReport> queryWrapper = Wrappers.query(entity);
        if (orderItemList != null && orderItemList.size() > 0) {
            orderItemList.forEach(t -> queryWrapper.orderBy(true, t.isAsc(), t.getColumn()));
        }

        return nbmhTradeReportMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void delete(List<Long> idList) {

        idList.forEach(id -> {
            NbmhTradeReport entity = this.getById(id);
            if (entity != null) {
                entity.setStatus(-1).setUpdateTime(new Date());
                nbmhTradeReportMapper.updateById(entity);
            }
        });
    }

}
