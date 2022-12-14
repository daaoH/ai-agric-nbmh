package com.hszn.nbmh.prevent.api.fallback;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.entity.NbmhEarRecord;
import com.hszn.nbmh.prevent.api.feign.RemoteNbmhEarRecordService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 耳标补打信息 暴露接口熔断类
 * </p>
 *
 * @author MCR
 * @since 2022-08-16
 */

@Component
public class NbmhEarRecordFallback implements RemoteNbmhEarRecordService {

    @Override
    public Result add(NbmhEarRecord entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<NbmhEarRecord> getById(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result update(NbmhEarRecord entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<IPage<NbmhEarRecord>> query(NbmhEarRecord entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<List<NbmhEarRecord>> list(NbmhEarRecord entity, List<OrderItem> orderItemList) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result delete(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }
}
