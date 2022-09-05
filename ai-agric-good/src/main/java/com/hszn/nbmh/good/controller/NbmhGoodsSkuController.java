package com.hszn.nbmh.good.controller;


import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.exception.ServiceException;
import com.hszn.nbmh.good.api.entity.NbmhGoodsSku;
import com.hszn.nbmh.good.api.enums.GoodAuthEnum;
import com.hszn.nbmh.good.api.enums.GoodStatusEnum;
import com.hszn.nbmh.good.service.INbmhGoodsSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * sku库存 前端控制器
 * </p>
 *
 * @author yuan
 * @since 2022-09-01
 */
@RestController
@RequestMapping("/goods-sku")
public class NbmhGoodsSkuController {

    @Autowired
    private INbmhGoodsSkuService goodsSkuService;

}
