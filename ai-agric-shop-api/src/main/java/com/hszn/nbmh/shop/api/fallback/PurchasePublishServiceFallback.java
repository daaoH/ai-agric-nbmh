package com.hszn.nbmh.shop.api.fallback;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.shop.api.entity.NbmhPurchasePublish;
import com.hszn.nbmh.shop.api.feign.RemotePurchasePublishService;
import com.hszn.nbmh.shop.api.params.input.PruchasePublishParam;
import com.hszn.nbmh.shop.api.params.out.PublishListReturn;
import org.springframework.stereotype.Component;

/**
 * @Author：袁德民
 * @Description:
 * @Date:上午11:22 22/8/30
 * @Modified By:
 */

@Component
public class PurchasePublishServiceFallback implements RemotePurchasePublishService {

    @Override
    public Result publishBuyGoods(NbmhPurchasePublish purchasePublish) {
        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Override
    public Result<IPage<PublishListReturn>> queryPublishList(PruchasePublishParam publishParam, Integer pageSize, Integer pageNum) {
        return Result.failed(CommonEnum.DATA_QUERY_FAILED.getMsg());
    }
}
