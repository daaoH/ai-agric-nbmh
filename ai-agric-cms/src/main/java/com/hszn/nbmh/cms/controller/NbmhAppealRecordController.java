package com.hszn.nbmh.cms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.cms.api.entity.NbmhAppealRecord;
import com.hszn.nbmh.cms.service.INbmhAppealRecordService;
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
 * 申诉记录表 前端控制器
 * </p>
 *
 * @author pyq
 * @since 2022-09-01
 */
@Tag(name = "申诉记录")
@RestController
@RequestMapping("/nbmh-appeal-record")
public class NbmhAppealRecordController {

    @Autowired
    private INbmhAppealRecordService nbmhAppealRecordService;

    @Operation(summary = "新增申诉", method = "POST")
    @PostMapping("/add")
    @Inner(false)
    public Result add(@RequestBody NbmhAppealRecord nbmhAppealRecord) {

        List<Integer> idList = nbmhAppealRecordService.save(Collections.singletonList(nbmhAppealRecord));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询申诉信息", method = "GET")
    @Parameters({@Parameter(description = "申诉信息Id", name = "id")})
    @GetMapping("/{id}")
    @Inner(false)
    public Result<NbmhAppealRecord> getById(@PathVariable(value = "id") @NotBlank Long id) {

        return Result.ok(nbmhAppealRecordService.getById(id));
    }

    @Operation(summary = "更新申诉信息", method = "PUT")
    @PutMapping
    @Inner(false)
    public Result update(@RequestBody NbmhAppealRecord nbmhAppealRecord) {

        nbmhAppealRecordService.update(Collections.singletonList(nbmhAppealRecord));
        return Result.ok();
    }

    @Operation(summary = "分页查询申诉", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize"), @Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/query")
    @Inner(false)
    public Result<IPage<NbmhAppealRecord>> query(@RequestBody NbmhAppealRecord nbmhAppealRecord,
                                       @RequestParam @DecimalMin("1") int pageNum,
                                       @RequestParam @DecimalMin("1") int pageSize,
                                       @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        IPage<NbmhAppealRecord> butcherReportPage = nbmhAppealRecordService.query(nbmhAppealRecord, pageNum, pageSize, orderItemList);

        return Result.ok(butcherReportPage);
    }

    @Operation(summary = "查询申诉", method = "POST")
    @Parameters({@Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhAppealRecord>> list(@RequestBody NbmhAppealRecord nbmhAppealRecord,
                                     @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        return Result.ok(nbmhAppealRecordService.list(nbmhAppealRecord, orderItemList));
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "删除申诉")
    @Inner(false)
    public Result delete(@PathVariable Long id) {

        nbmhAppealRecordService.delete(Collections.singletonList(id));
        return Result.ok();
    }

}


