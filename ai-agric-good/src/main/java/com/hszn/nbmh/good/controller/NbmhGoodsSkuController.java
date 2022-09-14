package com.hszn.nbmh.good.controller;


import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.good.api.entity.NbmhGoodsSku;
import com.hszn.nbmh.good.service.INbmhGoodsSkuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Operation(description = "根据id获取商品sku")
    @PostMapping("/getGoodsSkuById")
    public Result<NbmhGoodsSku> getGoodsSkuById(@RequestParam("skuId") Long skuId) {
        NbmhGoodsSku goodsSku = goodsSkuService.getGoodsSkuFromCache(skuId);
        return Result.ok(goodsSku);
    }

    @Operation(description = "锁定库存")
    @Parameters({
            @Parameter(name = "skuId", description = "skuId"),
            @Parameter(name = "num", description = "数量")
    })
    @PostMapping("/lock/stock")
    public Result<Boolean> lockstock(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num) {
        return Result.ok(goodsSkuService.lockStock(skuId, num));
    }

    @Operation(description = "释放库存")
    @Parameters({
            @Parameter(name = "skuId", description = "skuId"),
            @Parameter(name = "num", description = "数量")
    })
    @PostMapping("/unlock/stock")
    public Result<Boolean> unlockstock(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num) {
        return Result.ok(goodsSkuService.unlockStock(skuId, num));
    }
}
