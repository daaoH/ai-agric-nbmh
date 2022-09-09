package com.hszn.nbmh.prevent.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryCondition;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.NbmhAnimalLibrary;
import com.hszn.nbmh.prevent.service.INbmhAnimalLibraryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 动物基因库/病例库 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-08-31
 */

@Tag(name = "动物基因库/病例库管理")
@Validated
@RestController
@RequestMapping("/nbmh-animal-library")
public class NbmhAnimalLibraryController {

    @Autowired
    private INbmhAnimalLibraryService nbmhAnimalLibraryService;

    @Operation(summary = "新增动物基因库/病例库", method = "POST")
    @PostMapping("/add")
    @Inner(false)
    public Result add(@RequestBody @Validated({NbmhAnimalLibrary.Save.class}) NbmhAnimalLibrary nbmhAnimalLibrary) {

        List<Integer> idList = nbmhAnimalLibraryService.save(Collections.singletonList(nbmhAnimalLibrary));
        if (idList != null && idList.size() > 0) {
            return Result.ok(idList);
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询动物基因库/病例库", method = "GET")
    @PostMapping("/{id}")
    @Inner(false)
    public Result<NbmhAnimalLibrary> getById(@PathVariable(value = "id") @NotNull(message = "动物基因库/病例库id不能为空") Long id) {

        return Result.ok(nbmhAnimalLibraryService.getById(id));
    }

    @Operation(summary = "更新动物基因库/病例库", method = "PUT")
    @PutMapping
    @Inner(false)
    public Result update(@RequestBody @Validated({NbmhAnimalLibrary.Update.class}) NbmhAnimalLibrary nbmhAnimalLibrary) {

        nbmhAnimalLibraryService.update(Collections.singletonList(nbmhAnimalLibrary));
        return Result.ok();
    }

    @Operation(summary = "分页查询动物基因库/病例库", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize")})
    @PostMapping("/query")
    @Inner(false)
    public Result<IPage<NbmhAnimalLibrary>> query(@RequestBody QueryCondition<NbmhAnimalLibrary> queryCondition,
                                                  @RequestParam @DecimalMin("1") int pageNum,
                                                  @RequestParam @DecimalMin("1") int pageSize) {

        return Result.ok(nbmhAnimalLibraryService.query(queryCondition.getEntity(), pageNum, pageSize, queryCondition.getOrderItemList()));
    }

    @Operation(summary = "查询动物基因库/病例库", method = "POST")
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhAnimalLibrary>> list(@RequestBody QueryCondition<NbmhAnimalLibrary> queryCondition) {

        return Result.ok(nbmhAnimalLibraryService.list(queryCondition.getEntity(), queryCondition.getOrderItemList()));
    }

    @Operation(summary = "删除动物基因库/病例库", method = "DELETE")
    @DeleteMapping("delete/{id}")
    @Inner(false)
    public Result delete(@PathVariable(value = "id") @NotNull(message = "动物基因库/病例库id不能为空") Long id) {

        nbmhAnimalLibraryService.delete(Collections.singletonList(id));
        return Result.ok();
    }

    @Operation(summary = "管理员审核动物基因库/病例库", method = "POST")
    @PostMapping("/audit")
    @Inner(false)
    public Result audit(@RequestBody @Validated({NbmhAnimalLibrary.Update.class}) NbmhAnimalLibrary nbmhAnimalLibrary) {

        nbmhAnimalLibraryService.audit(Collections.singletonList(nbmhAnimalLibrary));
        return Result.ok();
    }


}
