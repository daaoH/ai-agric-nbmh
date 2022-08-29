package com.hszn.nbmh.cms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.cms.api.entity.NbmhAd;
import com.hszn.nbmh.cms.service.INbmhAdService;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
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
 * 广告表 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-08-20
 */
@Tag(name = "广告管理")
@RestController
@RequestMapping("/nbmh-ad")
public class NbmhAdController {

    @Autowired
    private INbmhAdService nbmhAdService;

    @Operation(summary = "新增广告", method = "POST")
    @PostMapping("/add")
    @Inner(false)
    public Result add(@RequestBody NbmhAd nbmhAd) {

        List<Integer> idList = nbmhAdService.save(Collections.singletonList(nbmhAd));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询广告", method = "GET")
    @Parameters({@Parameter(description = "广告Id", name = "id")})
    @GetMapping("/{id}")
    @Inner(false)
    public Result<NbmhAd> getById(@PathVariable(value = "id") @NotBlank Long id) {

        return Result.ok(nbmhAdService.getById(id));
    }

    @Operation(summary = "更新广告", method = "PUT")
    @PutMapping
    @Inner(false)
    public Result update(@RequestBody NbmhAd nbmhAd) {

        nbmhAdService.update(Collections.singletonList(nbmhAd));
        return Result.ok();
    }

    @Operation(summary = "分页查询广告", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize"), @Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/query")
    @Inner(false)
    public Result<IPage<NbmhAd>> query(@RequestBody NbmhAd nbmhAd,
                                       @RequestParam @DecimalMin("1") int pageNum,
                                       @RequestParam @DecimalMin("1") int pageSize,
                                       @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        IPage<NbmhAd> butcherReportPage = nbmhAdService.query(nbmhAd, pageNum, pageSize, orderItemList);

        return Result.ok(butcherReportPage);
    }

    @Operation(summary = "查询广告", method = "POST")
    @Parameters({@Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhAd>> list(@RequestBody NbmhAd nbmhAd,
                                     @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        return Result.ok(nbmhAdService.list(nbmhAd, orderItemList));
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "删除广告")
    @Inner(false)
    public Result delete(@PathVariable Long id) {

        nbmhAdService.delete(Collections.singletonList(id));
        return Result.ok();
    }

}
