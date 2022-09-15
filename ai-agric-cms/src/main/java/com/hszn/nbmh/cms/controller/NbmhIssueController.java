package com.hszn.nbmh.cms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.cms.api.entity.NbmhIssue;
import com.hszn.nbmh.cms.service.INbmhIssueService;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
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
 * 常见问题表 前端控制器
 * </p>
 *
 * @author pyq
 * @since 2022-09-05
 */
@Tag(name = "常见问题管理")
@RestController
@RequestMapping("/nbmh-issue")
public class NbmhIssueController {
    @Autowired
    private INbmhIssueService nbmhIssueService;

    @Operation(summary = "新增问题", method = "POST")
    @PostMapping("/add")
    @Inner(false)
    public Result add(@RequestBody NbmhIssue nbmhIssue) {

       boolean  ret = nbmhIssueService.save(nbmhIssue);
        if (ret) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询问题", method = "GET")
    @Parameters({@Parameter(description = "问题Id", name = "id")})
    @GetMapping("/{id}")
    @Inner(false)
    public Result<NbmhIssue> getById(@PathVariable(value = "id") @NotBlank Long id) {

        return Result.ok(nbmhIssueService.getById(id));
    }

    @Operation(summary = "更新问题", method = "PUT")
    @PutMapping
    @Inner(false)
    public Result update(@RequestBody NbmhIssue nbmhIssue) {

        nbmhIssueService.update(Collections.singletonList(nbmhIssue));
        return Result.ok();
    }

    @Operation(summary = "分页查询问题", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize"), @Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/query")
    @Inner(false)
    public Result<IPage<NbmhIssue>> query(@RequestBody NbmhIssue nbmhIssue,
                                       @RequestParam @DecimalMin("1") int pageNum,
                                       @RequestParam @DecimalMin("1") int pageSize,
                                       @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        IPage<NbmhIssue> butcherReportPage = nbmhIssueService.query(nbmhIssue, pageNum, pageSize, orderItemList);

        return Result.ok(butcherReportPage);
    }

    @Operation(summary = "查询问题", method = "POST")
    @Parameters({@Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhIssue>> list(@RequestBody NbmhIssue nbmhIssue,
                                     @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        return Result.ok(nbmhIssueService.list(nbmhIssue, orderItemList));
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "删除问题")
    @Inner(false)
    public Result delete(@PathVariable Long id) {

        nbmhIssueService.delete(Collections.singletonList(id));
        return Result.ok();
    }

}
