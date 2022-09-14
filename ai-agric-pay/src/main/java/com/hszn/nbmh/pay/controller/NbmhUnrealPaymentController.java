package com.hszn.nbmh.pay.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryCondition;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.pay.api.entity.NbmhUnrealPayment;
import com.hszn.nbmh.pay.service.INbmhUnrealPaymentService;
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
 * 虚拟支付信息表 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-09-13
 */
@Tag(name = "虚拟支付信息记录")
@Validated
@RestController
@RequestMapping("/nbmh-unreal-payment")
public class NbmhUnrealPaymentController {
    @Autowired
    private INbmhUnrealPaymentService nbmhUnrealPaymentService;

    @Operation(summary = "新增虚拟支付信息记录", method = "POST")
    @PostMapping("/add")
    @Inner(false)
    public Result add(@RequestBody @Validated({NbmhUnrealPayment.Save.class}) NbmhUnrealPayment nbmhUnrealPayment) {

        List<Integer> idList = nbmhUnrealPaymentService.save(Collections.singletonList(nbmhUnrealPayment));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询虚拟支付信息记录", method = "GET")
    @PostMapping("/{id}")
    @Inner(false)
    public Result<NbmhUnrealPayment> getById(@PathVariable(value = "id") @NotNull(message = "动物虚拟支付信息记录Id不能为空") Long id) {

        return Result.ok(nbmhUnrealPaymentService.getById(id));
    }

    @Operation(summary = "更新虚拟支付信息记录", method = "PUT")
    @PutMapping
    @Inner(false)
    public Result update(@RequestBody @Validated({NbmhUnrealPayment.Update.class}) NbmhUnrealPayment nbmhUnrealPayment) {

        nbmhUnrealPaymentService.update(Collections.singletonList(nbmhUnrealPayment));
        return Result.ok();
    }

    @Operation(summary = "分页查询虚拟支付信息记录", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize")})
    @PostMapping("/query")
    @Inner(false)
    public Result<IPage<NbmhUnrealPayment>> query(@RequestBody QueryCondition<NbmhUnrealPayment> queryCondition,
                                                 @RequestParam @DecimalMin("1") int pageNum,
                                                 @RequestParam @DecimalMin("1") int pageSize) {

        return Result.ok(nbmhUnrealPaymentService.query(queryCondition.getEntity(), pageNum, pageSize, queryCondition.getOrderItemList()));
    }

    @Operation(summary = "查询虚拟支付信息记录", method = "POST")
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhUnrealPayment>> list(@RequestBody QueryCondition<NbmhUnrealPayment> queryCondition) {

        return Result.ok(nbmhUnrealPaymentService.list(queryCondition.getEntity(), queryCondition.getOrderItemList()));
    }

    @Operation(summary = "删除虚拟支付信息记录", method = "DELETE")
    @DeleteMapping("delete/{id}")
    @Inner(false)
    public Result delete(@PathVariable(value = "id") @NotNull(message = "动物虚拟支付信息记录Id不能为空") Long id) {

        nbmhUnrealPaymentService.delete(Collections.singletonList(id));
        return Result.ok();
    }

}
