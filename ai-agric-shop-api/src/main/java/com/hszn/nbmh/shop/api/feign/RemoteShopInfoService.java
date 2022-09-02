package com.hszn.nbmh.shop.api.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.shop.api.constant.ShopPathConstant;
import com.hszn.nbmh.shop.api.entity.NbmhShopExtraInfo;
import com.hszn.nbmh.shop.api.entity.NbmhShopInfo;
import com.hszn.nbmh.shop.api.fallback.RemoteShopInfoServiceFallback;
import com.hszn.nbmh.shop.api.params.input.ShopEditParam;
import com.hszn.nbmh.shop.api.params.input.ShopInfoParam;
import com.hszn.nbmh.shop.api.params.input.ShopInfoSearchParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 店铺对外服务
 *
 * @author liwei
 * @version 1.0
 * @since 2022-09-02 17:19
 */
@FeignClient(value = ServiceNameConstant.SHOP_SERVICE, path = ShopPathConstant.SHOP_INFO_URL, fallback = RemoteShopInfoServiceFallback.class)
public interface RemoteShopInfoService {
    @PostMapping("/openShop")
    Result<String> openShop(@RequestBody @Validated ShopInfoParam shopInfo);

    @GetMapping("/detail")
    Result<NbmhShopInfo> detail(@RequestParam("id") Long id);

    @PostMapping("/search")
    Result<Page<NbmhShopInfo>> search(@RequestBody QueryRequest<ShopInfoSearchParam> param);

    @PostMapping("/update")
    Result<String> update(@RequestBody ShopEditParam param);

    @PostMapping("/commitEntityInfo")
    Result<String> commitEntityInfo(@RequestBody NbmhShopExtraInfo info);

    @GetMapping("/currentShopInfo")
    Result<NbmhShopInfo> currentShopInfo();
}
