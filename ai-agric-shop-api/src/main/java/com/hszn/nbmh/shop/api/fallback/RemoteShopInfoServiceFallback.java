package com.hszn.nbmh.shop.api.fallback;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.shop.api.entity.NbmhShopExtraInfo;
import com.hszn.nbmh.shop.api.entity.NbmhShopInfo;
import com.hszn.nbmh.shop.api.feign.RemoteShopInfoService;
import com.hszn.nbmh.shop.api.params.input.ShopEditParam;
import com.hszn.nbmh.shop.api.params.input.ShopInfoParam;
import com.hszn.nbmh.shop.api.params.input.ShopInfoSearchParam;
import org.springframework.stereotype.Component;

/**
 * 店铺对外服务降级类
 *
 * @author liwei
 * @version 1.0
 * @since 2022-09-02 17:26
 */
@Component
public class RemoteShopInfoServiceFallback implements RemoteShopInfoService {
    @Override
    public Result<String> openShop(ShopInfoParam shopInfo) {
        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Override
    public Result<NbmhShopInfo> detail(Long id) {
        return Result.failed(CommonEnum.DATA_QUERY_FAILED.getMsg());
    }

    @Override
    public Result<Page<NbmhShopInfo>> search(QueryRequest<ShopInfoSearchParam> param) {
        return Result.failed(CommonEnum.DATA_QUERY_FAILED.getMsg());
    }

    @Override
    public Result<String> update(ShopEditParam param) {
        return Result.failed(CommonEnum.DATA_UPDATE_FAILED.getMsg());
    }

    @Override
    public Result<String> commitEntityInfo(NbmhShopExtraInfo info) {
        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Override
    public Result<NbmhShopInfo> currentShopInfo() {
        return Result.failed(CommonEnum.DATA_QUERY_FAILED.getMsg());
    }
}
