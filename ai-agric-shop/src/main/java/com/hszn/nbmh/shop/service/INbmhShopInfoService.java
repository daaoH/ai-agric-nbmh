package com.hszn.nbmh.shop.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.shop.api.entity.NbmhShopInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.shop.api.params.input.ShopEditParam;
import com.hszn.nbmh.shop.api.params.input.ShopInfoParam;
import com.hszn.nbmh.shop.api.params.input.ShopInfoSearchParam;

/**
 * <p>
 * 店铺信息表 服务类
 * </p>
 *
 * @author lw
 * @since 2022-08-31
 */
public interface INbmhShopInfoService extends IService<NbmhShopInfo> {

    /**
     * 新增店铺
     *
     * @param shopInfo
     * @return boolean
     */
    boolean saveShop(ShopInfoParam shopInfo);

    /**
     * 根据条件查询
     *
     * @param param
     * @return
     */
    Page<NbmhShopInfo> search(QueryRequest<ShopInfoSearchParam> param);

    /**
     * 店铺修改
     *
     * @param param
     * @return
     */
    boolean modify(ShopEditParam param);

}
