package com.hszn.nbmh.user.controller;


import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.user.api.entity.NbmhUserExtract;
import com.hszn.nbmh.user.service.INbmhUserExtractService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 * 用户提现表 前端控制器
 * </p>
 *
 * @author wangjun
 * @since 2022-09-15
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/nbmh-user-extract")
public class NbmhUserExtractController {
    //用户提现记录接口
    private final INbmhUserExtractService userExtractService;


    @PostMapping("/getByPage")
    @Operation(summary="用户提现记录(分页)")
    @Inner(false)
    public Result getByPage(@RequestBody QueryRequest<NbmhUserExtract> param) {
        //获取用户提现记录分页数据集
        return Result.ok(this.userExtractService.getByPage(param));
    }


    @PostMapping("/submit")
    @Operation(summary="用户提现记录提交")
    @Inner(false)
    public Result submit(@RequestBody NbmhUserExtract param) {
        param.setCreateTime(new Date());
        boolean ret=this.userExtractService.save(param);
        if (ret) {
            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }
}
