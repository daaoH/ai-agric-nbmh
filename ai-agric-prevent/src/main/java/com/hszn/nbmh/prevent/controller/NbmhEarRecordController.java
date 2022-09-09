package com.hszn.nbmh.prevent.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.NbmhEarRecord;
import com.hszn.nbmh.prevent.api.params.input.NbmhEarRecordParam;
import com.hszn.nbmh.prevent.service.INbmhEarRecordService;
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
 * 耳标补打信息表 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-08-16
 */
@Tag(name = "耳标补打信息")
@Validated
@RestController
@RequestMapping("/nbmh-ear-record")
public class NbmhEarRecordController {

    @Autowired
    private INbmhEarRecordService nbmhEarRecordService;


    @Operation(summary = "新增耳标补打信息", method = "POST")
    @PostMapping("/add")
    @Inner(false)
    public Result add(@RequestBody @Validated({NbmhEarRecord.Save.class}) NbmhEarRecord nbmhEarRecord) {

        List<Integer> idList = nbmhEarRecordService.save(Collections.singletonList(nbmhEarRecord));
        if (idList != null && idList.size() > 0) {
            return Result.ok(idList);
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询耳标补打信息", method = "GET")
    @Parameters({@Parameter(description = "耳标补打信息Id", name = "id")})
    @PostMapping("/{id}")
    @Inner(false)
    public Result<NbmhEarRecord> getById(@PathVariable(value = "id") @NotNull(message = "耳标补打信息id不能为空") Long id) {

        return Result.ok(nbmhEarRecordService.getById(id));
    }

    @Operation(summary = "更新耳标补打信息", method = "PUT")
    @PutMapping
    @Inner(false)
    public Result update(@RequestBody @Validated({NbmhEarRecord.Update.class}) NbmhEarRecord nbmhEarRecord) {

        nbmhEarRecordService.update(Collections.singletonList(nbmhEarRecord));
        return Result.ok();
    }

    @Operation(summary = "分页查询耳标补打信息", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize")})
    @PostMapping("/query")
    @Inner(false)
    public Result<IPage<NbmhEarRecord>> query(@RequestBody NbmhEarRecordParam nbmhEarRecordParam,
                                              @RequestParam @DecimalMin("1") int pageNum,
                                              @RequestParam @DecimalMin("1") int pageSize) {

        return Result.ok(nbmhEarRecordService.query(nbmhEarRecordParam, pageNum, pageSize, null));
    }

    @Operation(summary = "查询耳标补打信息", method = "POST")
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhEarRecord>> list(@RequestBody NbmhEarRecord nbmhEarRecord) {

        return Result.ok(nbmhEarRecordService.list(nbmhEarRecord, null));
    }

    @Operation(summary = "删除耳标补打信息", method = "DELETE")
    @DeleteMapping("delete/{id}")
    @Inner(false)
    public Result delete(@PathVariable(value = "id") @NotNull(message = "耳标补打信息id不能为空") Long id) {

        nbmhEarRecordService.delete(Collections.singletonList(id));
        return Result.ok();
    }

}
