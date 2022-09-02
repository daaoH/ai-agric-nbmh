package com.hszn.nbmh.shop.service;

import com.hszn.nbmh.shop.api.entity.NbmhShopExtraInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 店铺扩展信息表 服务类
 * </p>
 *
 * @author lw
 * @since 2022-09-02
 */
public interface INbmhShopExtraInfoService extends IService<NbmhShopExtraInfo> {
    /**
     * 根据店铺id获取数据
     *
     * @param shopId
     * @return
     */
    NbmhShopExtraInfo finByShopId(Long shopId);
}
