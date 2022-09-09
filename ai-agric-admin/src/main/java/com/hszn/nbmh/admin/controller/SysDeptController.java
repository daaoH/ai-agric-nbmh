package com.hszn.nbmh.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.admin.api.entity.SysDept;
import com.hszn.nbmh.admin.service.ISysDeptService;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryCondition;
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
 * 部门管理 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-09-08
 */
@Tag(name = "部门管理")
@Validated
@RestController
@RequestMapping("/sys-dept")
public class SysDeptController {

    @Autowired
    private ISysDeptService sysDeptService;

    @Operation(summary = "新增部门", method = "POST")
    @PostMapping("/add")
    @Inner(false)
    public Result add(@RequestBody @Validated({SysDept.Save.class}) SysDept sysDept) {

        List<Integer> idList = sysDeptService.save(Collections.singletonList(sysDept));
        if (idList != null && idList.size() > 0) {
            return Result.ok(idList);
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询部门", method = "GET")
    @GetMapping("/{id}")
    @Inner(false)
    public Result<SysDept> getById(@PathVariable(value = "id") @NotNull(message = "部门Id不能为空") Long id) {

        return Result.ok(sysDeptService.getById(id));
    }

    @Operation(summary = "更新部门", method = "PUT")
    @PutMapping
    @Inner(false)
    public Result update(@RequestBody @Validated({SysDept.Update.class}) SysDept sysDept) {

        sysDeptService.update(Collections.singletonList(sysDept));
        return Result.ok();
    }

    @Operation(summary = "分页查询部门", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize")})
    @PostMapping("/query")
    @Inner(false)
    public Result<IPage<SysDept>> query(@RequestBody QueryCondition<SysDept> queryCondition,
                                        @RequestParam @DecimalMin("1") int pageNum,
                                        @RequestParam @DecimalMin("1") int pageSize) {

        return Result.ok(sysDeptService.query(queryCondition.getEntity(), pageNum, pageSize, queryCondition.getOrderItemList()));
    }

    @Operation(summary = "查询部门列表", method = "POST")
    @PostMapping("/list")
    @Inner(false)
    public Result<List<SysDept>> list(@RequestBody QueryCondition<SysDept> queryCondition) {

        return Result.ok(sysDeptService.list(queryCondition.getEntity(), queryCondition.getOrderItemList()));
    }

    @Operation(summary = "删除部门", method = "DELETE")
    @DeleteMapping("delete/{id}")
    @Inner(false)
    public Result delete(@PathVariable(value = "id") @NotNull(message = "部门Id不能为空") Long id) {

        sysDeptService.delete(Collections.singletonList(id));
        return Result.ok();
    }

}
