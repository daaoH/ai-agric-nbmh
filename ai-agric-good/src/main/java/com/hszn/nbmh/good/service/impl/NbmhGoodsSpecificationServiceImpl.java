package com.hszn.nbmh.good.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hszn.nbmh.good.api.entity.NbmhGoodsSpecification;
import com.hszn.nbmh.good.api.params.vo.SpecsVo;
import com.hszn.nbmh.good.mapper.NbmhGoodsSpecificationMapper;
import com.hszn.nbmh.good.service.INbmhGoodsSpecificationService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品规格表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-08-25
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhGoodsSpecificationServiceImpl extends ServiceImpl<NbmhGoodsSpecificationMapper, NbmhGoodsSpecification> implements INbmhGoodsSpecificationService {
    @Resource
    private NbmhGoodsSpecificationMapper nbmhGoodsSpecificationMapper;


    @Override
    public List<SpecsVo> getSpecsVoList(Long goodId) {
        List<NbmhGoodsSpecification> specsList = baseMapper.selectList(Wrappers.<NbmhGoodsSpecification>query().lambda().eq(NbmhGoodsSpecification::getGoodsId, goodId).eq(NbmhGoodsSpecification::getStatus, 0));
        Map<String, List<NbmhGoodsSpecification>> groupSpecs = specsList.stream().collect(Collectors.groupingBy(specs -> specs.getSpecification()));
        if(CollectionUtils.isNotEmpty(groupSpecs)){
            List<SpecsVo> specsVos = new ArrayList<>();
            for(String name : groupSpecs.keySet()){
                SpecsVo specsVo = new SpecsVo();
                specsVo.setName(name);
                specsVo.setValueList(groupSpecs.get(name));
                specsVos.add(specsVo);
            }

            return specsVos;
        }
        return null;
    }
}
