package com.hszn.nbmh.prevent.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryCondition;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.NbmhMedicalOrder;
import com.hszn.nbmh.prevent.api.params.input.NbmhMedicalOrderParam;
import com.hszn.nbmh.prevent.service.INbmhMedicalOrderService;
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
 * 诊断下单记录 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-08-31
 */
@Tag(name = "诊断下单记录管理")
@Validated
@RestController
@RequestMapping("/nbmh-medical-order")
public class NbmhMedicalOrderController {

    @Autowired
    private INbmhMedicalOrderService nbmhMedicalOrderService;

    @Operation(summary = "新增诊断下单记录", method = "POST")
    @PostMapping("/add")
    @Inner(false)
    public Result add(@RequestBody @Validated({NbmhMedicalOrder.Save.class}) NbmhMedicalOrderParam medicalOrderParam) {

        List<Integer> idList = nbmhMedicalOrderService.save(Collections.singletonList(medicalOrderParam));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询诊断下单记录", method = "GET")
    @PostMapping("/{id}")
    @Inner(false)
    public Result<NbmhMedicalOrder> getById(@PathVariable(value = "id") @NotNull(message = "动物诊断下单记录Id不能为空") Long id) {

        return Result.ok(nbmhMedicalOrderService.getById(id));
    }

    @Operation(summary = "更新诊断下单记录", method = "PUT")
    @PutMapping
    @Inner(false)
    public Result update(@RequestBody @Validated({NbmhMedicalOrder.Update.class}) NbmhMedicalOrder nbmhMedicalOrder) {

        nbmhMedicalOrderService.update(Collections.singletonList(nbmhMedicalOrder));
        return Result.ok();
    }

    @Operation(summary = "分页查询诊断下单记录", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize")})
    @PostMapping("/query")
    @Inner(false)
    public Result<IPage<NbmhMedicalOrder>> query(@RequestBody QueryCondition<NbmhMedicalOrder> queryCondition,
                                                 @RequestParam @DecimalMin("1") int pageNum,
                                                 @RequestParam @DecimalMin("1") int pageSize) {

        return Result.ok(nbmhMedicalOrderService.query(queryCondition.getEntity(), pageNum, pageSize, queryCondition.getOrderItemList()));
    }

    @Operation(summary = "查询诊断下单记录", method = "POST")
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhMedicalOrder>> list(@RequestBody QueryCondition<NbmhMedicalOrder> queryCondition) {

        return Result.ok(nbmhMedicalOrderService.list(queryCondition.getEntity(), queryCondition.getOrderItemList()));
    }

    @Operation(summary = "删除诊断下单记录", method = "DELETE")
    @DeleteMapping("delete/{id}")
    @Inner(false)
    public Result delete(@PathVariable(value = "id") @NotNull(message = "动物诊断下单记录Id不能为空") Long id) {

        nbmhMedicalOrderService.delete(Collections.singletonList(id));
        return Result.ok();
    }


}
