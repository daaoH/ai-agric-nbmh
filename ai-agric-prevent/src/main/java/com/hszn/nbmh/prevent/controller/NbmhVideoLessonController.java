package com.hszn.nbmh.prevent.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryCondition;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.NbmhVideoLesson;
import com.hszn.nbmh.prevent.service.INbmhVideoLessonService;
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
 * 视频课堂视频 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-08-31
 */
@Tag(name = "视频课堂视频")
@Validated
@RestController
@RequestMapping("/nbmh-video-lesson")
public class NbmhVideoLessonController {

    @Autowired
    private INbmhVideoLessonService nbmhVideoLessonService;

    @Operation(summary = "新增视频课堂视频", method = "POST")
    @PostMapping("/add")
    @Inner(false)
    public Result add(@RequestBody @Validated({NbmhVideoLesson.Save.class}) NbmhVideoLesson nbmhVideoLesson) {

        List<Integer> idList = nbmhVideoLessonService.save(Collections.singletonList(nbmhVideoLesson));
        if (idList != null && idList.size() > 0) {
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询视频课堂视频", method = "GET")
    @PostMapping("/{id}")
    @Inner(false)
    public Result<NbmhVideoLesson> getById(@PathVariable(value = "id") @NotNull(message = "视频课堂视频Id不能为空") Long id) {

        return Result.ok(nbmhVideoLessonService.getById(id));
    }

    @Operation(summary = "更新视频课堂视频", method = "PUT")
    @PutMapping
    @Inner(false)
    public Result update(@RequestBody @Validated({NbmhVideoLesson.Update.class}) NbmhVideoLesson nbmhVideoLesson) {

        nbmhVideoLessonService.update(Collections.singletonList(nbmhVideoLesson));
        return Result.ok();
    }

    @Operation(summary = "分页查询视频课堂视频", method = "POST")
    @Parameters({@Parameter(description = "页码", name = "pageNum"), @Parameter(description = "每页显示条数", name = "pageSize")})
    @PostMapping("/query")
    @Inner(false)
    public Result<IPage<NbmhVideoLesson>> query(@RequestBody QueryCondition<NbmhVideoLesson> queryCondition,
                                                @RequestParam @DecimalMin("1") int pageNum,
                                                @RequestParam @DecimalMin("1") int pageSize) {

        return Result.ok(nbmhVideoLessonService.query(queryCondition.getEntity(), pageNum, pageSize, queryCondition.getOrderItemList()));
    }

    @Operation(summary = "查询视频课堂视频", method = "POST")
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhVideoLesson>> list(@RequestBody QueryCondition<NbmhVideoLesson> queryCondition) {

        return Result.ok(nbmhVideoLessonService.list(queryCondition.getEntity(), queryCondition.getOrderItemList()));
    }

    @Operation(summary = "删除视频课堂视频", method = "DELETE")
    @DeleteMapping("delete/{id}")
    @Inner(false)
    public Result delete(@PathVariable(value = "id") @NotNull(message = "视频课堂视频Id不能为空") Long id) {

        nbmhVideoLessonService.delete(Collections.singletonList(id));
        return Result.ok();
    }

    @Operation(summary = "管理员审核视频课堂视频", method = "PUT")
    @PutMapping("/audit")
    @Inner(false)
    public Result audit(@RequestBody NbmhVideoLesson nbmhVideoLesson) {

        nbmhVideoLessonService.audit(Collections.singletonList(nbmhVideoLesson));
        return Result.ok();
    }

}
