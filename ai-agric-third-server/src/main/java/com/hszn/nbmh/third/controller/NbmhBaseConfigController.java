package com.hszn.nbmh.third.controller;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.third.entity.NbmhBaseConfig;
import com.hszn.nbmh.third.service.INbmhBaseConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 全局配置表 前端控制器
 * </p>
 *
 * @author wangjun
 * @since 2022-08-26
 */
@Slf4j
@Validated
@RestController
@Scope("prototype")
@RequiredArgsConstructor
@RequestMapping("/nbmh-base-config")
@Tag(name="全局配置")
public class NbmhBaseConfigController {

    private final INbmhBaseConfigService baseConfigService;

    @PostMapping("/getByPage")
    @Operation(summary="获取分页数据")
    public Result getByPage(@RequestBody QueryRequest<NbmhBaseConfig> param) {
        return Result.ok(this.baseConfigService.getByPage(param));
    }

    @GetMapping("/getBySubject/{subject}")
    @Operation(summary="根据业务类型获取数据集")
    public Result getBySubject(@PathVariable("subject") String subject) {
        return Result.ok(this.baseConfigService.getBySubject(subject));
    }

    @PostMapping
    @Operation(summary="全局配置创建提交")
    public Result addSubmit(@RequestBody NbmhBaseConfig params) {
        if (ObjectUtils.isNotEmpty(params)) {
            // 提交
            params.setCreateTime(new Date());
            boolean ret=this.baseConfigService.save(params);
            if (ret) {
                return Result.ok();
            }
            return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
        }
        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @PutMapping
    @Operation(summary="全局配置修改提交")
    public Result updateSubmit(@RequestBody NbmhBaseConfig params) {
        if (ObjectUtils.isNotEmpty(params)) {
            // 提交
            params.setUpdateTime(new Date());
            boolean ret=this.baseConfigService.updateById(params);
            if (ret) {
                return Result.ok();
            }
            return Result.failed(CommonEnum.DATA_UPDATE_FAILED.getMsg());
        }
        return Result.failed(CommonEnum.DATA_UPDATE_FAILED.getMsg());
    }

    @Delete("/{id}")
    @Operation(summary="删除")
    @Parameters({@Parameter(description="id", name="id")})
    public Result delete(@PathVariable("id") Long id) {
        NbmhBaseConfig baseConfig=this.baseConfigService.getById(id);
        if (ObjectUtils.isNotEmpty(baseConfig)) {
            // 删除
            boolean ret=this.baseConfigService.removeById(baseConfig);
            if (ret) {
                return Result.ok();
            }
            return Result.failed(CommonEnum.DATA_DELETE_FAILED.getMsg());
        }
        return Result.failed(CommonEnum.DATA_DELETE_FAILED.getMsg());
    }

}
