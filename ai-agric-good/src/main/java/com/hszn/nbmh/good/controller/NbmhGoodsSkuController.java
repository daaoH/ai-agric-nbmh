package com.hszn.nbmh.good.controller;


import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.exception.ServiceException;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.good.api.entity.NbmhGoodsSku;
import com.hszn.nbmh.good.api.enums.GoodAuthEnum;
import com.hszn.nbmh.good.api.enums.GoodStatusEnum;
import com.hszn.nbmh.good.api.params.input.AddGoodsParams;
import com.hszn.nbmh.good.service.INbmhGoodsSkuService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

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

    @Operation(description = "根据id获取商品sku")
    @PostMapping("/getGoodsSkuById")
    public Result<NbmhGoodsSku> getGoodsSkuById(@RequestParam("skuId") Long skuId){
        NbmhGoodsSku goodsSku = goodsSkuService.getGoodsSkuFromCache(skuId);
        return Result.ok(goodsSku);
    }
}
