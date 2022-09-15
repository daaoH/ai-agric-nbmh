package com.hszn.nbmh.prevent.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.SortUtil;
import com.hszn.nbmh.prevent.api.entity.*;
import com.hszn.nbmh.prevent.api.params.input.AnimalParam;
import com.hszn.nbmh.prevent.api.params.input.VaccinParam;
import com.hszn.nbmh.prevent.api.params.out.AnimalDetailsResult;
import com.hszn.nbmh.prevent.api.params.out.StatisticsResult;
import com.hszn.nbmh.prevent.mapper.NbmhAnimalMapper;
import com.hszn.nbmh.prevent.mapper.NbmhFarmMapper;
import com.hszn.nbmh.prevent.mapper.NbmhInspectMapper;
import com.hszn.nbmh.prevent.mapper.NbmhVaccinMapper;
import com.hszn.nbmh.prevent.service.INbmhAnimalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.user.api.entity.NbmhUser;
import com.hszn.nbmh.user.api.feign.RemoteUserService;
import com.hszn.nbmh.user.api.params.out.CurUserInfo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 动物信息表 服务实现类
 * </p>
 *
 * @author wangjun
 * @since 2022-08-16
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor=Exception.class)
public class NbmhAnimalServiceImpl extends ServiceImpl<NbmhAnimalMapper, NbmhAnimal> implements INbmhAnimalService {


    private final NbmhFarmMapper nbmhFarmMapper;


    private final NbmhInspectMapper inspectMapper;

    private final NbmhVaccinMapper vaccinMapper;

    //用户
    private final RemoteUserService remoteUserService;


    /**
     * 动物分页数据
     *
     * @param param
     * @return IPage<NbmhAnimal>
     */
    @Override
    public IPage<NbmhAnimal> getByPage(QueryRequest<NbmhAnimal> param) {
        //添加条件
        LambdaQueryWrapper<NbmhAnimal> queryWrapper=new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(param.getQueryEntity())) {
            //耳标
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getEarNo())) {
                queryWrapper.like(NbmhAnimal::getEarNo, param.getQueryEntity().getEarNo());
            }
            //抵押状态
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getStatus())) {
                queryWrapper.eq(NbmhAnimal::getStatus, param.getQueryEntity().getStatus());
            }
            //抵押状态
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getFarmId())) {
                queryWrapper.eq(NbmhAnimal::getFarmId, param.getQueryEntity().getFarmId());
            }
            //类型
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getType())) {
                queryWrapper.eq(NbmhAnimal::getType, param.getQueryEntity().getType());
            }
            //农户id
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getUserId())) {
                queryWrapper.eq(NbmhAnimal::getUserId, param.getQueryEntity().getUserId());
            }
            //时间
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getCreateTime())) {
                String strStart=DateFormatUtils.format(param.getQueryEntity().getCreateTime(), "yyyy-MM-dd");
                queryWrapper.apply("date_format (create_time,'%Y-%m-%d') = date_format('" + strStart + "','%Y-%m-%d')");
            }
        }

        //分页
        Page<NbmhAnimal> page=new Page<>(param.getPageNum(), param.getPageSize());
        //排序
        SortUtil.handlePageSort(param, page, param.getField(), param.getOrder(), true);
        return baseMapper.selectPage(page, queryWrapper);
    }

    /**
     * 根据农户统计动物信息
     *
     * @param userId
     * @return
     */
    @Override
    public StatisticsResult getCensusByUserId(Long userId, Integer type) {
        StatisticsResult result=new StatisticsResult();
        if (!ObjectUtils.isNotEmpty(userId)) {
            return result;
        }
        //添加条件
        LambdaQueryWrapper<NbmhAnimal> queryWrapper=new LambdaQueryWrapper<>();
        //农户id
        queryWrapper.eq(NbmhAnimal::getUserId, userId);
        List<NbmhAnimal> animals=this.baseMapper.selectList(queryWrapper);

        int enclosureNum=0;  //存栏数量   历史总数-屠宰数量--无害化数量
        int immuneNum=0;     //历史免疫数量 =  (屠宰数量 + 无害化数量 + 正常免疫数量 )
        int notImmuneNum=0;  //未免疫数量
        /**
         * 农户旗下农场下未做防疫动物数量
         * 参数 用户id  动物类型
         */
        LambdaQueryWrapper<NbmhFarm> farmQueryWrapper=new LambdaQueryWrapper<>();
        farmQueryWrapper.eq(NbmhFarm::getFarmerId, userId);
        List<NbmhFarm> farmList=nbmhFarmMapper.selectList(farmQueryWrapper);
        //遍历获取对应类型未检疫动物AnimalJsonEntity
        if (ObjectUtils.isNotEmpty(farmList)) {
            for (NbmhFarm nbmhFarm : farmList) {
                if (ObjectUtils.isNotEmpty(nbmhFarm.getFarmAnimalJson())) {
                    //用json的方法toJavaList，参数放入想转的集合对象就可以了
                    List<AnimalJsonEntity> monthTaskRes=JSON.parseArray(nbmhFarm.getFarmAnimalJson(), AnimalJsonEntity.class);
                    for (AnimalJsonEntity animalJsonEntity : monthTaskRes) {
                        if (animalJsonEntity.getAnimalType() == type.intValue()) {
                            notImmuneNum=notImmuneNum + animalJsonEntity.getCount();
                        }
                    }
                }
            }
        }
        int normalNum=0;//正常免疫现有数量
        int slaughterNum=0;  //屠宰数量 状态=2
        int harmlessNum=0;   //无害化数量 状态=3
        int quarantineNum=0; //检疫数量 状态=4
        int total=0;        //历史总数 + 正常免疫数量 +屠宰数量 + 无害化数量 + 未免疫数量 + 检疫数量
        if (ObjectUtils.isNotEmpty(animals)) {
            for (NbmhAnimal a : animals) {
                if (a.getStatus() == 0) {
                    normalNum+=1;
                } else if (a.getStatus() == 2) {
                    slaughterNum+=1;
                } else if (a.getStatus() == 3) {
                    harmlessNum+=1;
                } else if (a.getStatus() == 4) {
                    quarantineNum+=1;
                }
            }
        }

        //历史总数 + 正常免疫数量 +屠宰数量 + 无害化数量 + 未免疫数量 + 检疫数量
        total=normalNum + slaughterNum + harmlessNum + quarantineNum;
        result.setTotal(total);
        //屠宰数量
        result.setSlaughterNum(slaughterNum);
        //无害化数量 状态=3
        result.setHarmlessNum(harmlessNum);
        //检疫数量 状态
        result.setQuarantineNum(quarantineNum);
        //存栏数量
        enclosureNum=total - slaughterNum - harmlessNum;
        result.setEnclosureNum(enclosureNum);
        immuneNum=slaughterNum + harmlessNum + normalNum;
        //存栏数量
        result.setImmuneNum(immuneNum);
        //为检疫数量
        result.setNotImmuneNum(notImmuneNum);
        return result;
    }

    /**
     * 根据耳标查询动物信息
     *
     * @param earNo
     * @return
     */
    @Override
    public AnimalDetailsResult getByEarNo(String earNo) {
        AnimalDetailsResult animalDetailsResult=new AnimalDetailsResult();
        NbmhAnimal animal=baseMapper.selectOne(Wrappers.<NbmhAnimal>query().lambda().eq(NbmhAnimal::getEarNo, earNo));
        if (ObjectUtils.isNotEmpty(animal)) {
            animalDetailsResult.setAnimal(animal);
            animalDetailsResult.setInspect(inspectMapper.selectOne(Wrappers.<NbmhInspect>query().lambda().eq(NbmhInspect::getEarNo, earNo)));
            animalDetailsResult.setVaccinList(vaccinMapper.selectList(Wrappers.<NbmhVaccin>query().lambda().eq(NbmhVaccin::getAnimalId, animal.getId())));
            animalDetailsResult.setCurUserInfo(remoteUserService.queryCurUserInfo(animal.getUserId(), 5).getData());
        }
        return animalDetailsResult;
    }

    /**
     * 添加动物数据返回id
     *
     * @param param
     * @return id
     */
    @Override
    public Long insert(VaccinParam param) {

        /**
         * 判断动物是否存在 存在则返回对应id
         */
        if (ObjectUtils.isNotEmpty(param.getAnimalId())) {
            return param.getAnimalId();
        }
        /**
         * 创建动物
         */
        NbmhAnimal animal=new NbmhAnimal();
        //对象转换
        BeanUtils.copyProperties(param, animal);
        this.baseMapper.insert(animal);
        return animal.getId();
    }

}
