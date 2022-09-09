package com.hszn.nbmh.cms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.cms.api.entity.NbmhArticle;
import com.hszn.nbmh.cms.service.INbmhArticleService;
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
 * 文章信息表 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-08-20
 */
@Tag(name = "文章信息管理")
@Validated
@RestController
@RequestMapping("/nbmh-article")
public class NbmhArticleController {

    @Autowired
    private INbmhArticleService nbmhArticleService;

    @Operation(summary = "新增文章信息", method = "POST")
    @PostMapping("/add")
    public Result add(@RequestBody @Validated({NbmhArticle.Save.class}) NbmhArticle nbmhArticle) {

        List<Integer> idList = nbmhArticleService.save(Collections.singletonList(nbmhArticle));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询文章信息", method = "GET")
    @Parameters({@Parameter(description = "文章信息Id", name = "id")})
    @GetMapping("/{id}")
    public Result<NbmhArticle> getById(@PathVariable(value = "id") @NotNull(message = "文章信息id不能为空") Long id) {

        return Result.ok(nbmhArticleService.getById(id));
    }

    @Operation(summary = "更新文章信息", method = "PUT")
    @PutMapping
    public Result update(@RequestBody @Validated({NbmhArticle.Update.class}) NbmhArticle nbmhArticle) {

        nbmhArticleService.update(Collections.singletonList(nbmhArticle));
        return Result.ok();
    }

    @Operation(summary = "分页查询文章信息", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize")})
    @PostMapping("/query")
    public Result<IPage<NbmhArticle>> query(@RequestBody QueryCondition<NbmhArticle> queryCondition,
                                            @RequestParam @DecimalMin("1") int pageNum,
                                            @RequestParam @DecimalMin("1") int pageSize) {

        return Result.ok(nbmhArticleService.query(queryCondition.getEntity(), pageNum, pageSize, queryCondition.getOrderItemList()));
    }

    @Operation(summary = "查询文章信息", method = "POST")
    @PostMapping("/list")
    public Result<List<NbmhArticle>> list(@RequestBody QueryCondition<NbmhArticle> queryCondition) {

        return Result.ok(nbmhArticleService.list(queryCondition.getEntity(), queryCondition.getOrderItemList()));
    }

    @Operation(summary = "删除文章信息", method = "DELETE")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable(value = "id") @NotNull(message = "文章信息id不能为空") Long id) {

        nbmhArticleService.delete(Collections.singletonList(id));
        return Result.ok();
    }

}
