package com.hszn.nbmh.cms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.cms.api.entity.NbmhArticle;
import com.hszn.nbmh.cms.service.INbmhArticleService;
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
 * 文章信息表 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-08-20
 */
@Tag(name = "文章信息管理")
@RestController
@RequestMapping("/nbmh-article")
public class NbmhArticleController {

    @Autowired
    private INbmhArticleService nbmhArticleService;

    @Operation(summary = "新增文章信息", method = "POST")
    @PostMapping("/add")
    public Result add(@RequestBody NbmhArticle nbmhArticle) {

        List<Integer> idList = nbmhArticleService.save(Collections.singletonList(nbmhArticle));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询文章信息", method = "GET")
    @Parameters({@Parameter(description = "文章信息Id", name = "id")})
    @GetMapping("/{id}")
    public Result<NbmhArticle> getById(@PathVariable(value = "id") @NotBlank Long id) {

        return Result.ok(nbmhArticleService.getById(id));
    }

    @Operation(summary = "更新文章信息", method = "PUT")
    @PutMapping
    public Result update(@RequestBody NbmhArticle nbmhArticle) {

        nbmhArticleService.update(Collections.singletonList(nbmhArticle));
        return Result.ok();
    }

    @Operation(summary = "分页查询文章信息", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize"), @Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/query")
    public Result<IPage<NbmhArticle>> query(@RequestBody NbmhArticle nbmhArticle,
                                            @RequestParam @DecimalMin("1") int pageNum,
                                            @RequestParam @DecimalMin("1") int pageSize,
                                            @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        IPage<NbmhArticle> butcherReportPage = nbmhArticleService.query(nbmhArticle, pageNum, pageSize, orderItemList);

        return Result.ok(butcherReportPage);
    }

    @Operation(summary = "查询文章信息", method = "POST")
    @Parameters({@Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/list")
    public Result<List<NbmhArticle>> list(@RequestBody NbmhArticle NbmhArticle,
                                          @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        return Result.ok(nbmhArticleService.list(NbmhArticle, orderItemList));
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "删除文章信息")
    public Result delete(@PathVariable Long id) {

        nbmhArticleService.delete(Collections.singletonList(id));
        return Result.ok();
    }

}
