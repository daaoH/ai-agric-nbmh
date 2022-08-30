package com.hszn.nbmh.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.shop.api.entity.NbmhPurchasePublish;
import com.hszn.nbmh.shop.api.params.input.PruchasePublishParam;
import com.hszn.nbmh.shop.api.params.out.PublishListReturn;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 采购发布信息表 Mapper 接口
 * </p>
 *
 * @author yuan
 * @since 2022-08-30
 */
public interface NbmhPurchasePublishMapper extends BaseMapper<NbmhPurchasePublish> {

    IPage<PublishListReturn> queryPublishPageList(IPage<PublishListReturn> page, @Param("params") PruchasePublishParam params);
}
