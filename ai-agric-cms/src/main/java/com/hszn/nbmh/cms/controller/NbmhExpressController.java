package com.hszn.nbmh.cms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.cms.api.entity.NbmhExpress;
import com.hszn.nbmh.cms.service.INbmhExpressService;
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
import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 快递公司表 前端控制器
 * </p>
 *
 * @author pyq
 * @since 2022-09-13
 */
@Tag(name = "快递公司表")
@Validated
@RestController
@RequestMapping("/nbmh-express")
public class NbmhExpressController {
    @Autowired
    private INbmhExpressService nbmhExpressService;

    @Operation(summary = "新增快递公司", method = "POST")
    @PostMapping("/add")
    @Inner(false)
    public Result add(@RequestBody @Validated NbmhExpress nbmhExpress) {

        List<Integer> idList = nbmhExpressService.save(Collections.singletonList(nbmhExpress));
        if(idList != null && idList.size() > 0) {
            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询快递公司", method = "GET")
    @Parameters({@Parameter(description = "快递公司Id", name = "id")})
    @GetMapping("/{id}")
    @Inner(false)
    public Result<NbmhExpress> getById(@PathVariable(value = "id") @NotBlank Long id) {
        return  Result.ok(nbmhExpressService.getById(id));
    }

    @Operation(summary = "更新快递公司", method = "PUT")
    @PutMapping
    @Inner(false)
    public Result update(@RequestBody NbmhExpress nbmhExpress) {
        nbmhExpressService.update(Collections.singletonList(nbmhExpress));
        return Result.ok();
    }

    @Operation(summary = "分页查询快递公司", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize"),@Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/query")
    @Inner(false)
    public Result<IPage<NbmhExpress>> query(@RequestBody NbmhExpress nbmhExpress,
                                            @RequestParam @DecimalMin("1") int pageNum,
                                            @RequestParam @DecimalMin("1") int pageSize,
                                            @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        IPage<NbmhExpress> butcherReportPage = nbmhExpressService.query(nbmhExpress, pageNum, pageSize, orderItemList);

        return Result.ok(butcherReportPage);
    }

    @Operation(summary = "查询快递公司", method = "POST")
    @Parameters({@Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhExpress>> list(@RequestBody NbmhExpress nbmhExpress,
                                          @RequestParam(value = "orderIemList", required = false)List<OrderItem> orderItemList) {

        return Result.ok(nbmhExpressService.list(nbmhExpress, orderItemList));
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "删除快递公司")
    @Inner(false)
    public Result delete(@PathVariable Long id) {

        nbmhExpressService.delete(Collections.singletonList(id));
        return Result.ok();
    }

}
