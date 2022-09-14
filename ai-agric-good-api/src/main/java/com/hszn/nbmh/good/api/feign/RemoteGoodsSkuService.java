package com.hszn.nbmh.good.api.feign;

import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.good.api.constant.GoodsPathConstant;
import com.hszn.nbmh.good.api.entity.NbmhGoodsSku;
import com.hszn.nbmh.good.api.fallback.GoodsSkuServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午7:52 22/9/13
 * @Modified By:
 */
@FeignClient(value = ServiceNameConstant.GOODS_SERVICE, path = GoodsPathConstant.GOODS_SKU, fallback = GoodsSkuServiceFallback.class)
public interface RemoteGoodsSkuService {

    @PostMapping("/getGoodsSkuById")
    Result<NbmhGoodsSku> getGoodsSkuById(@RequestParam("skuId") Long skuId);

    @PostMapping("/lock/stock")
    Result<Boolean> lockstock(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num);

    @PostMapping("/unlock/stock")
    Result<Boolean> unlockstock(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num);
}
