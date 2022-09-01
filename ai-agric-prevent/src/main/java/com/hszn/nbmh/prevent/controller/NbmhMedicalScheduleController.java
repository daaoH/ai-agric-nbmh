package com.hszn.nbmh.prevent.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.NbmhMedicalSchedule;
import com.hszn.nbmh.prevent.service.INbmhMedicalScheduleService;
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
 * 接诊时间设置 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-08-31
 */

@Tag(name = "接诊时间设置管理")
@RestController
@RequestMapping("/nbmh-medical-schedule")
public class NbmhMedicalScheduleController {

    @Autowired
    private INbmhMedicalScheduleService nbmhMedicalScheduleService;

    @Operation(summary = "新增接诊时间设置", method = "POST")
    @PostMapping("/add")
    public Result add(@RequestBody NbmhMedicalSchedule nbmhMedicalSchedule) {

        List<Integer> idList = nbmhMedicalScheduleService.save(Collections.singletonList(nbmhMedicalSchedule));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询接诊时间设置", method = "GET")
    @PostMapping("/{id}")
    public Result<NbmhMedicalSchedule> getById(@PathVariable(value = "id") @NotBlank Long id) {

        return Result.ok(nbmhMedicalScheduleService.getById(id));
    }

    @Operation(summary = "更新接诊时间设置", method = "PUT")
    @PutMapping
    public Result update(@RequestBody NbmhMedicalSchedule nbmhMedicalSchedule) {

        nbmhMedicalScheduleService.update(Collections.singletonList(nbmhMedicalSchedule));
        return Result.ok();
    }

    @Operation(summary = "分页查询接诊时间设置", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize"), @Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/query")
    public Result<IPage<NbmhMedicalSchedule>> query(@RequestBody NbmhMedicalSchedule nbmhMedicalSchedule,
                                                    @RequestParam @DecimalMin("1") int pageNum,
                                                    @RequestParam @DecimalMin("1") int pageSize,
                                                    @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        IPage<NbmhMedicalSchedule> butcherReportPage = nbmhMedicalScheduleService.query(nbmhMedicalSchedule, pageNum, pageSize, orderItemList);

        return Result.ok(butcherReportPage);
    }

    @Operation(summary = "查询接诊时间设置", method = "POST")
    @Parameters({@Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhMedicalSchedule>> list(@RequestBody NbmhMedicalSchedule nbmhMedicalSchedule,
                                                  @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        return Result.ok(nbmhMedicalScheduleService.list(nbmhMedicalSchedule, orderItemList));
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "删除接诊时间设置")
    public Result delete(@PathVariable Long id) {

        nbmhMedicalScheduleService.delete(Collections.singletonList(id));
        return Result.ok();
    }


}
