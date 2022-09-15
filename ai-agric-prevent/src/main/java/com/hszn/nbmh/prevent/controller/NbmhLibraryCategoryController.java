package com.hszn.nbmh.prevent.controller;


import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.NbmhLibraryCategory;
import com.hszn.nbmh.prevent.service.INbmhLibraryCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 知识库分类前端控制器
 * </p>
 *
 * @author lw
 * @since 2022-09-14
 */
@RestController
@RequestMapping("/library-category")
public class NbmhLibraryCategoryController {
    private final INbmhLibraryCategoryService categoryService;

    public NbmhLibraryCategoryController(INbmhLibraryCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Inner(value = false)
    @GetMapping("/search")
    @Operation(description = "查询知识库类别")
    public Result<List<NbmhLibraryCategory>> search() {
        return Result.ok(categoryService.search(null));
    }
}
