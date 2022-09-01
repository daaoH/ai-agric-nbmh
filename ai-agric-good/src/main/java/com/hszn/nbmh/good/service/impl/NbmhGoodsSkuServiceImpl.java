package com.hszn.nbmh.good.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hszn.nbmh.good.api.entity.NbmhGoodsSku;
import com.hszn.nbmh.good.api.params.vo.SkuVo;
import com.hszn.nbmh.good.api.params.vo.SpecsVo;
import com.hszn.nbmh.good.mapper.NbmhGoodsSkuMapper;
import com.hszn.nbmh.good.service.INbmhGoodsSkuService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * sku库存 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-09-01
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhGoodsSkuServiceImpl extends ServiceImpl<NbmhGoodsSkuMapper, NbmhGoodsSku> implements INbmhGoodsSkuService {

    @Resource
    private NbmhGoodsSkuMapper skuMapper;


    @Override
    public List<SkuVo> querySkuByGoodId(Long goodId) {
        List<NbmhGoodsSku> skus = skuMapper.selectList(Wrappers.<NbmhGoodsSku>query().lambda()
                .eq(NbmhGoodsSku::getGoodsId, goodId)
                .eq(NbmhGoodsSku::getStatus, 0)
                .eq(NbmhGoodsSku::getGoodsStatus, 1));
        if(CollectionUtils.isNotEmpty(skus)){
            List<SkuVo> skuVos = new ArrayList<>();
            for (NbmhGoodsSku sku : skus){
                SkuVo skuVo = new SkuVo();
                BeanUtil.copyProperties(sku, skuVo);
                String specs = sku.getSpecs();
                SpecsVo specsVo = JSON.parseObject(specs, SpecsVo.class);
                skuVo.setSpecsVo(specsVo);
                skuVos.add(skuVo);
            }

            return skuVos;
        }
        return null;
    }
}
