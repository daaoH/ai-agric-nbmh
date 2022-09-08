package com.hszn.nbmh.good.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.utils.SnowFlakeIdUtil;
import com.hszn.nbmh.good.api.entity.NbmhGoods;
import com.hszn.nbmh.good.api.entity.NbmhGoodsAttribute;
import com.hszn.nbmh.good.api.entity.NbmhGoodsSku;
import com.hszn.nbmh.good.api.entity.NbmhGoodsSpecification;
import com.hszn.nbmh.good.api.params.input.AddGoodsParams;
import com.hszn.nbmh.good.api.params.input.QueryGoodsParams;
import com.hszn.nbmh.good.api.params.vo.AttributeVo;
import com.hszn.nbmh.good.api.params.vo.GoodSpecs;
import com.hszn.nbmh.good.api.params.vo.SkuVo;
import com.hszn.nbmh.good.api.params.vo.SpecsVo;
import com.hszn.nbmh.good.mapper.NbmhGoodsAttributeMapper;
import com.hszn.nbmh.good.mapper.NbmhGoodsMapper;
import com.hszn.nbmh.good.mapper.NbmhGoodsSkuMapper;
import com.hszn.nbmh.good.mapper.NbmhGoodsSpecificationMapper;
import com.hszn.nbmh.good.service.INbmhGoodsAttributeService;
import com.hszn.nbmh.good.service.INbmhGoodsService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.good.service.INbmhGoodsSkuService;
import com.hszn.nbmh.good.service.INbmhGoodsSpecificationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import javax.annotation.Untainted;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品基本信息表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-08-25
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhGoodsServiceImpl extends ServiceImpl<NbmhGoodsMapper, NbmhGoods> implements INbmhGoodsService {

    @Resource
    private NbmhGoodsMapper goodsMapper;

    @Autowired
    private INbmhGoodsSkuService skuService;

    @Autowired
    private INbmhGoodsAttributeService attributeService;

    @Autowired
    private INbmhGoodsSpecificationService specificationService;

    SnowFlakeIdUtil snowFlakeUtil = new SnowFlakeIdUtil(0, 0);


    @Override
    public IPage<NbmhGoods> queryGoodsByParams(IPage<NbmhGoods> page, QueryGoodsParams params) {
        return goodsMapper.queryGoodsByParams(page, params);
    }

    @Transactional
    @Override
    public boolean addGoods(AddGoodsParams params) {

        Long id = snowFlakeUtil.nextId();
        NbmhGoods goods = new NbmhGoods();
        goods.setId(id);
        goods.setStatus(0);
        BeanUtils.copyProperties(params, goods);

        goodsMapper.insert(goods);

        List<AttributeVo> attributes = params.getAttributes();
        if(CollectionUtil.isNotEmpty(attributes)){
            List<NbmhGoodsAttribute> goodsAttributes = new ArrayList<>();
            for(AttributeVo attribute : attributes){
                NbmhGoodsAttribute goodsAttribute = new NbmhGoodsAttribute();
                goodsAttribute.setId(snowFlakeUtil.nextId());
                goodsAttribute.setAttribute(attribute.getAttribute());
                goodsAttribute.setValue(attribute.getValue());
                goodsAttribute.setGoodsId(id);
                goodsAttributes.add(goodsAttribute);
            }
            attributeService.saveBatch(goodsAttributes);
        }

        List<SpecsVo> specs = params.getSpecs();
        if(CollectionUtil.isNotEmpty(specs)){
            List<NbmhGoodsSpecification> goodsSpecifications = new ArrayList<>();
            List<NbmhGoodsSku> goodsSkus = new ArrayList<>();
            for(SpecsVo specsVo : specs){
                for(GoodSpecs goodSpecs : specsVo.getGoodSpecs()){
                    NbmhGoodsSpecification specification = new NbmhGoodsSpecification();
                    specification.setId(snowFlakeUtil.nextId());
                    specification.setGoodsId(id);
                    specification.setSpecification(specsVo.getName());
                    specification.setValue(goodSpecs.getValue());
                    goodsSpecifications.add(specification);

                    NbmhGoodsSku goodsSku = new NbmhGoodsSku();
                    goodsSku.setId(snowFlakeUtil.nextId());
                    goodsSku.setShopId(params.getShopId());
                    goodsSku.setStock(params.getStock());
                    goodsSku.setGoodsId(id);
                    goodsSku.setGoodsName(params.getName());
                    goodsSku.setPrice(new BigDecimal(goodSpecs.getValue()));
                    goodsSku.setSkuName(params.getName()+" "+goodSpecs.getSpecification());
                    goodsSkus.add(goodsSku);
                }
            }
            specificationService.saveBatch(goodsSpecifications);
            skuService.saveBatch(goodsSkus);
        }
        return true;
    }
}
