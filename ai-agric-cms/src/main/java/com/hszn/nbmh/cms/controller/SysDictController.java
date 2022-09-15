package com.hszn.nbmh.cms.controller;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hszn.nbmh.cms.api.entity.SysDict;
import com.hszn.nbmh.cms.api.entity.SysDictItem;
import com.hszn.nbmh.cms.api.params.out.DictResultOut;
import com.hszn.nbmh.cms.service.ISysDictItemService;
import com.hszn.nbmh.cms.service.ISysDictService;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author 李肖
 * @since 2022-08-31
 */

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/sys-dict")
@Tag(name = "字典接口")
public class SysDictController {

    //字典接口
    private final ISysDictService sysDictService;

    //字典明细
    private final ISysDictItemService sysDictItemService;

    @PostMapping("/getByPage")
    @Operation(summary = "字典-分页")
    @Inner(false)
    public Result getByPage(@RequestBody QueryRequest<SysDict> sysDict) {
        return Result.ok(sysDictService.getByPage(sysDict));
    }


    @GetMapping("/getById/{type}")
    @Operation(summary = "字典-根据id获取字典及明细")
    @Inner(false)
    public Result getById(@PathVariable("type") @Validated String type) {
        //返回结果
        DictResultOut result = new DictResultOut();

        //获取字典结果
        SysDict dict = sysDictService.getOne(Wrappers.<SysDict>query().lambda().eq(SysDict::getType, type).eq(SysDict::getStatus, 0));
        //判断字典数据是否为空..为空直接返回..否者获取字典明细（集合）
        if (ObjectUtils.isEmpty(dict)) {
            return Result.failed(CommonEnum.DATA_NOT_EXIST.getMsg());
        }
        //获取字典明细（集合）
        List<SysDictItem> dictItemList = sysDictItemService.list(Wrappers.<SysDictItem>query().lambda().eq(SysDictItem::getDictId, dict.getId()).eq(SysDictItem::getStatus, 0));
        //组装接口返回结果数据
        result.setDict(dict);
        result.setDictItemList(dictItemList);
        //返回了
        return Result.ok(result);
    }


    @PostMapping("/submit")
    @Operation(summary = "字典创建")
    @Inner(false)
    public Result submit(@RequestBody SysDict dict) {

        // 提交字典创建
        if (sysDictService.save(dict)) {

            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @PostMapping("/update")
    @Operation(summary = "更新字典信息")
    @Inner(false)
    public Result update(@RequestBody SysDict sysDict) {
        //更新字典信息
        if (sysDictService.updateById(sysDict)) {
            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_UPDATE_FAILED.getMsg());
    }


    @PostMapping("/del/{id}")
    @Parameters({@Parameter(description = "数据id", name = "id")})
    @Operation(summary = "删除字典信息")
    @Inner(false)
    public Result del(@PathVariable("id") Long id) {
        // 删除字典
        if (sysDictService.removeById(id)) {
            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_DELETE_FAILED.getMsg());
    }

}
