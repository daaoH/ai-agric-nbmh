package com.hszn.nbmh.good.controller;


import com.alibaba.nacos.common.utils.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.good.api.entity.NbmhGoodsCategory;
import com.hszn.nbmh.good.service.INbmhGoodsCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 类目表 前端控制器
 * </p>
 *
 * @author yuan
 * @since 2022-08-25
 */

@Tag(name = "goods-category", description = "商品类目接口")
@RestController
@RequestMapping("/goods-category")
public class NbmhGoodsCategoryController {

    @Autowired
    private INbmhGoodsCategoryService categoryService;

    @Inner(false)
    @Operation(description = "获取一级分类")
    @GetMapping("/getFirstCategory")
    public Result<List<NbmhGoodsCategory>> getFirstCategory(){
        List<NbmhGoodsCategory> categorys = categoryService.list(Wrappers.<NbmhGoodsCategory>query().lambda().eq(NbmhGoodsCategory::getLevel, "L1").eq(NbmhGoodsCategory::getStatus, 0));
        if(CollectionUtils.isNotEmpty(categorys)){
            return Result.ok(categorys);
        }

        return Result.failed(CommonEnum.DATA_NOT_EXIST.getMsg());
    }


    @Inner(false)
    @Operation(description = "获取二级分类")
    @Parameter(description = "二级分类父id")
    @GetMapping("/getSecondCategory")
    public Result<List<NbmhGoodsCategory>> getSecondCategory(@NotNull @RequestParam("pid") Integer pid){
        List<NbmhGoodsCategory> categorys = categoryService.list(Wrappers.<NbmhGoodsCategory>query().lambda().eq(NbmhGoodsCategory::getPid, pid).eq(NbmhGoodsCategory::getStatus, 0));
        if(CollectionUtils.isNotEmpty(categorys)){
            return Result.ok(categorys);
        }

        return Result.failed(CommonEnum.DATA_NOT_EXIST.getMsg());
    }

}
