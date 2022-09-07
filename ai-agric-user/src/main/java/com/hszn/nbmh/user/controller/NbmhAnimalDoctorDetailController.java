package com.hszn.nbmh.user.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.user.api.entity.NbmhAnimalDoctorDetail;
import com.hszn.nbmh.user.service.INbmhAnimalDoctorDetailService;
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
 * 兽医详细信息表 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-08-30
 */
@Tag(name = "兽医详细信息")
@RestController
@RequestMapping("/nbmh-animal-doctor-detail")
public class NbmhAnimalDoctorDetailController {


    @Autowired
    private INbmhAnimalDoctorDetailService nbmhAnimalDoctorDetailService;

    @Operation(summary = "新增兽医详细信息", method = "POST")
    @PostMapping("/add")
    public Result add(@RequestBody NbmhAnimalDoctorDetail nbmhAnimalDoctorDetail) {

        List<Integer> idList = nbmhAnimalDoctorDetailService.save(Collections.singletonList(nbmhAnimalDoctorDetail));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询兽医详细信息", method = "GET")
    @PostMapping("/{id}")
    public Result<NbmhAnimalDoctorDetail> getById(@PathVariable(value = "id") @NotBlank Long id) {

        return Result.ok(nbmhAnimalDoctorDetailService.getById(id));
    }

    @Operation(summary = "更新兽医详细信息", method = "PUT")
    @PutMapping
    public Result update(@RequestBody NbmhAnimalDoctorDetail nbmhAnimalDoctorDetail) {

        nbmhAnimalDoctorDetailService.update(Collections.singletonList(nbmhAnimalDoctorDetail));
        return Result.ok();
    }

    @Operation(summary = "分页查询兽医详细信息", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize"), @Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/query")
    public Result<IPage<NbmhAnimalDoctorDetail>> query(@RequestBody NbmhAnimalDoctorDetail nbmhAnimalDoctorDetail,
                                                       @RequestParam @DecimalMin("1") int pageNum,
                                                       @RequestParam @DecimalMin("1") int pageSize,
                                                       @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        IPage<NbmhAnimalDoctorDetail> butcherReportPage = nbmhAnimalDoctorDetailService.query(nbmhAnimalDoctorDetail, pageNum, pageSize, orderItemList);

        return Result.ok(butcherReportPage);
    }

    @Operation(summary = "查询兽医详细信息", method = "POST")
    @Parameters({@Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhAnimalDoctorDetail>> list(@RequestBody NbmhAnimalDoctorDetail nbmhAnimalDoctorDetail,
                                                     @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        return Result.ok(nbmhAnimalDoctorDetailService.list(nbmhAnimalDoctorDetail, orderItemList));
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "删除兽医详细信息")
    public Result delete(@PathVariable Long id) {

        nbmhAnimalDoctorDetailService.delete(Collections.singletonList(id));
        return Result.ok();
    }

    @Operation(summary = "更新兽医专家接诊次数", method = "POST")
    @PostMapping
    public Result updateAcceptOrderNum(@RequestParam(value = "doctorId") Long doctorId) {

        nbmhAnimalDoctorDetailService.updateAcceptOrderNum(doctorId);
        return Result.ok();
    }

}
