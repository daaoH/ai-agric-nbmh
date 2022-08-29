package com.hszn.nbmh.prevent.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.SortUtil;
import com.hszn.nbmh.prevent.api.entity.AnimalJsonEntity;
import com.hszn.nbmh.prevent.api.entity.NbmhAnimal;
import com.hszn.nbmh.prevent.api.entity.NbmhFarm;
import com.hszn.nbmh.prevent.api.params.input.AnimalParam;
import com.hszn.nbmh.prevent.api.params.input.VaccinParam;
import com.hszn.nbmh.prevent.api.params.out.StatisticsResult;
import com.hszn.nbmh.prevent.mapper.NbmhAnimalMapper;
import com.hszn.nbmh.prevent.mapper.NbmhFarmMapper;
import com.hszn.nbmh.prevent.service.INbmhAnimalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
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

    /**
     * 根据多参数获取数据集
     *
     * @param params
     * @return
     */
    public List<NbmhAnimal> ByParams(NbmhAnimal params) {
        LambdaQueryWrapper<NbmhAnimal> lambdaQueryWrapper=Wrappers.lambdaQuery(params);
        return baseMapper.selectList(lambdaQueryWrapper);
    }


    /**
     * 动物分页数据
     *
     * @param param
     * @return IPage<NbmhAnimal>
     */
    @Override
    public IPage<NbmhAnimal> getByPage(QueryRequest<AnimalParam> param) {
        //添加条件
        LambdaQueryWrapper<NbmhAnimal> queryWrapper=new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(param.getQueryEntity())) {
            //抵押状态
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getEarNo())) {
                queryWrapper.eq(NbmhAnimal::getEarNo, param.getQueryEntity().getEarNo());
            }
            //抵押状态
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getStatus())) {
                queryWrapper.eq(NbmhAnimal::getStatus, param.getQueryEntity().getStatus());
            }
            //农户8
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getUserId())) {
                queryWrapper.eq(NbmhAnimal::getUserId, param.getQueryEntity().getUserId());
            }
            //时间
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getCreateTime())) {
                queryWrapper.eq(NbmhAnimal::getCreateTime, param.getQueryEntity().getCreateTime());
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
    public NbmhAnimal getByEarNo(String earNo) {
        return this.ByParams(new NbmhAnimal().setEarNo(earNo)).get(0);
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
