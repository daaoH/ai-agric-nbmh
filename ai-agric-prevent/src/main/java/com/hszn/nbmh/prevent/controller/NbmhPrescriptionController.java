package com.hszn.nbmh.prevent.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryCondition;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.NbmhPrescription;
import com.hszn.nbmh.prevent.service.INbmhPrescriptionService;
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
 * 处方基础信息表 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-09-06
 */
@Tag(name = "处方基础信息表")
@Validated
@RestController
@RequestMapping("/nbmh-prescription")
public class NbmhPrescriptionController {

    @Autowired
    private INbmhPrescriptionService nbmhPrescriptionService;

    @Operation(summary = "新增处方基础信息", method = "POST")
    @PostMapping("/add")
    public Result add(@RequestBody @Validated({NbmhPrescription.Save.class}) NbmhPrescription nbmhPrescription) {

        List<Integer> idList = nbmhPrescriptionService.save(Collections.singletonList(nbmhPrescription));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询处方基础信息", method = "GET")
    @PostMapping("/{id}")
    public Result<NbmhPrescription> getById(@PathVariable(value = "id") @NotNull(message = "处方基础信息Id不能为空") Long id) {

        return Result.ok(nbmhPrescriptionService.getById(id));
    }

    @Operation(summary = "更新处方基础信息", method = "PUT")
    @PutMapping
    public Result update(@RequestBody @Validated({NbmhPrescription.Update.class}) NbmhPrescription nbmhPrescription) {

        nbmhPrescriptionService.update(Collections.singletonList(nbmhPrescription));
        return Result.ok();
    }

    @Operation(summary = "分页查询处方基础信息", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize")})
    @PostMapping("/query")
    public Result<IPage<NbmhPrescription>> query(@RequestBody QueryCondition<NbmhPrescription> queryCondition,
                                                 @RequestParam @DecimalMin("1") int pageNum,
                                                 @RequestParam @DecimalMin("1") int pageSize) {

        return Result.ok(nbmhPrescriptionService.query(queryCondition.getEntity(), pageNum, pageSize, queryCondition.getOrderItemList()));
    }

    @Operation(summary = "查询处方基础信息", method = "POST")
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhPrescription>> list(@RequestBody QueryCondition<NbmhPrescription> queryCondition) {

        return Result.ok(nbmhPrescriptionService.list(queryCondition.getEntity(), queryCondition.getOrderItemList()));
    }

    @Operation(summary = "删除处方基础信息", method = "DELETE")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable(value = "id") @NotNull(message = "处方基础信息Id不能为空") Long id) {

        nbmhPrescriptionService.delete(Collections.singletonList(id));
        return Result.ok();
    }


}
