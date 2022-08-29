package com.hszn.nbmh.good.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.good.api.entity.NbmhComment;
import com.hszn.nbmh.good.api.entity.NbmhGoods;
import com.hszn.nbmh.good.api.entity.NbmhGoodsAttribute;
import com.hszn.nbmh.good.api.params.out.GoodDetailReturn;
import com.hszn.nbmh.good.api.params.vo.CommentVo;
import com.hszn.nbmh.good.service.*;
import com.hszn.nbmh.user.api.entity.NbmhUserFootprint;
import com.hszn.nbmh.user.api.feign.RemoteFootprintService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.bytebuddy.asm.Advice;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.*;

/**
 * <p>
 * 商品基本信息表 前端控制器
 * </p>
 *
 * @author yuan
 * @since 2022-08-25
 */
@Tag(name = "nbmh-goods", description = "商品信息接口")
@RestController
@RequestMapping("/nbmh-goods")
public class NbmhGoodsController {

    @Autowired
    private INbmhGoodsCategoryService categoryService;

    @Autowired
    private INbmhGoodsAttributeService attributeService;

    @Autowired
    private INbmhGoodsService goodsService;

    @Autowired
    private INbmhGoodsSpecificationService specService;

    @Autowired
    private INbmhCommentService commentService;

    @Autowired
    private RemoteFootprintService footprintService;

    @Autowired
    private Executor executor;

    @Inner(false)
    @Operation(description = "获取商品详细信息")
    @Parameter(description = "商品id")
    @PostMapping("/queryGoodDetail")
    public Result<GoodDetailReturn> queryGoodDetail(@RequestParam("goodId") Long goodId){

        //获取商品信息
        NbmhGoods good = goodsService.getById(goodId);

        //获取商品属性
        Callable<List> goodAttributeCallable = () -> attributeService.list(Wrappers.<NbmhGoodsAttribute>query().lambda().eq(NbmhGoodsAttribute::getGoodsId, goodId).eq(NbmhGoodsAttribute::getStatus, 0));

        //获取商品规格
        Callable<List> specsCallable = () -> specService.getSpecsVoList(goodId);

        //评论
        Callable<CommentVo> commentsCallable = () -> {
            IPage<NbmhComment> pages = commentService.page(new Page<NbmhComment>(1, 3), Wrappers.<NbmhComment>query().lambda().eq(NbmhComment::getThingId, goodId).eq(NbmhComment::getType, 0).eq(NbmhComment::getStatus, 0));

            if(ObjectUtils.isNotEmpty(pages)) {
                CommentVo commentVo = new CommentVo();
                commentVo.setTotal(pages.getTotal());
                commentVo.setComment(pages.getRecords());
                return commentVo;
            }
            return null;
        };

        //异步记录用户足迹
        executor.execute(() -> {
            NbmhUserFootprint footprint = new NbmhUserFootprint();
            footprint.setGoodsId(goodId);
            footprint.setUserId(1L);
            footprint.setCreateTime(new Date());
            footprint.setUpdateTime(new Date());
            footprintService.addFootprint(footprint);
        });

        FutureTask<List> goodAttributeTask = new FutureTask<>(goodAttributeCallable);
        FutureTask<List> specsTask = new FutureTask<>(specsCallable);
        FutureTask<CommentVo> commentsTask = new FutureTask<CommentVo>(commentsCallable);

        executor.execute(goodAttributeTask);
        executor.execute(specsTask);
        executor.execute(commentsTask);

        GoodDetailReturn data = new GoodDetailReturn();
        try{
            data.setGoods(good);
            data.setGoodsAttribute(goodAttributeTask.get());
            data.setSpecification(specsTask.get());
            data.setComments(commentsTask.get());

        }catch (Exception e){
            return Result.failed(e.getMessage());
        }

        return Result.ok(data);
    }


    @Inner(false)
    @Operation(description = "根据类别id获取商品列表")
    @Parameter(description = "商品类别id")
    @PostMapping("/queryGoodsByCategoryId")
    public Result<List<NbmhGoods>> queryGoodsByCategoryId(@RequestParam("categoryId") Integer categoryId){
        List<NbmhGoods> goods = goodsService.list(Wrappers.<NbmhGoods>query().lambda().eq(NbmhGoods::getCategoryId, categoryId).eq(NbmhGoods::getStatus, 0));
        if(CollectionUtils.isNotEmpty(goods)){
            return Result.ok(goods);
        }

        return Result.failed(CommonEnum.DATA_NOT_EXIST.getMsg());
    }
}
