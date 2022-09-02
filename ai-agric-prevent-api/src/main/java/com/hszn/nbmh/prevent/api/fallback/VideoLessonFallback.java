package com.hszn.nbmh.prevent.api.fallback;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.entity.NbmhVideoLesson;
import com.hszn.nbmh.prevent.api.feign.RemoteVideoLessonService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 视频课堂 暴露接口熔断类
 * </p>
 *
 * @author MCR
 * @since 2022-09-02
 */

@Component
public class VideoLessonFallback implements RemoteVideoLessonService {

    @Override
    public Result add(NbmhVideoLesson entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result getById(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result update(NbmhVideoLesson entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<IPage<NbmhVideoLesson>> query(NbmhVideoLesson entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<List<NbmhVideoLesson>> list(NbmhVideoLesson entity, List<OrderItem> orderItemList) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result delete(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }
}
