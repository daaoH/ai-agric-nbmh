package com.hszn.nbmh.prevent.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.NbmhButcherReport;
import com.hszn.nbmh.prevent.api.params.out.ButcherStatisticsResult;
import com.hszn.nbmh.prevent.api.params.out.NbmhButcherReportDetail;
import com.hszn.nbmh.prevent.service.INbmhButcherReportService;
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
 * 屠宰/无害化申报信息表 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-08-15
 */

@Tag(name = "屠宰/无害化报备")
//@Validated
@RestController
@RequestMapping("/nbmh-butcher-report")
public class NbmhButcherReportController {

    @Autowired
    private INbmhButcherReportService butcherReportService;

    @Operation(summary = "新增屠宰/无害化申报信息", method = "POST")
    @PostMapping("/add")
    @Inner(false)
    public Result add(@RequestBody NbmhButcherReport nbmhButcherReport) {

        List<Integer> idList = butcherReportService.save(Collections.singletonList(nbmhButcherReport));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询屠宰/无害化申报信息", method = "POST")
    @Parameters({@Parameter(description = "屠宰/无害化申报信息记录Id", name = "id")})
    @PostMapping("/{id}")
    @Inner(false)
    public Result<NbmhButcherReport> getById(@PathVariable(value = "id") @NotBlank Long id) {

        return Result.ok(butcherReportService.getById(id));
    }

    @Operation(summary = "更新屠宰/无害化申报信息", method = "PUT")
    @PutMapping
    @Inner(false)
    public Result update(@RequestBody NbmhButcherReport nbmhButcherReport) {

        butcherReportService.update(Collections.singletonList(nbmhButcherReport));
        return Result.ok();
    }

    @Operation(summary = "分页查询屠宰/无害化申报信息", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize"), @Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/query")
    @Inner(false)
    public Result<IPage<NbmhButcherReport>> query(@RequestBody NbmhButcherReport nbmhButcherReport,
                                                  @RequestParam @DecimalMin("1") int pageNum,
                                                  @RequestParam @DecimalMin("1") int pageSize,
                                                  @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        IPage<NbmhButcherReport> butcherReportPage = butcherReportService.query(nbmhButcherReport, pageNum, pageSize, orderItemList);

        return Result.ok(butcherReportPage);
    }

    @Operation(summary = "查询屠宰/无害化申报信息", method = "POST")
    @Parameters({@Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhButcherReport>> list(@RequestBody NbmhButcherReport nbmhButcherReport,
                                                @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        return Result.ok(butcherReportService.list(nbmhButcherReport, orderItemList));
    }

    @Operation(summary = "删除屠宰/无害化申报信息")
    @DeleteMapping("delete/{id}")
    @Inner(false)
    public Result delete(@PathVariable Long id) {

        butcherReportService.delete(Collections.singletonList(id));
        return Result.ok();
    }

    @Operation(summary = "统计屠宰/无害化申报数据", method = "POST")
    @PostMapping("/statistics")
    @Inner(false)
    public Result<ButcherStatisticsResult> statistics(@RequestBody NbmhButcherReport nbmhButcherReport) {

        return Result.ok(butcherReportService.statistics(nbmhButcherReport));
    }

    @Operation(summary = "防疫站工作人员根据记录ID查询屠宰/无害化申报信息", method = "POST")
    @PostMapping("/detail")
    @Inner(false)
    public Result<NbmhButcherReportDetail> detail(@RequestBody NbmhButcherReport nbmhButcherReport) {

        return Result.ok(butcherReportService.detail(nbmhButcherReport));
    }

}
