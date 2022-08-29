package com.hszn.nbmh.prevent.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.NbmhPreventStation;
import com.hszn.nbmh.prevent.service.INbmhPreventStationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
 * 防疫站信息表 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-08-15
 */

@Tag(name="防疫站信息管理")
@RestController
@RequestMapping("/nbmh-prevent-station")
public class NbmhPreventStationController {

    @Autowired
    private INbmhPreventStationService nbmhPreventStationService;

    @Operation(summary = "新增防疫站信息", method = "POST")
    @PostMapping("/add")
    public Result add(@RequestBody NbmhPreventStation nbmhPreventStation) {

        List<Integer> idList = nbmhPreventStationService.save(Collections.singletonList(nbmhPreventStation));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询防疫站信息", method = "GET")
    @PostMapping("/{id}")
    public Result<NbmhPreventStation> getById(@PathVariable(value = "id") @NotBlank Long id) {

        return Result.ok(nbmhPreventStationService.getById(id));
    }

    @Operation(summary = "更新防疫站信息", method = "PUT")
    @PutMapping
    public Result update(@RequestBody NbmhPreventStation nbmhPreventStation) {

        nbmhPreventStationService.update(Collections.singletonList(nbmhPreventStation));
        return Result.ok();
    }

    @Operation(summary = "分页查询防疫站信息", method = "POST")
    @Parameters({@Parameter(description="页码", name="pageNum"), @Parameter(description="每页显示条数", name="pageSize"), @Parameter(description="排序条件集合", name="orderItemList")})
    @PostMapping("/query")
    public Result<IPage<NbmhPreventStation>> query(@RequestBody NbmhPreventStation nbmhPreventStation,
                                                   @RequestParam @DecimalMin("1") int pageNum,
                                                   @RequestParam @DecimalMin("1") int pageSize,
                                                   @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        IPage<NbmhPreventStation> butcherReportPage = nbmhPreventStationService.query(nbmhPreventStation, pageNum, pageSize, orderItemList);

        return Result.ok(butcherReportPage);
    }

    @Operation(summary = "查询防疫站信息", method = "POST")
    @Parameters({@Parameter(description="排序条件集合", name="orderItemList")})
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhPreventStation>> list(@RequestBody NbmhPreventStation nbmhPreventStation,
                                                 @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        return Result.ok(nbmhPreventStationService.list(nbmhPreventStation, orderItemList));
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "删除防疫站信息")
    public Result delete(@PathVariable Long id) {

        nbmhPreventStationService.delete(Collections.singletonList(id));
        return Result.ok();
    }


}
