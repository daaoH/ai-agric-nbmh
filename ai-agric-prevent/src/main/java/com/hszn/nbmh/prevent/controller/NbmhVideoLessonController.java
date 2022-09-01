package com.hszn.nbmh.prevent.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.NbmhVideoLesson;
import com.hszn.nbmh.prevent.service.INbmhVideoLessonService;
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
 * 视频课堂 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-08-31
 */
@Tag(name = "视频课堂")
@RestController
@RequestMapping("/nbmh-video-lesson")
public class NbmhVideoLessonController {

    @Autowired
    private INbmhVideoLessonService nbmhVideoLessonService;

    @Operation(summary = "新增视频课堂", method = "POST")
    @PostMapping("/add")
    public Result add(@RequestBody NbmhVideoLesson nbmhVideoLesson) {

        List<Integer> idList = nbmhVideoLessonService.save(Collections.singletonList(nbmhVideoLesson));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询视频课堂", method = "GET")
    @PostMapping("/{id}")
    public Result<NbmhVideoLesson> getById(@PathVariable(value = "id") @NotBlank Long id) {

        return Result.ok(nbmhVideoLessonService.getById(id));
    }

    @Operation(summary = "更新视频课堂", method = "PUT")
    @PutMapping
    public Result update(@RequestBody NbmhVideoLesson nbmhVideoLesson) {

        nbmhVideoLessonService.update(Collections.singletonList(nbmhVideoLesson));
        return Result.ok();
    }

    @Operation(summary = "分页查询视频课堂", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize"), @Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/query")
    public Result<IPage<NbmhVideoLesson>> query(@RequestBody NbmhVideoLesson nbmhVideoLesson,
                                                @RequestParam @DecimalMin("1") int pageNum,
                                                @RequestParam @DecimalMin("1") int pageSize,
                                                @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        IPage<NbmhVideoLesson> butcherReportPage = nbmhVideoLessonService.query(nbmhVideoLesson, pageNum, pageSize, orderItemList);

        return Result.ok(butcherReportPage);
    }

    @Operation(summary = "查询视频课堂", method = "POST")
    @Parameters({@Parameter(description = "排序条件集合", name = "orderItemList")})
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhVideoLesson>> list(@RequestBody NbmhVideoLesson nbmhVideoLesson,
                                              @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        return Result.ok(nbmhVideoLessonService.list(nbmhVideoLesson, orderItemList));
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "删除视频课堂")
    public Result delete(@PathVariable Long id) {

        nbmhVideoLessonService.delete(Collections.singletonList(id));
        return Result.ok();
    }


}
