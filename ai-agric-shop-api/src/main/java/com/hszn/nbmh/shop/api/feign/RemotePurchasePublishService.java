package com.hszn.nbmh.shop.api.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.shop.api.constant.ShopPathConstant;
import com.hszn.nbmh.shop.api.entity.NbmhPurchasePublish;
import com.hszn.nbmh.shop.api.fallback.PurchasePublishServiceFallback;
import com.hszn.nbmh.shop.api.params.input.PruchasePublishParam;
import com.hszn.nbmh.shop.api.params.out.PublishListReturn;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author：袁德民
 * @Description:
 * @Date:上午11:15 22/8/30
 * @Modified By:
 */
@FeignClient(value = ServiceNameConstant.SHOP_SERVICE, path = ShopPathConstant.PURCHASE_PUBLISH_URL, fallback = PurchasePublishServiceFallback.class)
public interface RemotePurchasePublishService {

    @PostMapping("/publishBuyGoods")
    Result publishBuyGoods(@RequestBody NbmhPurchasePublish purchasePublish);

    @PostMapping("/publishList")
    Result<IPage<PublishListReturn>> queryPublishList(@RequestBody PruchasePublishParam publishParam,
                                                             @RequestParam("pageSize") Integer pageSize, @RequestParam("pageNum") Integer pageNum);
}
