package com.hszn.nbmh.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.shop.api.entity.NbmhPurchasePublish;
import com.hszn.nbmh.shop.api.params.input.PruchasePublishParam;
import com.hszn.nbmh.shop.api.params.out.PublishListReturn;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 采购发布信息表 服务类
 * </p>
 *
 * @author yuan
 * @since 2022-08-30
 */
public interface INbmhPurchasePublishService extends IService<NbmhPurchasePublish> {

    IPage<PublishListReturn> queryPublishPageList(IPage<PublishListReturn> page, @Param("params") PruchasePublishParam params);
}
