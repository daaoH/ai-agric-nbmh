package com.hszn.nbmh.cms.controller;


import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.hszn.nbmh.cms.api.entity.NbmhRuleExplain;
import com.hszn.nbmh.cms.api.entity.SysDict;
import com.hszn.nbmh.cms.api.entity.SysDictItem;
import com.hszn.nbmh.cms.service.INbmhRuleExplainService;
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

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 规则说明表 前端控制器
 * </p>
 *
 * @author 李肖
 * @since 2022-09-02
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/rule-explain")
@Tag(name = "规则接口")
public class NbmhRuleExplainController {

    @Autowired
    private INbmhRuleExplainService RuleExplainService;

    @PostMapping("/submit")
    @Operation(summary = "规则创建")
    @Inner(false)
    public Result submit(@RequestBody NbmhRuleExplain nbmhRuleExplain) {
        nbmhRuleExplain.setCreateTime(new Date());
        // 提交字典创建
        if (RuleExplainService.save(nbmhRuleExplain)) {

            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @PostMapping("/update")
    @Operation(summary = "更新规则信息")
    @Inner(false)
    public Result update(@RequestBody NbmhRuleExplain nbmhRuleExplain) {
        nbmhRuleExplain.setUpdateTime(new Date());
        //更新字典信息
        if (RuleExplainService.updateById(nbmhRuleExplain)) {
            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_UPDATE_FAILED.getMsg());
    }


    @PostMapping("/del/{id}")
    @Parameters({@Parameter(description = "数据id", name = "id")})
    @Operation(summary = "删除规则信息")
    @Inner(false)
    public Result del(@PathVariable("id") Long id) {
        // 删除规则信息
        if (RuleExplainService.removeById(id)) {
            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_DELETE_FAILED.getMsg());
    }


    @PostMapping("/getByPage")
    @Operation(summary = "规则信息-分页")
    @Inner(false)
    public Result getByPage(@RequestBody QueryRequest<NbmhRuleExplain> nbmhRuleExplain) {
        return Result.ok(RuleExplainService.getByPage(nbmhRuleExplain));
    }
}
