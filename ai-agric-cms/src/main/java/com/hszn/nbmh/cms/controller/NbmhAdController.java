package com.hszn.nbmh.cms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
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
@Validated
@RestController
@RequestMapping("/nbmh-ad")
public class NbmhAdController {

    @Autowired
    private INbmhAdService nbmhAdService;

    @Operation(summary = "新增广告", method = "POST")
    @PostMapping("/add")
    @Inner(false)
    public Result add(@RequestBody @Validated({NbmhAd.Save.class}) NbmhAd nbmhAd) {

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
    public Result<NbmhAd> getById(@PathVariable(value = "id") @NotNull(message = "广告id不能为空") Long id) {

        return Result.ok(nbmhAdService.getById(id));
    }

    @Operation(summary = "更新广告", method = "PUT")
    @PutMapping
    @Inner(false)
    public Result update(@RequestBody @Validated({NbmhAd.Update.class}) NbmhAd nbmhAd) {

        nbmhAdService.update(Collections.singletonList(nbmhAd));
        return Result.ok();
    }

    @Operation(summary = "分页查询广告", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize")})
    @PostMapping("/query")
    @Inner(false)
    public Result<IPage<NbmhAd>> query(@RequestBody NbmhAd nbmhAd,
                                       @RequestParam @DecimalMin("1") int pageNum,
                                       @RequestParam @DecimalMin("1") int pageSize) {

        return Result.ok(nbmhAdService.query(nbmhAd, pageNum, pageSize, null));
    }

    @Operation(summary = "查询广告", method = "POST")
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhAd>> list(@RequestBody NbmhAd nbmhAd) {

        return Result.ok(nbmhAdService.list(nbmhAd, null));
    }

    @Operation(summary = "删除广告", method = "DELETE")
    @DeleteMapping("delete/{id}")
    @Inner(false)
    public Result delete(@PathVariable(value = "id") @NotNull(message = "广告id不能为空") Long id) {

        nbmhAdService.delete(Collections.singletonList(id));
        return Result.ok();
    }

}
