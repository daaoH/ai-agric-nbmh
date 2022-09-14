package com.hszn.nbmh.cms.controller;
import com.hszn.nbmh.cms.api.entity.SysDictItem;
import com.hszn.nbmh.cms.service.ISysDictItemService;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 字典项 前端控制器
 * </p>
 *
 * @author 李肖
 * @since 2022-08-31
 */
@Validated
@RequiredArgsConstructor
@Tag(name = "字典接口")
@RestController
@RequestMapping("/dict-item")
public class SysDictItemController {
    @Autowired
    private final ISysDictItemService sysDictItemService;

    @PostMapping("/submit")
    @Operation(summary = "新增字典", method = "POST")
    @Inner(false)
    public Result add(@RequestBody SysDictItem sysDictItem) {

        if (sysDictItemService.save(sysDictItem)) {
            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }


    @PutMapping("/update")
    @Operation(summary = "字典子级级修改")
    @Inner(false)
    public Result update(@RequestBody SysDictItem sysDictItem) {
        // 修改字典父级创建
        if (sysDictItemService.updateById(sysDictItem)) {
            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_UPDATE_FAILED.getMsg());
    }


    @DeleteMapping("delete/{id}")
    @Operation(summary = "删除字典")
    @Inner(false)
    public Result delete(@PathVariable Long id) {

        if (sysDictItemService.removeById(id)) {
            return Result.ok();
        }
        return Result.failed(CommonEnum.DATA_DELETE_FAILED.getMsg());

    }

    @PostMapping("/getByPage")
    @Operation(summary = "字典-分页")
    @Inner(false)
    public Result getByPage(@RequestBody QueryRequest<SysDictItem> sysDictItem) {
        return Result.ok(sysDictItemService.getByPage(sysDictItem));
    }
}
