package com.hszn.nbmh.cms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.cms.api.entity.NbmhArticleType;
import com.hszn.nbmh.cms.service.INbmhArticleTypeService;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 文章类型表 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-08-20
 */
@Tag(name = "文章类型管理")
@RestController
@RequestMapping("/nbmh-article-type")
public class NbmhArticleTypeController {

    @Autowired
    private INbmhArticleTypeService nbmhArticleTypeService;

    @Operation(summary = "新增文章类型", method = "POST")
    @PostMapping("/add")
    public Result add(@RequestBody NbmhArticleType nbmhArticleType) {

        List<Integer> idList = nbmhArticleTypeService.save(Collections.singletonList(nbmhArticleType));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询文章类型", method = "GET")
    @Parameters({@Parameter(description = "文章类型Id", name = "id")})
    @GetMapping("/{id}")
    public Result<NbmhArticleType> getById(@PathVariable(value = "id") @NotBlank Long id) {

        return Result.ok(nbmhArticleTypeService.getById(id));
    }

    @Operation(summary = "更新文章类型", method = "PUT")
    @PutMapping
    public Result update(@RequestBody NbmhArticleType nbmhArticleType) {

        nbmhArticleTypeService.update(Collections.singletonList(nbmhArticleType));
        return Result.ok();
    }

    @Operation(summary = "分页查询文章类型", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize"), @Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/query")
    public Result<IPage<NbmhArticleType>> query(@RequestBody NbmhArticleType nbmhArticleType,
                                                @RequestParam @DecimalMin("1") int pageNum,
                                                @RequestParam @DecimalMin("1") int pageSize,
                                                @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        IPage<NbmhArticleType> butcherReportPage = nbmhArticleTypeService.query(nbmhArticleType, pageNum, pageSize, orderItemList);

        return Result.ok(butcherReportPage);
    }

    @Operation(summary = "查询文章类型", method = "POST")
    @Parameters({@Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/list")
    public Result<List<NbmhArticleType>> list(@RequestBody NbmhArticleType NbmhArticleType,
                                              @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        return Result.ok(nbmhArticleTypeService.list(NbmhArticleType, orderItemList));
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "删除文章类型")
    public Result delete(@PathVariable Long id) {

        nbmhArticleTypeService.delete(Collections.singletonList(id));
        return Result.ok();
    }

}
