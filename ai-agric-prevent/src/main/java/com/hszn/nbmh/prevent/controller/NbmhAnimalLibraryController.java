package com.hszn.nbmh.prevent.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.NbmhAnimalLibrary;
import com.hszn.nbmh.prevent.service.INbmhAnimalLibraryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
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
@RestController
@RequestMapping("/nbmh-animal-library")
public class NbmhAnimalLibraryController {

    @Autowired
    private INbmhAnimalLibraryService nbmhAnimalLibraryService;

    @Operation(summary = "新增动物基因库/病例库", method = "POST")
    @PostMapping("/add")
    @Inner(false)
    public Result add(@RequestBody NbmhAnimalLibrary nbmhAnimalLibrary) {

        List<Integer> idList = nbmhAnimalLibraryService.save(Collections.singletonList(nbmhAnimalLibrary));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询动物基因库/病例库", method = "GET")
    @PostMapping("/{id}")
    @Inner(false)
    public Result<NbmhAnimalLibrary> getById(@PathVariable(value = "id") @NotBlank Long id) {

        return Result.ok(nbmhAnimalLibraryService.getById(id));
    }

    @Operation(summary = "更新动物基因库/病例库", method = "PUT")
    @PutMapping
    @Inner(false)
    public Result update(@RequestBody NbmhAnimalLibrary nbmhAnimalLibrary) {

        nbmhAnimalLibraryService.update(Collections.singletonList(nbmhAnimalLibrary));
        return Result.ok();
    }

    @Operation(summary = "分页查询动物基因库/病例库", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize"), @Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/query")
    @Inner(false)
    public Result<IPage<NbmhAnimalLibrary>> query(@RequestBody NbmhAnimalLibrary nbmhAnimalLibrary,
                                                  @RequestParam @DecimalMin("1") int pageNum,
                                                  @RequestParam @DecimalMin("1") int pageSize,
                                                  @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        IPage<NbmhAnimalLibrary> butcherReportPage = nbmhAnimalLibraryService.query(nbmhAnimalLibrary, pageNum, pageSize, orderItemList);

        return Result.ok(butcherReportPage);
    }

    @Operation(summary = "查询动物基因库/病例库", method = "POST")
    @Parameters({@Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhAnimalLibrary>> list(@RequestBody NbmhAnimalLibrary nbmhAnimalLibrary,
                                                @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        return Result.ok(nbmhAnimalLibraryService.list(nbmhAnimalLibrary, orderItemList));
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "删除动物基因库/病例库")
    @Inner(false)
    public Result delete(@PathVariable Long id) {

        nbmhAnimalLibraryService.delete(Collections.singletonList(id));
        return Result.ok();
    }


}
