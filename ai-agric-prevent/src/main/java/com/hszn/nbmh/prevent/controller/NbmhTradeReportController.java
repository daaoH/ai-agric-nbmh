package com.hszn.nbmh.prevent.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.NbmhTradeReport;
import com.hszn.nbmh.prevent.service.INbmhTradeReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
 * 活体交易信息表 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-08-15
 */
@Tag(name="活体交易信息报备")
@RestController
@RequestMapping("/nbmh-trade-report")
public class NbmhTradeReportController {

    @Autowired
    private INbmhTradeReportService tradeReportService;

    @Operation(summary = "新增活体交易信息报备", method = "POST")
    @PostMapping("/add")
    @Inner(false)
    public Result add(@RequestBody NbmhTradeReport nbmhTradeReport){

        List<Integer> idList = tradeReportService.save(Collections.singletonList(nbmhTradeReport));
        if(idList != null && idList.size()>0){
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询活体交易信息报备", method = "POST")
    @Parameters({@Parameter(description="活体交易信息报备Id", name="id")})
    @PostMapping("/{id}")
    @Inner(false)
    public Result<NbmhTradeReport> getById(@PathVariable(value = "id") @NotBlank Long id) {

        return Result.ok(tradeReportService.getById(id));
    }

    @Operation(summary = "更新活体交易信息报备", method = "PUT")
    @PutMapping
    @Inner(false)
    public Result update(@RequestBody NbmhTradeReport nbmhTradeReport) {

        tradeReportService.update(Collections.singletonList(nbmhTradeReport));
        return Result.ok();
    }

    @Operation(summary = "分页查询活体交易信息", method = "POST")
    @Parameters({@Parameter(description="页码", name="pageNum"), @Parameter(description="每页显示条数", name="pageSize"), @Parameter(description="排序条件集合", name="orderItemList")})
    @PostMapping("/query")
    @Inner(false)
    public Result<IPage<NbmhTradeReport>> query(@RequestBody NbmhTradeReport nbmhTradeReport,
                                                @RequestParam @DecimalMin("1") int pageNum,
                                                @RequestParam @DecimalMin("1") int pageSize,
                                                @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        IPage<NbmhTradeReport> butcherReportPage = tradeReportService.query(nbmhTradeReport, pageNum, pageSize, orderItemList);

        return Result.ok(butcherReportPage);
    }

    @Operation(summary = "查询活体交易信息", method = "POST")
    @Parameters({@Parameter(description="排序条件集合", name="orderItemList")})
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhTradeReport>> list(@RequestBody NbmhTradeReport nbmhTradeReport,
                                              @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        return Result.ok(tradeReportService.list(nbmhTradeReport, orderItemList));
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation(value="删除活体交易信息报备")
    @Inner(false)
    public Result delete(@PathVariable Long id) {

        tradeReportService.delete(Collections.singletonList(id));
        return Result.ok();
    }

}
