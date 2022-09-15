package com.hszn.nbmh.cms.controller;


import com.hszn.nbmh.cms.api.entity.NbmhRuleExplain;
import com.hszn.nbmh.cms.api.entity.NbmhViolationRecord;
import com.hszn.nbmh.cms.service.INbmhViolationRecordService;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 违规记录表 前端控制器
 * </p>
 *
 * @author 李肖
 * @since 2022-09-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/record")
@Tag(name = "规则接口")
public class NbmhViolationRecordController {
    @Autowired
    private INbmhViolationRecordService recordService;

    @PostMapping("/submit")
    @Operation(summary = "创建违规记录")
    @Inner(false)
    public Result submit(@RequestBody NbmhViolationRecord record) {

        // 提交字典创建
        if (recordService.save(record)) {

            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }
    @PostMapping("/update")
    @Operation(summary = "更新违规记录")
    @Inner(false)
    public Result update(@RequestBody NbmhViolationRecord record) {
        //更新违规记录
        if (recordService.updateById(record)) {
            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_UPDATE_FAILED.getMsg());
    }

    @PostMapping("/del/{id}")
    @Parameters({@Parameter(description = "数据id", name = "id")})
    @Operation(summary = "删除违规记录")
    @Inner(false)
    public Result del(@PathVariable("id") Long id) {
        // 删除规则信息
        if (recordService.removeById(id)) {
            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_DELETE_FAILED.getMsg());
    }

    @PostMapping("/getByPage")
    @Operation(summary = "违规记录-分页")
    @Inner(false)
    public Result getByPage(@RequestBody QueryRequest<NbmhViolationRecord> record) {
        return Result.ok(recordService.getByPage(record));
    }
}
