package com.hszn.nbmh.prevent.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.NbmhMedicalOrder;
import com.hszn.nbmh.prevent.service.INbmhMedicalOrderService;
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
 * 诊断下单记录 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-08-31
 */
@Tag(name = "诊断下单记录管理")
@RestController
@RequestMapping("/nbmh-medical-order")
public class NbmhMedicalOrderController {

    @Autowired
    private INbmhMedicalOrderService nbmhMedicalOrderService;

    @Operation(summary = "新增诊断下单记录", method = "POST")
    @PostMapping("/add")
    @Inner(false)
    public Result add(@RequestBody NbmhMedicalOrder nbmhMedicalOrder) {

        List<Integer> idList = nbmhMedicalOrderService.save(Collections.singletonList(nbmhMedicalOrder));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询诊断下单记录", method = "GET")
    @PostMapping("/{id}")
    @Inner(false)
    public Result<NbmhMedicalOrder> getById(@PathVariable(value = "id") @NotBlank Long id) {

        return Result.ok(nbmhMedicalOrderService.getById(id));
    }

    @Operation(summary = "更新诊断下单记录", method = "PUT")
    @PutMapping
    @Inner(false)
    public Result update(@RequestBody NbmhMedicalOrder nbmhMedicalOrder) {

        nbmhMedicalOrderService.update(Collections.singletonList(nbmhMedicalOrder));
        return Result.ok();
    }

    @Operation(summary = "分页查询诊断下单记录", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize"), @Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/query")
    @Inner(false)
    public Result<IPage<NbmhMedicalOrder>> query(@RequestBody NbmhMedicalOrder nbmhMedicalOrder,
                                                 @RequestParam @DecimalMin("1") int pageNum,
                                                 @RequestParam @DecimalMin("1") int pageSize,
                                                 @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        IPage<NbmhMedicalOrder> butcherReportPage = nbmhMedicalOrderService.query(nbmhMedicalOrder, pageNum, pageSize, orderItemList);

        return Result.ok(butcherReportPage);
    }

    @Operation(summary = "查询诊断下单记录", method = "POST")
    @Parameters({@Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhMedicalOrder>> list(@RequestBody NbmhMedicalOrder nbmhMedicalOrder,
                                               @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        return Result.ok(nbmhMedicalOrderService.list(nbmhMedicalOrder, orderItemList));
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "删除诊断下单记录")
    @Inner(false)
    public Result delete(@PathVariable Long id) {

        nbmhMedicalOrderService.delete(Collections.singletonList(id));
        return Result.ok();
    }


}
