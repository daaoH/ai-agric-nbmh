package com.hszn.nbmh.shop.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.shop.api.entity.NbmhPurchasePublish;
import com.hszn.nbmh.shop.api.params.input.PruchasePublishParam;
import com.hszn.nbmh.shop.api.params.out.PublishListReturn;
import com.hszn.nbmh.shop.mapper.NbmhPurchasePublishMapper;
import com.hszn.nbmh.shop.service.INbmhPurchasePublishService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 采购发布信息表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-08-30
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhPurchasePublishServiceImpl extends ServiceImpl<NbmhPurchasePublishMapper, NbmhPurchasePublish> implements INbmhPurchasePublishService {
    @Resource
    private NbmhPurchasePublishMapper purchasePublishMapper;


    @Override
    public IPage<PublishListReturn> queryPublishPageList(IPage<PublishListReturn> page, PruchasePublishParam params) {
        return purchasePublishMapper.queryPublishPageList(page, params);
    }
}
