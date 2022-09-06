package com.hszn.nbmh.prevent.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.NbmhPrescription;
import com.hszn.nbmh.prevent.service.INbmhPrescriptionService;
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
 * 处方基础信息表 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-09-06
 */
@Tag(name = "处方基础信息表")
@RestController
@RequestMapping("/nbmh-prescription")
public class NbmhPrescriptionController {

    @Autowired
    private INbmhPrescriptionService nbmhPrescriptionService;

    @Operation(summary = "新增处方基础信息", method = "POST")
    @PostMapping("/add")
    public Result add(@RequestBody NbmhPrescription nbmhPrescription) {

        List<Integer> idList = nbmhPrescriptionService.save(Collections.singletonList(nbmhPrescription));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询处方基础信息", method = "GET")
    @PostMapping("/{id}")
    public Result<NbmhPrescription> getById(@PathVariable(value = "id") @NotBlank Long id) {

        return Result.ok(nbmhPrescriptionService.getById(id));
    }

    @Operation(summary = "更新处方基础信息", method = "PUT")
    @PutMapping
    public Result update(@RequestBody NbmhPrescription nbmhPrescription) {

        nbmhPrescriptionService.update(Collections.singletonList(nbmhPrescription));
        return Result.ok();
    }

    @Operation(summary = "分页查询处方基础信息", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize"), @Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/query")
    public Result<IPage<NbmhPrescription>> query(@RequestBody NbmhPrescription nbmhPrescription,
                                                 @RequestParam @DecimalMin("1") int pageNum,
                                                 @RequestParam @DecimalMin("1") int pageSize,
                                                 @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        IPage<NbmhPrescription> butcherReportPage = nbmhPrescriptionService.query(nbmhPrescription, pageNum, pageSize, orderItemList);

        return Result.ok(butcherReportPage);
    }

    @Operation(summary = "查询处方基础信息", method = "POST")
    @Parameters({@Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhPrescription>> list(@RequestBody NbmhPrescription nbmhPrescription,
                                               @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        return Result.ok(nbmhPrescriptionService.list(nbmhPrescription, orderItemList));
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "删除处方基础信息")
    public Result delete(@PathVariable Long id) {

        nbmhPrescriptionService.delete(Collections.singletonList(id));
        return Result.ok();
    }


}
