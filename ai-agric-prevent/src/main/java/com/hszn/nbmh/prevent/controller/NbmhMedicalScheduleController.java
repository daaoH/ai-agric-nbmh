package com.hszn.nbmh.prevent.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryCondition;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.NbmhMedicalSchedule;
import com.hszn.nbmh.prevent.service.INbmhMedicalScheduleService;
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
 * 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-08-31
 */

@Tag(name = "接诊时间设置管理")
@Validated
@RestController
@RequestMapping("/nbmh-medical-schedule")
public class NbmhMedicalScheduleController {

    @Autowired
    private INbmhMedicalScheduleService nbmhMedicalScheduleService;

    @Operation(summary = "新增接诊时间设置", method = "POST")
    @PostMapping("/add")
    @Inner(false)
    public Result add(@RequestBody @Validated({NbmhMedicalSchedule.Save.class}) NbmhMedicalSchedule nbmhMedicalSchedule) {

        List<Integer> idList = nbmhMedicalScheduleService.save(Collections.singletonList(nbmhMedicalSchedule));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询接诊时间设置", method = "GET")
    @PostMapping("/{id}")
    @Inner(false)
    public Result<NbmhMedicalSchedule> getById(@PathVariable(value = "id") @NotNull(message = "接诊时间设置记录Id不能为空") Long id) {

        return Result.ok(nbmhMedicalScheduleService.getById(id));
    }

    @Operation(summary = "更新接诊时间设置", method = "PUT")
    @PutMapping
    @Inner(false)
    public Result update(@RequestBody @Validated({NbmhMedicalSchedule.Update.class}) NbmhMedicalSchedule nbmhMedicalSchedule) {

        nbmhMedicalScheduleService.update(Collections.singletonList(nbmhMedicalSchedule));
        return Result.ok();
    }

    @Operation(summary = "分页查询接诊时间设置", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize")})
    @PostMapping("/query")
    @Inner(false)
    public Result<IPage<NbmhMedicalSchedule>> query(@RequestBody QueryCondition<NbmhMedicalSchedule> queryCondition,
                                                    @RequestParam @DecimalMin("1") int pageNum,
                                                    @RequestParam @DecimalMin("1") int pageSize) {

        return Result.ok(nbmhMedicalScheduleService.query(queryCondition.getEntity(), pageNum, pageSize, queryCondition.getOrderItemList()));
    }

    @Operation(summary = "查询接诊时间设置", method = "POST")
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhMedicalSchedule>> list(@RequestBody QueryCondition<NbmhMedicalSchedule> queryCondition) {

        return Result.ok(nbmhMedicalScheduleService.list(queryCondition.getEntity(), queryCondition.getOrderItemList()));
    }

    @Operation(summary = "删除接诊时间设置", method = "DELETE")
    @DeleteMapping("delete/{id}")
    @Inner(false)
    public Result delete(@PathVariable(value = "id") @NotNull(message = "接诊时间设置记录Id不能为空") Long id) {

        nbmhMedicalScheduleService.delete(Collections.singletonList(id));
        return Result.ok();
    }


}
