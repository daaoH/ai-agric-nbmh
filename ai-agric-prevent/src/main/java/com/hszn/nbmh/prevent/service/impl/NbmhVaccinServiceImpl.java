package com.hszn.nbmh.prevent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.SortUtil;
import com.hszn.nbmh.prevent.api.entity.NbmhAnimal;
import com.hszn.nbmh.prevent.api.entity.NbmhVaccin;
import com.hszn.nbmh.prevent.api.params.input.VaccinParam;
import com.hszn.nbmh.prevent.mapper.NbmhVaccinMapper;
import com.hszn.nbmh.prevent.service.INbmhAnimalService;
import com.hszn.nbmh.prevent.service.INbmhVaccinService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>
 * 防疫信息表 服务实现类
 * </p>
 *
 * @author wangjun
 * @since 2022-08-16
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor=Exception.class)
public class NbmhVaccinServiceImpl extends ServiceImpl<NbmhVaccinMapper, NbmhVaccin> implements INbmhVaccinService {


    private final INbmhAnimalService animalService;

    /**
     * 分页查询
     *
     * @param param
     * @return IPage<NbmhVaccin>
     */
    @Override
    public IPage<NbmhVaccin> getByPage(QueryRequest<VaccinParam> param) {
        //添加条件
        LambdaQueryWrapper<NbmhVaccin> queryWrapper=new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(param.getQueryEntity())) {
            //农户
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getUserId())) {
                queryWrapper.eq(NbmhVaccin::getFarmerId, param.getQueryEntity().getFarmerId());
            }
            //防疫人员
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getVaccinUser())) {
                queryWrapper.eq(NbmhVaccin::getVaccinUser, param.getQueryEntity().getVaccinUser());
            }
            //抵押状态
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getStatus())) {
                queryWrapper.eq(NbmhVaccin::getStatus, param.getQueryEntity().getStatus());
            }
            //牲畜类型
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getAnimalType())) {
                queryWrapper.eq(NbmhVaccin::getAnimalType, param.getQueryEntity().getAnimalType());
            }

            //时间 查询条件为年月日匹配
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getCreateTime())) {
                String strStart=DateFormatUtils.format(param.getQueryEntity().getCreateTime(), "yyyy-MM-dd");
                queryWrapper.apply("UNIX_TIMESTAMP(create_time) = UNIX_TIMESTAMP('" + strStart + "')");
            }
        }
        //分页
        Page<NbmhVaccin> page=new Page<>(param.getPageNum(), param.getPageSize());
        //排序
        SortUtil.handlePageSort(param, page, param.getField(), param.getOrder(), true);
        return baseMapper.selectPage(page, queryWrapper);
    }


    @Override
    public List<NbmhVaccin> getByEarNo(String earNo) {
        NbmhAnimal animal=animalService.getByEarNo(earNo);
        NbmhVaccin vaccin=new NbmhVaccin();
        vaccin.setAnimalId(animal.getId());
        return this.details(vaccin);
    }

    /**
     * 防疫动物详情
     *
     * @param vaccin
     * @return List<NbmhVaccin>
     */
    @Override
    public List<NbmhVaccin> details(NbmhVaccin vaccin) {
        //添加条件
        LambdaQueryWrapper<NbmhVaccin> queryWrapper=Wrappers.lambdaQuery(vaccin);
        //时间 查询条件为年月日匹配
        if (ObjectUtils.isNotEmpty(vaccin.getVaccinTime())) {
            String strStart=DateFormatUtils.format(vaccin.getVaccinTime(), "yyyy-MM-dd");
            queryWrapper.apply("UNIX_TIMESTAMP(create_time) = UNIX_TIMESTAMP('" + strStart + "')");
        }
        queryWrapper.orderByDesc(NbmhVaccin::getCreateTime);
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * @param param(根据防疫人,动物类型,时间,获取历史具体防疫动物数量)
     * @return num
     * 防疫数据统计数量
     */
    @Override
    public Long getNum(NbmhVaccin param) {
        //添加条件
        LambdaQueryWrapper<NbmhVaccin> queryWrapper=new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(param)) {
            //防疫id
            if (ObjectUtils.isNotEmpty(param.getVaccinUserId())) {
                queryWrapper.eq(NbmhVaccin::getVaccinUserId, param.getVaccinUserId());
            }
            //动物类型
            if (ObjectUtils.isNotEmpty(param.getAnimalType())) {
                queryWrapper.eq(NbmhVaccin::getAnimalType, param.getAnimalType());
            }
            //时间 查询条件为年月日匹配
            if (ObjectUtils.isNotEmpty(param.getVaccinTime())) {
                String strStart=DateFormatUtils.format(param.getVaccinTime(), "yyyy-MM-dd");
                queryWrapper.apply("UNIX_TIMESTAMP(create_time) = UNIX_TIMESTAMP('" + strStart + "')");
            }
        }
        return this.baseMapper.selectCount(queryWrapper);
    }

}
