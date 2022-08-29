package com.hszn.nbmh.prevent.api.fallback;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.entity.NbmhPreventStation;
import com.hszn.nbmh.prevent.api.feign.RemotePreventStationService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 防疫站信息 暴露接口熔断类
 * </p>
 *
 * @author MCR
 * @since 2022-08-16
 */

@Component
public class PreventStationServiceFallback implements RemotePreventStationService {

    @Override
    public Result add(NbmhPreventStation entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result getById(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result update(NbmhPreventStation entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<IPage<NbmhPreventStation>> query(NbmhPreventStation entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<List<NbmhPreventStation>> list(NbmhPreventStation entity, List<OrderItem> orderItemList) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result delete(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }
}
