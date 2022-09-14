package com.hszn.nbmh.good.api.fallback;

import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.good.api.entity.NbmhGoodsSku;
import com.hszn.nbmh.good.api.feign.RemoteGoodsSkuService;
import org.springframework.stereotype.Component;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午7:55 22/9/13
 * @Modified By:
 */
@Component
public class GoodsSkuServiceFallback implements RemoteGoodsSkuService {

    @Override
    public Result<NbmhGoodsSku> getGoodsSkuById(Long skuId) {
        return null;
    }

    @Override
    public Result<Boolean> lockstock(Long skuId, Integer num) {
        return null;
    }

    @Override
    public Result<Boolean> unlockstock(Long skuId, Integer num) {
        return null;
    }
}
