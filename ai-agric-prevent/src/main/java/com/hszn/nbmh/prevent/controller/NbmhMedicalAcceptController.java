package com.hszn.nbmh.prevent.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryCondition;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.NbmhMedicalAccept;
import com.hszn.nbmh.prevent.service.INbmhMedicalAcceptService;
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
 * 动物诊疗派单记录 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-08-31
 */
@Tag(name = "动物诊疗派单记录")
@Validated
@RestController
@RequestMapping("/nbmh-medical-accept")
public class NbmhMedicalAcceptController {

    @Autowired
    private INbmhMedicalAcceptService NbmhMedicalAcceptService;

    @Operation(summary = "新增动物诊疗接单记录", method = "POST")
    @PostMapping("/add")
    @Inner(false)
    public Result add(@RequestBody @Validated({NbmhMedicalAccept.Save.class}) NbmhMedicalAccept nbmhMedicalAccept) {

        List<Integer> idList = NbmhMedicalAcceptService.save(Collections.singletonList(nbmhMedicalAccept));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询动物诊疗接单记录", method = "GET")
    @PostMapping("/{id}")
    @Inner(false)
    public Result<NbmhMedicalAccept> getById(@PathVariable(value = "id") @NotNull(message = "动物诊疗接单记录Id不能为空") Long id) {

        return Result.ok(NbmhMedicalAcceptService.getById(id));
    }

    @Operation(summary = "更新动物诊疗接单记录", method = "PUT")
    @PutMapping
    @Inner(false)
    public Result update(@RequestBody @Validated({NbmhMedicalAccept.Update.class}) NbmhMedicalAccept nbmhMedicalAccept) {

        NbmhMedicalAcceptService.update(Collections.singletonList(nbmhMedicalAccept));
        return Result.ok();
    }

    @Operation(summary = "分页查询动物诊疗接单记录", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize")})
    @PostMapping("/query")
    @Inner(false)
    public Result<IPage<NbmhMedicalAccept>> query(@RequestBody QueryCondition<NbmhMedicalAccept> queryCondition,
                                                  @RequestParam @DecimalMin("1") int pageNum,
                                                  @RequestParam @DecimalMin("1") int pageSize) {

        return Result.ok(NbmhMedicalAcceptService.query(queryCondition.getEntity(), pageNum, pageSize, queryCondition.getOrderItemList()));
    }

    @Operation(summary = "查询动物诊疗接单记录", method = "POST")
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhMedicalAccept>> list(@RequestBody QueryCondition<NbmhMedicalAccept> queryCondition) {

        return Result.ok(NbmhMedicalAcceptService.list(queryCondition.getEntity(), queryCondition.getOrderItemList()));
    }

    @Operation(summary = "删除动物诊疗接单记录", method = "DELETE")
    @DeleteMapping("delete/{id}")
    @Inner(false)
    public Result delete(@PathVariable(value = "id") @NotNull(message = "动物诊疗接单记录Id不能为空") Long id) {

        NbmhMedicalAcceptService.delete(Collections.singletonList(id));
        return Result.ok();
    }

    @Operation(summary = "兽医确认接单", method = "POST")
    @PostMapping("/acceptOrder")
    @Inner(false)
    public Result acceptOrder(@RequestBody @Validated({NbmhMedicalAccept.Update.class}) NbmhMedicalAccept nbmhMedicalAccept) {

        NbmhMedicalAcceptService.acceptOrder(Collections.singletonList(nbmhMedicalAccept));
        return Result.ok();
    }

}
