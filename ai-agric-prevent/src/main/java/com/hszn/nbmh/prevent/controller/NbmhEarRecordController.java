package com.hszn.nbmh.prevent.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.NbmhEarRecord;
import com.hszn.nbmh.prevent.api.params.input.NbmhEarRecordParam;
import com.hszn.nbmh.prevent.service.INbmhEarRecordService;
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
 * 耳标补打信息表 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-08-16
 */
@Tag(name = "耳标补打信息")
@RestController
@RequestMapping("/nbmh-ear-record")
public class NbmhEarRecordController {

    @Autowired
    private INbmhEarRecordService nbmhEarRecordService;


    @Operation(summary = "新增耳标补打信息", method = "POST")
    @PostMapping("/add")
    @Inner(false)
    public Result add(@RequestBody NbmhEarRecord nbmhEarRecord) {

        List<Integer> idList = nbmhEarRecordService.save(Collections.singletonList(nbmhEarRecord));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询耳标补打信息", method = "GET")
    @Parameters({@Parameter(description = "耳标补打信息Id", name = "id")})
    @PostMapping("/{id}")
    @Inner(false)
    public Result<NbmhEarRecord> getById(@PathVariable(value = "id") @NotBlank Long id) {

        return Result.ok(nbmhEarRecordService.getById(id));
    }

    @Operation(summary = "更新耳标补打信息", method = "PUT")
    @PutMapping
    @Inner(false)
    public Result update(@RequestBody NbmhEarRecord nbmhEarRecord) {

        nbmhEarRecordService.update(Collections.singletonList(nbmhEarRecord));
        return Result.ok();
    }

    @Operation(summary = "分页查询耳标补打信息", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize"), @Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/query")
    @Inner(false)
    public Result<IPage<NbmhEarRecord>> query(@RequestBody NbmhEarRecordParam nbmhEarRecordParam,
                                              @RequestParam @DecimalMin("1") int pageNum,
                                              @RequestParam @DecimalMin("1") int pageSize,
                                              @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        IPage<NbmhEarRecord> butcherReportPage = nbmhEarRecordService.query(nbmhEarRecordParam, pageNum, pageSize, orderItemList);

        return Result.ok(butcherReportPage);
    }

    @Operation(summary = "查询耳标补打信息", method = "POST")
    @Parameters({@Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhEarRecord>> list(@RequestBody NbmhEarRecord nbmhEarRecord,
                                            @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        return Result.ok(nbmhEarRecordService.list(nbmhEarRecord, orderItemList));
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "删除耳标补打信息")
    @Inner(false)
    public Result delete(@PathVariable Long id) {

        nbmhEarRecordService.delete(Collections.singletonList(id));
        return Result.ok();
    }

}
