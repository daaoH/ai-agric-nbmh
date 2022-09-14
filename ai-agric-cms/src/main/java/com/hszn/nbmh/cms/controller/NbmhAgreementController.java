package com.hszn.nbmh.cms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.cms.api.entity.NbmhAgreement;
import com.hszn.nbmh.cms.service.INbmhAgreementService;
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
 * 用户协议表 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-08-20
 */
@Tag(name = "用户协议管理")
@Validated
@RestController
@RequestMapping("/nbmh-agreement")
public class NbmhAgreementController {

    @Autowired
    private INbmhAgreementService nbmhAgreementService;

    @Operation(summary = "新增用户协议", method = "POST")
    @PostMapping("/add")
    @Inner(false)
    public Result add(@RequestBody @Validated({NbmhAgreement.Save.class}) NbmhAgreement nbmhAgreement) {

        List<Integer> idList = nbmhAgreementService.save(Collections.singletonList(nbmhAgreement));
        if (idList != null && idList.size() > 0) {
            return Result.ok(idList);
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询用户协议", method = "GET")
    @Parameters({@Parameter(description = "用户协议Id", name = "id")})
    @GetMapping("/{id}")
    @Inner(false)
    public Result<NbmhAgreement> getById(@PathVariable(value = "id") @NotNull(message = "用户协议id不能为空") Long id) {

        return Result.ok(nbmhAgreementService.getById(id));
    }

    @Operation(summary = "更新用户协议", method = "PUT")
    @PutMapping
    @Inner(false)
    public Result update(@RequestBody @Validated({NbmhAgreement.Update.class}) NbmhAgreement nbmhAgreement) {

        nbmhAgreementService.update(Collections.singletonList(nbmhAgreement));
        return Result.ok();
    }

    @Operation(summary = "分页查询用户协议", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize")})
    @PostMapping("/query")
    @Inner(false)
    public Result<IPage<NbmhAgreement>> query(@RequestBody QueryCondition<NbmhAgreement> queryCondition,
                                              @RequestParam @DecimalMin("1") int pageNum,
                                              @RequestParam @DecimalMin("1") int pageSize) {

        return Result.ok(nbmhAgreementService.query(queryCondition.getEntity(), pageNum, pageSize, queryCondition.getOrderItemList()));
    }

    @Operation(summary = "查询用户协议", method = "POST")
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhAgreement>> list(@RequestBody QueryCondition<NbmhAgreement> queryCondition) {

        return Result.ok(nbmhAgreementService.list(queryCondition.getEntity(), queryCondition.getOrderItemList()));
    }

    @Operation(summary = "删除用户协议", method = "DELETE")
    @DeleteMapping("delete/{id}")
    @Inner(false)
    public Result delete(@PathVariable(value = "id") @NotNull(message = "用户协议id不能为空") Long id) {

        nbmhAgreementService.delete(Collections.singletonList(id));
        return Result.ok();
    }

}
