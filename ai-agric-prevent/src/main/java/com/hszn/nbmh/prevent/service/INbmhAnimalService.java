package com.hszn.nbmh.prevent.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.prevent.api.entity.NbmhAnimal;
import com.hszn.nbmh.prevent.api.params.input.AnimalParam;
import com.hszn.nbmh.prevent.api.params.input.VaccinParam;
import com.hszn.nbmh.prevent.api.params.out.AnimalDetailsResult;
import com.hszn.nbmh.prevent.api.params.out.StatisticsResult;

import java.util.List;

/**
 * <p>
 * 动物信息表 服务类
 * </p>
 *
 * @author wangjun
 * @since 2022-08-16
 */
public interface INbmhAnimalService extends IService<NbmhAnimal> {


    /**
     * 分页查询
     *
     * @param param
     * @return IPage
     */
    IPage<NbmhAnimal> getByPage(QueryRequest<NbmhAnimal> param);

    /**
     * 根据农户统计动物信息
     *
     * @param userId
     * @return
     * @Param type 动物类型
     */
    StatisticsResult getCensusByUserId(Long userId, Integer type);


    /**
     * 根据耳标查询动物信息
     *
     * @param earNo
     * @return NbmhAnimal
     */
    AnimalDetailsResult getByEarNo(String earNo);

    /**
     * 添加返回id
     *
     * @param param
     * @return id
     */
    Long insert(VaccinParam param);


}
