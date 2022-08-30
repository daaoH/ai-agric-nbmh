package com.hszn.nbmh.shop.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.shop.api.entity.NbmhPurchasePublish;
import com.hszn.nbmh.shop.api.params.input.PruchasePublishParam;
import com.hszn.nbmh.shop.api.params.out.PublishListReturn;
import com.hszn.nbmh.shop.service.INbmhPurchasePublishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 采购发布信息表 前端控制器
 * </p>
 *
 * @author yuan
 * @since 2022-08-30
 */
@Tag(name = "purchase-publish", description = "采购发布接口")
@SecurityRequirement(name= HttpHeaders.AUTHORIZATION)
@RestController
@RequestMapping("/purchase-publish")
public class NbmhPurchasePublishController {

    @Autowired
    private INbmhPurchasePublishService publishService;

    @Inner(false)
    @Operation(summary = "发布采购商品接口")
    @PostMapping("/publishBuyGoods")
    public Result publishBuyGoods(@RequestBody NbmhPurchasePublish purchasePublish){
        boolean ret = publishService.save(purchasePublish);
        if(ret){
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Inner(false)
    @Operation(summary = "采购发布列表查询接口")
    @PostMapping("/publishList")
    public Result<IPage<PublishListReturn>> queryPublishList(@RequestBody PruchasePublishParam publishParam,
                                                            @RequestParam("pageSize") Integer pageSize, @RequestParam("pageNum") Integer pageNum){

        IPage<PublishListReturn> page = new Page<>(pageNum, pageSize);
        IPage<PublishListReturn> ret = publishService.queryPublishPageList(page, publishParam);
        return Result.ok(ret);
    }
}
