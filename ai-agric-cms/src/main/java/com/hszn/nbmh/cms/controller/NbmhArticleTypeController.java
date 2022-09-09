package com.hszn.nbmh.cms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.cms.api.entity.NbmhArticleType;
import com.hszn.nbmh.cms.service.INbmhArticleTypeService;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryCondition;
import com.hszn.nbmh.common.core.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
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
@Validated
@RestController
@RequestMapping("/nbmh-article-type")
public class NbmhArticleTypeController {

    @Autowired
    private INbmhArticleTypeService nbmhArticleTypeService;

    @Operation(summary = "新增文章类型", method = "POST")
    @PostMapping("/add")
    public Result add(@RequestBody @Validated({NbmhArticleType.Save.class}) NbmhArticleType nbmhArticleType) {

        List<Integer> idList = nbmhArticleTypeService.save(Collections.singletonList(nbmhArticleType));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询文章类型", method = "GET")
    @Parameters({@Parameter(description = "文章类型Id", name = "id")})
    @GetMapping("/{id}")
    public Result<NbmhArticleType> getById(@PathVariable(value = "id") @NotNull(message = "文章类型id不能为空") Long id) {

        return Result.ok(nbmhArticleTypeService.getByTypeId(id));
    }

    @Operation(summary = "更新文章类型", method = "PUT")
    @PutMapping
    public Result update(@RequestBody @Validated({NbmhArticleType.Update.class}) NbmhArticleType nbmhArticleType) {

        nbmhArticleTypeService.update(Collections.singletonList(nbmhArticleType));
        return Result.ok();
    }

    @Operation(summary = "分页查询文章类型", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize")})
    @PostMapping("/query")
    public Result<IPage<NbmhArticleType>> query(@RequestBody QueryCondition<NbmhArticleType> queryCondition,
                                                @RequestParam @DecimalMin("1") int pageNum,
                                                @RequestParam @DecimalMin("1") int pageSize) {

        return Result.ok(nbmhArticleTypeService.query(queryCondition.getEntity(), pageNum, pageSize, queryCondition.getOrderItemList()));
    }

    @Operation(summary = "查询文章类型", method = "POST")
    @PostMapping("/list")
    public Result<List<NbmhArticleType>> list(@RequestBody QueryCondition<NbmhArticleType> queryCondition) {

        return Result.ok(nbmhArticleTypeService.list(queryCondition.getEntity(), queryCondition.getOrderItemList()));
    }

    @Operation(summary = "删除文章类型", method = "DELETE")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable(value = "id") @NotNull(message = "文章类型id不能为空") Long id) {

        nbmhArticleTypeService.delete(Collections.singletonList(id));
        return Result.ok();
    }

}
