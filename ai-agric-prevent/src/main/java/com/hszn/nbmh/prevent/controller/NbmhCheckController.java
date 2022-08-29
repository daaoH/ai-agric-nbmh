package com.hszn.nbmh.prevent.controller;


import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.entity.NbmhCheck;
import com.hszn.nbmh.prevent.api.params.input.CheckParam;
import com.hszn.nbmh.prevent.service.INbmhCheckService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 稽查上报 前端控制器
 * </p>
 *
 * @author wangjun
 * @since 2022-08-25
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/nbmh-check")
@Tag(name="稽查上报")
public class NbmhCheckController {

    private final INbmhCheckService checkService;

    @PostMapping("/submit")
    @Operation(summary="稽查上报-提交")
    public Result submit(@RequestBody NbmhCheck param) {
        //提交
        boolean ret=this.checkService.save(param);
        if (ret) {
            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }


    @PostMapping("/getByPage")
    @Operation(summary="稽查列表(分页查询)")
    public Result getByPage(@RequestBody QueryRequest<CheckParam> param) {
        return Result.ok(this.checkService.getByPage(param).getRecords());
    }

}
