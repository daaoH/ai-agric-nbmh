package com.hszn.nbmh.prevent.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryCondition;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.NbmhPrescriptionDrug;
import com.hszn.nbmh.prevent.service.INbmhPrescriptionDrugService;
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
 * 处方药品列表 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-09-06
 */
@Tag(name = "处方药品管理")
@Validated
@RestController
@RequestMapping("/nbmh-prescription-drug")
public class NbmhPrescriptionDrugController {

    @Autowired
    private INbmhPrescriptionDrugService nbmhPrescriptionDrugService;

    @Operation(summary = "新增处方药品", method = "POST")
    @PostMapping("/add")
    public Result add(@RequestBody @Validated({NbmhPrescriptionDrug.Save.class}) NbmhPrescriptionDrug nbmhPrescriptionDrug) {

        List<Integer> idList = nbmhPrescriptionDrugService.save(Collections.singletonList(nbmhPrescriptionDrug));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询处方药品", method = "GET")
    @PostMapping("/{id}")
    public Result<NbmhPrescriptionDrug> getById(@PathVariable(value = "id") @NotNull(message = "处方药品Id不能为空") Long id) {

        return Result.ok(nbmhPrescriptionDrugService.getById(id));
    }

    @Operation(summary = "更新处方药品", method = "PUT")
    @PutMapping
    public Result update(@RequestBody @Validated({NbmhPrescriptionDrug.Update.class}) NbmhPrescriptionDrug nbmhPrescriptionDrug) {

        nbmhPrescriptionDrugService.update(Collections.singletonList(nbmhPrescriptionDrug));
        return Result.ok();
    }

    @Operation(summary = "分页查询处方药品", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize")})
    @PostMapping("/query")
    public Result<IPage<NbmhPrescriptionDrug>> query(@RequestBody QueryCondition<NbmhPrescriptionDrug> queryCondition,
                                                     @RequestParam @DecimalMin("1") int pageNum,
                                                     @RequestParam @DecimalMin("1") int pageSize) {

        return Result.ok(nbmhPrescriptionDrugService.query(queryCondition.getEntity(), pageNum, pageSize, queryCondition.getOrderItemList()));
    }

    @Operation(summary = "查询处方药品", method = "POST")
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhPrescriptionDrug>> list(@RequestBody QueryCondition<NbmhPrescriptionDrug> queryCondition) {

        return Result.ok(nbmhPrescriptionDrugService.list(queryCondition.getEntity(), queryCondition.getOrderItemList()));
    }

    @Operation(summary = "删除处方药品", method = "DELETE")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable(value = "id") @NotNull(message = "处方药品Id不能为空") Long id) {

        nbmhPrescriptionDrugService.delete(Collections.singletonList(id));
        return Result.ok();
    }


}
