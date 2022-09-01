package com.hszn.nbmh.shop.controller;


import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.shop.api.entity.NbmhOperateCategory;
import com.hszn.nbmh.shop.api.params.input.OperateCategoryParam;
import com.hszn.nbmh.shop.service.INbmhOperateCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 开店经营类目表 前端控制器
 * </p>
 *
 * @author lw
 * @since 2022-09-01
 */
@Tag(name = "operate-category", description = "经营类目接口")
@RestController
@RequestMapping("/operate-category")
public class NbmhOperateCategoryController {

    private final INbmhOperateCategoryService categoryService;

    public NbmhOperateCategoryController(INbmhOperateCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Inner(value = false)
    @GetMapping("/search")
    @Operation(description = "查询经营类目,默认返回第一层数据")
    public Result<List<NbmhOperateCategory>> search() {
        return Result.ok(categoryService.search(null));
    }


}
