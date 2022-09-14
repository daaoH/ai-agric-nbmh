package com.hszn.nbmh.good.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.exception.ServiceException;
import com.hszn.nbmh.good.api.entity.NbmhGoodsSku;
import com.hszn.nbmh.good.api.enums.GoodAuthEnum;
import com.hszn.nbmh.good.api.enums.GoodStatusEnum;
import com.hszn.nbmh.good.api.params.vo.SkuVo;
import com.hszn.nbmh.good.api.params.vo.SpecsVo;
import com.hszn.nbmh.good.mapper.NbmhGoodsSkuMapper;
import com.hszn.nbmh.good.service.INbmhGoodsSkuService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

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

    @Override
    public Boolean lockStock(Long skuId, Integer num) {
        return skuMapper.lockSkuStock(skuId, num);
    }

    @Override
    public Boolean unlockStock(Long skuId, Integer num) {
        return skuMapper.unLockStock(skuId, num);
    }

    @Autowired
    private RedisTemplate redisTemplate;

    //从缓存中获取sku数据
    @Override
    public NbmhGoodsSku getGoodsSkuFromCache(Long id){
        NbmhGoodsSku goodsSku = (NbmhGoodsSku) redisTemplate.opsForValue().get(INbmhGoodsSkuService.getCacheKeys(id));
        if(ObjectUtils.isEmpty(goodsSku)){
            goodsSku = this.getById(id);
            if(ObjectUtils.isEmpty(goodsSku)){
                return null;
            }
            redisTemplate.opsForValue().set(INbmhGoodsSkuService.getCacheKeys(id), goodsSku);
        }

        Integer stock = (Integer) redisTemplate.opsForValue().get(INbmhGoodsSkuService.getStockCacheKey(id));

        //库存不为空且库存与缓存中不一致
        if(!ObjectUtils.isEmpty(stock) && !goodsSku.getStock().equals(stock)){
            goodsSku.setStock(stock);
            redisTemplate.opsForValue().set(INbmhGoodsSkuService.getCacheKeys(id), goodsSku);
        }

        return goodsSku;
    }
}
