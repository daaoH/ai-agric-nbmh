package com.hszn.nbmh.cms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.cms.api.entity.NbmhAgreement;
import com.hszn.nbmh.cms.service.INbmhAgreementService;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import io.swagger.annotations.Api;
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
 * 用户协议表 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-08-20
 */
@Tag(name="用户协议管理")
@RestController
@RequestMapping("/nbmh-agreement")
public class NbmhAgreementController {

    @Autowired
    private INbmhAgreementService nbmhAgreementService;

    @Operation(summary = "新增用户协议", method = "POST")
    @PostMapping("/add")
    public Result add(@RequestBody NbmhAgreement nbmhAgreement) {

        List<Integer> idList = nbmhAgreementService.save(Collections.singletonList(nbmhAgreement));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询用户协议", method = "GET")
    @Parameters({@Parameter(description = "用户协议Id", name = "id")})
    @GetMapping("/{id}")
    public Result<NbmhAgreement> getById(@PathVariable(value = "id") @NotBlank Long id) {

        return Result.ok(nbmhAgreementService.getById(id));
    }

    @Operation(summary = "更新用户协议", method = "PUT")
    @PutMapping
    public Result update(@RequestBody NbmhAgreement nbmhAgreement) {

        nbmhAgreementService.update(Collections.singletonList(nbmhAgreement));
        return Result.ok();
    }

    @Operation(summary = "分页查询用户协议", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize"), @Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/query")
    public Result<IPage<NbmhAgreement>> query(@RequestBody NbmhAgreement nbmhAgreement,
                                              @RequestParam @DecimalMin("1") int pageNum,
                                              @RequestParam @DecimalMin("1") int pageSize,
                                              @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        IPage<NbmhAgreement> butcherReportPage = nbmhAgreementService.query(nbmhAgreement, pageNum, pageSize, orderItemList);

        return Result.ok(butcherReportPage);
    }

    @Operation(summary = "查询用户协议", method = "POST")
    @Parameters({@Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/list")
    public Result<List<NbmhAgreement>> list(@RequestBody NbmhAgreement nbmhAgreement,
                                            @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        return Result.ok(nbmhAgreementService.list(nbmhAgreement, orderItemList));
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "删除用户协议")
    public Result delete(@PathVariable Long id) {

        nbmhAgreementService.delete(Collections.singletonList(id));
        return Result.ok();
    }

}
