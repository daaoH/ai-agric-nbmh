package com.hszn.nbmh.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.core.utils.SortUtil;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.user.api.entity.NbmhVipPrice;
import com.hszn.nbmh.user.service.INbmhUserAddressService;
import com.hszn.nbmh.user.service.INbmhVipPriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户vip价格对照表 前端控制器
 * </p>
 *
 * @author 李肖
 * @since 2022-09-03
 */
@Validated
@RequiredArgsConstructor
@Tag(name = "vip价格接口")
@RestController
@RequestMapping("/nbmh-vip-price")
public class NbmhVipPriceController {


    @Autowired
    private INbmhVipPriceService vipPriceService;

    @PostMapping("/submit")
    @Operation(summary = "创建vip价格")
    @Inner(false)
    public Result submit(@RequestBody NbmhVipPrice nbmhVipPrice) {

        // 提交vip价格创建
        if (vipPriceService.save(nbmhVipPrice)) {

            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @PostMapping("/update")
    @Operation(summary = "更新vip")
    @Inner(false)
    public Result update(@RequestBody NbmhVipPrice record) {
        //更新违规记录
        if (vipPriceService.updateById(record)) {
            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_UPDATE_FAILED.getMsg());
    }

    @PostMapping("/del/{id}")
    @Parameters({@Parameter(description = "数据id", name = "id")})
    @Operation(summary = "删除VIP价格")
    @Inner(false)
    public Result del(@PathVariable("id") Long id) {
        // 删除规则信息
        if (vipPriceService.removeById(id)) {
            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_DELETE_FAILED.getMsg());
    }

    @PostMapping("/getByPage")
    @Operation(summary = "VIP价格-分页")
    @Inner(false)
    public Result getByPage(@RequestBody QueryRequest<NbmhVipPrice> nbmhVipPrice) {
        return Result.ok(vipPriceService.getByPage(nbmhVipPrice));
    }

    @PostMapping("/getByParam")
    @Operation(summary = "根据参数获取数据集")
    @Inner(false)
    public Result getByParam(@RequestBody NbmhVipPrice nbmhVipPrice) {

        return Result.ok(vipPriceService.getByParam(nbmhVipPrice));
    }

}
