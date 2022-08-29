package com.hszn.nbmh.prevent.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.NbmhFarm;
import com.hszn.nbmh.prevent.service.INbmhFarmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 养殖场信息表 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-08-16
 */
@Tag(name="养殖场信息")
@Validated
@RestController
@RequestMapping("/nbmh-farm")
public class NbmhFarmController {

    @Autowired
    private INbmhFarmService nbmhFarmService;


    @Operation(summary = "新增养殖场信息", method = "POST")
    @PostMapping("/add")
    public Result add(@RequestBody NbmhFarm nbmhFarm) {

        List<Integer> idList = nbmhFarmService.save(Collections.singletonList(nbmhFarm));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询养殖场信息", method = "GET")
    @Parameters({@Parameter(description="养殖场信息Id", name="id")})
    @PostMapping("/{id}")
    public Result<NbmhFarm> getById(@PathVariable(value = "id") @NotBlank Long id) {

        return Result.ok(nbmhFarmService.getById(id));
    }

    @Operation(summary = "更新养殖场信息", method = "PUT")
    @PutMapping
    public Result update(@RequestBody NbmhFarm nbmhFarm) {

        nbmhFarmService.update(Collections.singletonList(nbmhFarm));
        return Result.ok();
    }

    @Operation(summary = "分页查询养殖场信息", method = "POST")
    @Parameters({@Parameter(description="页码", name="pageNum"), @Parameter(description="每页显示条数", name="pageSize"), @Parameter(description="排序条件集合", name="orderItemList")})
    @PostMapping("/query")
    public Result<IPage<NbmhFarm>> query(@RequestBody NbmhFarm nbmhFarm,
                                         @RequestParam @DecimalMin("1") int pageNum,
                                         @RequestParam @DecimalMin("1") int pageSize,
                                         @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        IPage<NbmhFarm> butcherReportPage = nbmhFarmService.query(nbmhFarm, pageNum, pageSize, orderItemList);

        return Result.ok(butcherReportPage);
    }

    @Operation(summary = "查询养殖场信息", method = "POST")
    @Parameters({@Parameter(description="排序条件集合", name="orderItemList")})
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhFarm>> list(@RequestBody NbmhFarm nbmhFarm,
                                       @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        return Result.ok(nbmhFarmService.list(nbmhFarm, orderItemList));
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "删除养殖场信息")
    public Result delete(@PathVariable Long id) {

        nbmhFarmService.delete(Collections.singletonList(id));
        return Result.ok();
    }

}