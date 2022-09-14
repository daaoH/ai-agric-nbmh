package com.hszn.nbmh.user.controller;


import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.user.api.entity.NbmhUserCoinRecord;
import com.hszn.nbmh.user.service.INbmhUserCoinRecordService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 * 农牧币记录信息 前端控制器
 * </p>
 *
 * @author wangjun
 * @since 2022-09-14
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/nbmh-user-coin-record")
public class NbmhUserCoinRecordController {

    //农牧币交易记录接口
    private final INbmhUserCoinRecordService coinRecordService;


    @PostMapping("/getByPage")
    @Operation(summary="农牧币交易记录")
    @Inner(false)
    public Result getByPage(@RequestBody QueryRequest<NbmhUserCoinRecord> param) {
        //获取农牧币交易记录分页数据集
        return Result.ok(this.coinRecordService.getByPage(param));
    }


    @PostMapping("/submit")
    @Operation(summary="农牧币交易记录提交")
    @Inner(false)
    public Result submit(@RequestBody NbmhUserCoinRecord param) {
        param.setCreateTime(new Date());
        boolean ret=this.coinRecordService.save(param);
        if (ret) {
            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

}
