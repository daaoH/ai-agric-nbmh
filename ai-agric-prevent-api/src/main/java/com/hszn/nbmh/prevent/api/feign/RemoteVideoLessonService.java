package com.hszn.nbmh.prevent.api.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.constant.UrlPathConstant;
import com.hszn.nbmh.prevent.api.entity.NbmhVideoLesson;
import com.hszn.nbmh.prevent.api.fallback.VideoLessonFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 * 视频课堂 暴露接口
 * </p>
 *
 * @author MCR
 * @since 2022-09-02
 */

@FeignClient(value = ServiceNameConstant.PREVENT_SERVICE, path = UrlPathConstant.VIDEO_LESSON, fallback = VideoLessonFallback.class)
public interface RemoteVideoLessonService {

    @PostMapping("/add")
    Result add(@RequestBody NbmhVideoLesson entity);

    @GetMapping("/{id}")
    Result getById(@PathVariable(value = "id") @NotBlank Long id);

    @PutMapping
    Result update(@RequestBody NbmhVideoLesson entity);

    @PostMapping("/query")
    Result<IPage<NbmhVideoLesson>> query(@RequestBody NbmhVideoLesson entity, @RequestParam @DecimalMin("1") int pageNum, @RequestParam @DecimalMin("1") int pageSize, @RequestParam List<OrderItem> orderItemList);

    @PostMapping("/list")
    Result<List<NbmhVideoLesson>> list(@RequestBody NbmhVideoLesson entity, @RequestParam List<OrderItem> orderItemList);

    @DeleteMapping("delete/{id}")
    Result delete(@PathVariable Long id);
}
