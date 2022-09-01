package com.hszn.nbmh.good.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.good.api.entity.NbmhGoods;
import com.hszn.nbmh.good.api.entity.NbmhGoodsSku;
import com.hszn.nbmh.good.api.params.input.QueryGoodsParams;
import com.hszn.nbmh.good.api.params.vo.SkuVo;
import com.hszn.nbmh.good.mapper.NbmhGoodsMapper;
import com.hszn.nbmh.good.service.INbmhGoodsService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
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


    @Override
    public IPage<NbmhGoods> queryGoodsByParams(IPage<NbmhGoods> page, QueryGoodsParams params) {
        return goodsMapper.queryGoodsByParams(page, params);
    }
}
