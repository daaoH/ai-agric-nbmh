package com.hszn.nbmh.prevent.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.NbmhFarm;
import com.hszn.nbmh.prevent.api.params.out.NbmhFarmResult;
import com.hszn.nbmh.prevent.service.INbmhFarmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Date;
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


    @PostMapping("/submitList")
    @Inner(false)
    @Operation(summary="批量新增养殖场信息", method="POST")
    public Result submitList(@RequestBody List<NbmhFarm> farms) {
        if (ObjectUtils.isEmpty(farms))
            return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
        farms.forEach(f -> {
            f.setCreateTime(new Date());
            f.setStatus(0);
        });
        if (nbmhFarmService.saveBatch(farms)) {
            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }


    @Operation(summary="新增养殖场信息", method="POST")
    @PostMapping("/add")
    @Inner(false)
    public Result add(@RequestBody @Validated({NbmhFarm.Save.class}) NbmhFarm nbmhFarm) {

        List<Integer> idList=nbmhFarmService.save(Collections.singletonList(nbmhFarm));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary="根据ID查询养殖场信息", method="GET")
    @Parameters({@Parameter(description="养殖场信息Id", name="id")})
    @PostMapping("/{id}")
    @Inner(false)
    public Result<NbmhFarm> getById(@PathVariable(value="id") @NotNull(message="养殖场信息id不能为空") Long id) {

        return Result.ok(nbmhFarmService.getById(id));
    }

    @Operation(summary="更新养殖场信息", method="PUT")
    @PutMapping
    @Inner(false)
    public Result update(@RequestBody @Validated({NbmhFarm.Update.class}) NbmhFarm nbmhFarm) {

        nbmhFarmService.update(Collections.singletonList(nbmhFarm));
        return Result.ok();
    }

    @Operation(summary="分页查询养殖场信息", method="POST")
    @Parameters({@Parameter(description="页码", name="pageNum"), @Parameter(description="每页显示条数", name="pageSize")})
    @PostMapping("/query")
    @Inner(false)
    public Result<IPage<NbmhFarm>> query(@RequestBody NbmhFarm nbmhFarm,
                                         @RequestParam @DecimalMin("1") int pageNum,
                                         @RequestParam @DecimalMin("1") int pageSize) {

        return Result.ok(nbmhFarmService.query(nbmhFarm, pageNum, pageSize, null));
    }

    @Operation(summary="查询养殖场信息", method="POST")
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhFarm>> list(@RequestBody NbmhFarm nbmhFarm) {

        return Result.ok(nbmhFarmService.list(nbmhFarm, null));
    }

    @Operation(summary="删除养殖场信息", method="DELETE")
    @DeleteMapping("delete/{id}")
    @Inner(false)
    public Result delete(@PathVariable(value="id") @NotNull(message="养殖场信息id不能为空") Long id) {

        nbmhFarmService.delete(Collections.singletonList(id));
        return Result.ok();
    }

    @Operation(summary="离线防疫--根据防疫站Id获取养殖场列表", method="POST")
    @PostMapping("/listByPreventStationId")
    @Inner(false)
    public Result<List<NbmhFarmResult>> listByPreventStationId(@RequestParam @NotNull(message="防疫站Id不能为空") Long preventStationId) {

        return Result.ok(nbmhFarmService.listByPreventStationId(preventStationId));
    }

}
