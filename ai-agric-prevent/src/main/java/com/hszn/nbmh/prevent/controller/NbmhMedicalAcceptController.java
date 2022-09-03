package com.hszn.nbmh.prevent.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.NbmhMedicalAccept;
import com.hszn.nbmh.prevent.service.INbmhMedicalAcceptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 动物诊疗接单记录 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-08-31
 */
@RestController
@RequestMapping("/nbmh-medical-accept")
public class NbmhMedicalAcceptController {

    @Autowired
    private INbmhMedicalAcceptService NbmhMedicalAcceptService;

    @Operation(summary = "新增动物诊疗接单记录", method = "POST")
    @PostMapping("/add")
    @Inner(false)
    public Result add(@RequestBody NbmhMedicalAccept nbmhMedicalAccept) {

        List<Integer> idList = NbmhMedicalAcceptService.save(Collections.singletonList(nbmhMedicalAccept));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询动物诊疗接单记录", method = "GET")
    @PostMapping("/{id}")
    @Inner(false)
    public Result<NbmhMedicalAccept> getById(@PathVariable(value = "id") @NotBlank Long id) {

        return Result.ok(NbmhMedicalAcceptService.getById(id));
    }

    @Operation(summary = "更新动物诊疗接单记录", method = "PUT")
    @PutMapping
    @Inner(false)
    public Result update(@RequestBody NbmhMedicalAccept nbmhMedicalAccept) {

        NbmhMedicalAcceptService.update(Collections.singletonList(nbmhMedicalAccept));
        return Result.ok();
    }

    @Operation(summary = "分页查询动物诊疗接单记录", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize"), @Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/query")
    @Inner(false)
    public Result<IPage<NbmhMedicalAccept>> query(@RequestBody NbmhMedicalAccept nbmhMedicalAccept,
                                                  @RequestParam @DecimalMin("1") int pageNum,
                                                  @RequestParam @DecimalMin("1") int pageSize,
                                                  @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        IPage<NbmhMedicalAccept> butcherReportPage = NbmhMedicalAcceptService.query(nbmhMedicalAccept, pageNum, pageSize, orderItemList);

        return Result.ok(butcherReportPage);
    }

    @Operation(summary = "查询动物诊疗接单记录", method = "POST")
    @Parameters({@Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhMedicalAccept>> list(@RequestBody NbmhMedicalAccept nbmhMedicalAccept,
                                                @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        return Result.ok(NbmhMedicalAcceptService.list(nbmhMedicalAccept, orderItemList));
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "删除动物诊疗接单记录")
    @Inner(false)
    public Result delete(@PathVariable Long id) {

        NbmhMedicalAcceptService.delete(Collections.singletonList(id));
        return Result.ok();
    }


}
