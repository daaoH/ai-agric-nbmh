package com.hszn.nbmh.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryCondition;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.user.api.entity.NbmhUserFollowExpert;
import com.hszn.nbmh.user.api.params.out.NbmhUserFollowExpertInfo;
import com.hszn.nbmh.user.service.INbmhUserFollowExpertService;
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
 * 关注的专家记录表 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-08-31
 */

@Tag(name = "关注的专家")
@Validated
@RestController
@RequestMapping("/nbmh-user-follow-expert")
public class NbmhUserFollowExpertController {

    @Autowired
    private INbmhUserFollowExpertService nbmhUserFollowExpertService;

    @Operation(summary = "新增关注的专家", method = "POST")
    @PostMapping("/add")
    public Result add(@RequestBody @Validated({NbmhUserFollowExpert.Save.class}) NbmhUserFollowExpert nbmhUserFollowExpert) {

        List<Integer> idList = nbmhUserFollowExpertService.save(Collections.singletonList(nbmhUserFollowExpert));
        if (idList != null && idList.size() > 0) {
            return Result.ok(idList);
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询关注的专家", method = "GET")
    @PostMapping("/{id}")
    public Result<NbmhUserFollowExpert> getById(@PathVariable(value = "id") @NotNull(message = "关注的专家记录Id不能为空") Long id) {

        return Result.ok(nbmhUserFollowExpertService.getById(id));
    }

    @Operation(summary = "更新关注的专家", method = "PUT")
    @PutMapping
    public Result update(@RequestBody @Validated({NbmhUserFollowExpert.Update.class}) NbmhUserFollowExpert nbmhUserFollowExpert) {

        nbmhUserFollowExpertService.update(Collections.singletonList(nbmhUserFollowExpert));
        return Result.ok();
    }

    @Operation(summary = "分页查询关注的专家", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize")})
    @PostMapping("/query")
    public Result<IPage<NbmhUserFollowExpert>> query(@RequestBody QueryCondition<NbmhUserFollowExpert> queryCondition,
                                                     @RequestParam @DecimalMin("1") int pageNum,
                                                     @RequestParam @DecimalMin("1") int pageSize) {

        return Result.ok(nbmhUserFollowExpertService.query(queryCondition.getEntity(), pageNum, pageSize, queryCondition.getOrderItemList()));
    }

    @Operation(summary = "查询关注的专家", method = "POST")
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhUserFollowExpertInfo>> list(@RequestBody QueryCondition<NbmhUserFollowExpert> queryCondition) {

        return Result.ok(nbmhUserFollowExpertService.list(queryCondition.getEntity(), queryCondition.getOrderItemList()));
    }

    @Operation(summary = "删除关注的专家", method = "DELETE")
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable @NotNull(message = "关注的专家记录Id不能为空") Long id) {

        nbmhUserFollowExpertService.delete(Collections.singletonList(id));
        return Result.ok();
    }


}
