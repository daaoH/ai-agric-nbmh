package com.hszn.nbmh.user.controller;


import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.user.api.entity.NbmhRecharge;
import com.hszn.nbmh.user.service.INbmhRechargeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 * 充值记录表 前端控制器
 * </p>
 *
 * @author yuan
 * @since 2022-09-08
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/nbmh-recharge")
public class NbmhRechargeController {

    //充值记录接口
    private final INbmhRechargeService rechargeService;


    @PostMapping("/getByPage")
    @Operation(summary="用户充值记录(分页)")
    @Inner(false)
    public Result getByPage(@RequestBody QueryRequest<NbmhRecharge> param) {
        //获取用户提现记录分页数据集
        return Result.ok(this.rechargeService.getByPage(param));
    }

    @PostMapping("/submit")
    @Operation(summary="用户充值记录提交")
    @Inner(false)
    public Result submit(@RequestBody NbmhRecharge param) {
        param.setCreateTime(new Date());
        boolean ret=this.rechargeService.save(param);
        if (ret) {
            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

}
