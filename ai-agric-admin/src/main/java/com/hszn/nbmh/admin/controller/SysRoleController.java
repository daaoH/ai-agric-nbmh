package com.hszn.nbmh.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.admin.api.entity.SysRole;
import com.hszn.nbmh.admin.service.ISysRoleService;
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
 * 系统角色表 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-09-08
 */
@Tag(name = "系统角色管理")
@Validated
@RestController
@RequestMapping("/sys-role")
public class SysRoleController {

    @Autowired
    private ISysRoleService sysRoleService;

    @Operation(summary = "新增系统角色", method = "POST")
    @PostMapping("/add")
    @Inner(false)
    public Result add(@RequestBody @Validated({SysRole.Save.class}) SysRole sysRole) {

        List<Integer> idList = sysRoleService.save(Collections.singletonList(sysRole));
        if (idList != null && idList.size() > 0) {
            return Result.ok(idList);
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询系统角色", method = "GET")
    @GetMapping("/{id}")
    @Inner(false)
    public Result<SysRole> getById(@PathVariable(value = "id") @NotNull(message = "系统角色Id不能为空") Long id) {

        return Result.ok(sysRoleService.getById(id));
    }

    @Operation(summary = "更新系统角色", method = "PUT")
    @PutMapping
    @Inner(false)
    public Result update(@RequestBody @Validated({SysRole.Update.class}) SysRole sysRole) {

        sysRoleService.update(Collections.singletonList(sysRole));
        return Result.ok();
    }

    @Operation(summary = "分页查询系统角色", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize")})
    @PostMapping("/query")
    @Inner(false)
    public Result<IPage<SysRole>> query(@RequestBody QueryCondition<SysRole> queryCondition,
                                        @RequestParam @DecimalMin("1") int pageNum,
                                        @RequestParam @DecimalMin("1") int pageSize) {

        return Result.ok(sysRoleService.query(queryCondition.getEntity(), pageNum, pageSize, queryCondition.getOrderItemList()));
    }

    @Operation(summary = "查询系统角色列表", method = "POST")
    @PostMapping("/list")
    @Inner(false)
    public Result<List<SysRole>> list(@RequestBody QueryCondition<SysRole> queryCondition) {

        return Result.ok(sysRoleService.list(queryCondition.getEntity(), queryCondition.getOrderItemList()));
    }

    @Operation(summary = "删除系统角色", method = "DELETE")
    @DeleteMapping("delete/{id}")
    @Inner(false)
    public Result delete(@PathVariable(value = "id") @NotNull(message = "系统角色Id不能为空") Long id) {

        sysRoleService.delete(Collections.singletonList(id));
        return Result.ok();
    }

}
