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
import com.hszn.nbmh.prevent.mapper.NbmhAnimalMapper;
import com.hszn.nbmh.prevent.mapper.NbmhButcherReportMapper;
import com.hszn.nbmh.prevent.service.INbmhButcherReportService;
import com.hszn.nbmh.prevent.service.INbmhFarmService;
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
 * 屠宰/无害化申报信息表 服务实现类
 * </p>
 *
 * @author MCR
 * @since 2022-08-15
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhButcherReportServiceImpl extends ServiceImpl<NbmhButcherReportMapper, NbmhButcherReport> implements INbmhButcherReportService {

    @Resource
    private NbmhButcherReportMapper nbmhButcherReportMapper;

    @Autowired
    private INbmhFarmService nbmhFarmService;

    @Resource
    private NbmhAnimalMapper animalMapper;

    @Override
    @Transactional
    public List<Integer> save(List<NbmhButcherReport> butcherReportList) {
        BeanUtils.validBean(butcherReportList, NbmhButcherReport.Save.class);

        return butcherReportList.stream().map(entity -> {

            List<String> earNoList = Arrays.asList(entity.getEarNo().split(","));
            earNoList.forEach(earNo -> {
                List<NbmhButcherReport> ButcherReports = this.list(NbmhButcherReport.builder().earNo(earNo).isEar(entity.getIsEar()).reportType(entity.getReportType()).insured(entity.getInsured()).animalType(entity.getAnimalType()).status(1).build(), null);
                if (ButcherReports != null && ButcherReports.size() > 0) {
                    throw new ServiceException("动物耳标：" + earNo + "已存在报备记录，无法录入");
                }
            });

            Date createTime = new Date();
            entity.setReportNumber(String.valueOf(new SnowFlakeIdUtil(1, 0).nextId())).setCreateTime(createTime).setUpdateTime(createTime).setStatus(1);

            int ret = nbmhButcherReportMapper.insert(entity);

            handleAnimalData(entity);

            return ret;
        }).collect(Collectors.toList());
    }

    private void handleAnimalData(NbmhButcherReport entity) {
        List<NbmhAnimal> animalList = new ArrayList<>();

        if (entity.getAnimalId() == null || entity.getAnimalId().isEmpty()) {
            throw new ServiceException("动物Id为空，数据保存错误");
        }

        List<String> animalIdList = Arrays.asList(entity.getAnimalId().split(","));

        animalIdList.forEach(animalId -> {

            NbmhAnimal animal = animalMapper.selectById(animalId);
            if (animal == null) {
                throw new ServiceException("动物信息未找到");
            }

            if (entity.getReportType() == 0) {
                animal.setStatus(2);
            } else {
                animal.setStatus(3);
            }
            animal.setUpdateTime(new Date());
            animalMapper.updateById(animal);

            animalList.add(animal);
        });

        nbmhFarmService.updateAnimalJson(animalList, 0);
    }

    @Override
    @Transactional
    public int update(List<NbmhButcherReport> nbmhButcherReportList) {
        BeanUtils.validBean(nbmhButcherReportList, NbmhButcherReport.Update.class);

        if (nbmhButcherReportList == null || nbmhButcherReportList.size() == 0) {
            return 0;
        }

        return nbmhButcherReportList.stream().map(entity -> {

            entity.setUpdateTime(new Date());

            return nbmhButcherReportMapper.update(entity, Wrappers.<NbmhButcherReport>lambdaUpdate().eq(NbmhButcherReport::getId, entity.getId()));
        }).reduce(0, Integer::sum);
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<NbmhButcherReport> query(NbmhButcherReport entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {

        Page<NbmhButcherReport> page = new Page<>(pageNum, pageSize);
        page.setOrders(orderItemList);

        LambdaQueryWrapper<NbmhButcherReport> lambdaQueryWrapper = Wrappers.lambdaQuery(entity);


//        List<Long> farmerIdList= new ArrayList<>();
//        resourceIPage = nbmhButcherReportMapper.selectPage(page, Wrappers.lambdaQuery(entity).in(NbmhButcherReport::getFarmerId, farmerIdList));

        return nbmhButcherReportMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NbmhButcherReport> list(NbmhButcherReport entity, List<OrderItem> orderItemList) {

        QueryWrapper<NbmhButcherReport> queryWrapper = Wrappers.query(entity);
        if (orderItemList != null && orderItemList.size() > 0) {
            orderItemList.forEach(t -> queryWrapper.orderBy(true, t.isAsc(), t.getColumn()));
        }

        return nbmhButcherReportMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void delete(List<Long> idList) {
        idList.forEach(id -> {

            NbmhButcherReport entity = this.getById(id);
            if (entity != null) {
                entity.setStatus(-1).setUpdateTime(new Date());
                nbmhButcherReportMapper.updateById(entity);
            }
        });
    }

}