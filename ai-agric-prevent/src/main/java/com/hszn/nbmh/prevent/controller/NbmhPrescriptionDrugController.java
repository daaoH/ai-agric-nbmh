package com.hszn.nbmh.prevent.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.NbmhPrescriptionDrug;
import com.hszn.nbmh.prevent.service.INbmhPrescriptionDrugService;
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
 * 处方药品列表 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-09-06
 */
@Tag(name = "处方药品管理")
@RestController
@RequestMapping("/nbmh-prescription-drug")
public class NbmhPrescriptionDrugController {

    @Autowired
    private INbmhPrescriptionDrugService nbmhPrescriptionDrugService;

    @Operation(summary = "新增处方药品", method = "POST")
    @PostMapping("/add")
    public Result add(@RequestBody NbmhPrescriptionDrug nbmhPrescriptionDrug) {

        List<Integer> idList = nbmhPrescriptionDrugService.save(Collections.singletonList(nbmhPrescriptionDrug));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询处方药品", method = "GET")
    @PostMapping("/{id}")
    public Result<NbmhPrescriptionDrug> getById(@PathVariable(value = "id") @NotBlank Long id) {

        return Result.ok(nbmhPrescriptionDrugService.getById(id));
    }

    @Operation(summary = "更新处方药品", method = "PUT")
    @PutMapping
    public Result update(@RequestBody NbmhPrescriptionDrug nbmhPrescriptionDrug) {

        nbmhPrescriptionDrugService.update(Collections.singletonList(nbmhPrescriptionDrug));
        return Result.ok();
    }

    @Operation(summary = "分页查询处方药品", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize"), @Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/query")
    public Result<IPage<NbmhPrescriptionDrug>> query(@RequestBody NbmhPrescriptionDrug nbmhPrescriptionDrug,
                                                     @RequestParam @DecimalMin("1") int pageNum,
                                                     @RequestParam @DecimalMin("1") int pageSize,
                                                     @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        IPage<NbmhPrescriptionDrug> butcherReportPage = nbmhPrescriptionDrugService.query(nbmhPrescriptionDrug, pageNum, pageSize, orderItemList);

        return Result.ok(butcherReportPage);
    }

    @Operation(summary = "查询处方药品", method = "POST")
    @Parameters({@Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhPrescriptionDrug>> list(@RequestBody NbmhPrescriptionDrug nbmhPrescriptionDrug,
                                                   @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        return Result.ok(nbmhPrescriptionDrugService.list(nbmhPrescriptionDrug, orderItemList));
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "删除处方药品")
    public Result delete(@PathVariable Long id) {

        nbmhPrescriptionDrugService.delete(Collections.singletonList(id));
        return Result.ok();
    }


}
