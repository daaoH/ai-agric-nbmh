package com.hszn.nbmh.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.admin.api.entity.SysUserRole;
import com.hszn.nbmh.admin.service.ISysUserRoleService;
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
import java.util.List;

/**
 * <p>
 * 用户角色表 前端控制器
 * </p>
 *
 * @author yuan
 * @since 2022-09-06
 */
@Tag(name = "用户角色关系管理")
@Validated
@RestController
@RequestMapping("/sys-user-role")
public class SysUserRoleController {

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Operation(summary = "新增用户角色", method = "POST")
    @PostMapping("/add")
    public Result add(@RequestBody @Validated({SysUserRole.Save.class}) List<SysUserRole> sysUserRoleList) {

        List<Integer> idList = sysUserRoleService.save(sysUserRoleList);
        if (idList != null && idList.size() > 0) {
            return Result.ok(idList);
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "分页查询用户角色", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize")})
    @PostMapping("/query")
    public Result<IPage<SysUserRole>> query(@RequestBody QueryCondition<SysUserRole> queryCondition,
                                            @RequestParam @DecimalMin("1") int pageNum,
                                            @RequestParam @DecimalMin("1") int pageSize) {

        return Result.ok(sysUserRoleService.query(queryCondition.getEntity(), pageNum, pageSize, queryCondition.getOrderItemList()));
    }

    @Operation(summary = "查询用户角色列表", method = "POST")
    @PostMapping("/list")
    @Inner(false)
    public Result<List<SysUserRole>> list(@RequestBody QueryCondition<SysUserRole> queryCondition) {

        return Result.ok(sysUserRoleService.list(queryCondition.getEntity(), queryCondition.getOrderItemList()));
    }

    @Operation(summary = "删除用户角色", method = "DELETE")
    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Validated({SysUserRole.Delete.class}) List<SysUserRole> sysUserRoleList) {

        sysUserRoleService.delete(sysUserRoleList);
        return Result.ok();
    }

}
