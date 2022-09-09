package com.hszn.nbmh.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.admin.api.entity.SysPost;
import com.hszn.nbmh.admin.service.ISysPostService;
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
 * 岗位信息表 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-09-08
 */
@Tag(name = "岗位信息管理")
@Validated
@RestController
@RequestMapping("/sys-post")
public class SysPostController {

    @Autowired
    private ISysPostService sysPostService;

    @Operation(summary = "新增岗位信息", method = "POST")
    @PostMapping("/add")
    @Inner(false)
    public Result add(@RequestBody @Validated({SysPost.Save.class}) SysPost sysPost) {

        List<Integer> idList = sysPostService.save(Collections.singletonList(sysPost));
        if (idList != null && idList.size() > 0) {
            return Result.ok(idList);
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询岗位信息", method = "GET")
    @GetMapping("/{id}")
    @Inner(false)
    public Result<SysPost> getById(@PathVariable(value = "id") @NotNull(message = "岗位信息Id不能为空") Long id) {

        return Result.ok(sysPostService.getById(id));
    }

    @Operation(summary = "更新岗位信息", method = "PUT")
    @PutMapping
    @Inner(false)
    public Result update(@RequestBody @Validated({SysPost.Update.class}) SysPost sysPost) {

        sysPostService.update(Collections.singletonList(sysPost));
        return Result.ok();
    }

    @Operation(summary = "分页查询岗位信息", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize")})
    @PostMapping("/query")
    @Inner(false)
    public Result<IPage<SysPost>> query(@RequestBody QueryCondition<SysPost> queryCondition,
                                        @RequestParam @DecimalMin("1") int pageNum,
                                        @RequestParam @DecimalMin("1") int pageSize) {

        return Result.ok(sysPostService.query(queryCondition.getEntity(), pageNum, pageSize, queryCondition.getOrderItemList()));
    }

    @Operation(summary = "查询岗位信息列表", method = "POST")
    @PostMapping("/list")
    @Inner(false)
    public Result<List<SysPost>> list(@RequestBody QueryCondition<SysPost> queryCondition) {

        return Result.ok(sysPostService.list(queryCondition.getEntity(), queryCondition.getOrderItemList()));
    }

    @Operation(summary = "删除岗位信息", method = "DELETE")
    @DeleteMapping("delete/{id}")
    @Inner(false)
    public Result delete(@PathVariable(value = "id") @NotNull(message = "岗位信息Id不能为空") Long id) {

        sysPostService.delete(Collections.singletonList(id));
        return Result.ok();
    }

}
