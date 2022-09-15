package com.hszn.nbmh.cms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.cms.api.entity.NbmhFeedback;
import com.hszn.nbmh.cms.service.INbmhFeedbackService;
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
 * 意见反馈表 前端控制器
 * </p>
 *
 * @author pyq
 * @since 2022-09-03
 */
@Tag(name = "意见反馈")
@RestController
@RequestMapping("/nbmh-feedback")
public class NbmhFeedbackController {
    @Autowired
    private INbmhFeedbackService nbmhFeedbackService;

    @Operation(summary = "新增意见", method = "POST")
    @PostMapping("/add")
    @Inner(false)
    public Result add(@RequestBody NbmhFeedback nbmhFeedback) {

        List<Integer> idList = nbmhFeedbackService.save(Collections.singletonList(nbmhFeedback));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询意见反馈", method = "GET")
    @Parameters({@Parameter(description = "意见反馈Id", name = "id")})
    @GetMapping("/{id}")
    @Inner(false)
    public Result<NbmhFeedback> getById(@PathVariable(value = "id") @NotBlank Long id) {

        return Result.ok(nbmhFeedbackService.getById(id));
    }

    @Operation(summary = "更新意见反馈", method = "PUT")
    @PutMapping
    @Inner(false)
    public Result update(@RequestBody NbmhFeedback nbmhFeedback) {

        nbmhFeedbackService.update(Collections.singletonList(nbmhFeedback));
        return Result.ok();
    }

    @Operation(summary = "分页查询意见", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize"), @Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/query")
    @Inner(false)
    public Result<IPage<NbmhFeedback>> query(@RequestBody NbmhFeedback nbmhFeedback,
                                             @RequestParam @DecimalMin("1") int pageNum,
                                             @RequestParam @DecimalMin("1") int pageSize,
                                             @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        IPage<NbmhFeedback> butcherReportPage = nbmhFeedbackService.query(nbmhFeedback, pageNum, pageSize, orderItemList);

        return Result.ok(butcherReportPage);
    }

    @Operation(summary = "查询意见", method = "POST")
    @Parameters({@Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhFeedback>> list(@RequestBody NbmhFeedback nbmhFeedback,
                                           @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        return Result.ok(nbmhFeedbackService.list(nbmhFeedback, orderItemList));
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "删除意见")
    @Inner(false)
    public Result delete(@PathVariable Long id) {

        nbmhFeedbackService.delete(Collections.singletonList(id));
        return Result.ok();
    }
}
