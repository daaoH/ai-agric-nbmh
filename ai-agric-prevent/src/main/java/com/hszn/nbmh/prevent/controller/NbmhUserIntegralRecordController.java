package com.hszn.nbmh.prevent.controller;

import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.entity.NbmhUserIntegralRecord;
import com.hszn.nbmh.prevent.api.params.input.UserIntegralRecordParam;
import com.hszn.nbmh.prevent.service.INbmhUserIntegralRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 积分记录信息 前端控制器
 * </p>
 *
 * @author wangjun
 * @since 2022-08-22
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/nbmh-user-integral-record")
@Tag(name="积分记录")
public class NbmhUserIntegralRecordController {

    private final INbmhUserIntegralRecordService nbmhUserIntegralRecordService;

    @PostMapping("/submit")
    @Operation(summary="提交积分记录")
    public Result submit(@RequestBody List<NbmhUserIntegralRecord> params) {
        boolean ret=nbmhUserIntegralRecordService.saveBatch(params);
        // 提交积分记录
        if (ret) {
            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @PostMapping("/integralRecordDetails")
    @Operation(summary="防疫员详情-积分获取列表")
    public Result integralRecordDetails(@RequestBody UserIntegralRecordParam param) {
        return Result.ok(this.nbmhUserIntegralRecordService.integralRecordDetails(param));
    }

    @PostMapping("/integralRecord")
    @Operation(summary="积分记录")
    public Result integralRecord(@RequestBody UserIntegralRecordParam param) {
        return Result.ok(this.nbmhUserIntegralRecordService.integralRecord(param));
    }

    @PostMapping("/vUserIntegralRecordResult")
    @Operation(summary=" 防疫员积分获取记录")
    public Result vUserIntegralRecordResult(@RequestBody UserIntegralRecordParam param) {
        return Result.ok(this.nbmhUserIntegralRecordService.vUserIntegralRecordResult(param));
    }

}
