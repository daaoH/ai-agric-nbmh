package com.hszn.nbmh.prevent.api.fallback;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.entity.NbmhFarm;
import com.hszn.nbmh.prevent.api.feign.RemoteNbmhFarmService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 养殖场信息 暴露接口熔断类
 * </p>
 *
 * @author MCR
 * @since 2022-08-16
 */

@Component
public class NbmhFarmFallback implements RemoteNbmhFarmService {

    @Override
    public Result add(NbmhFarm entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<NbmhFarm> getById(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result update(NbmhFarm entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<IPage<NbmhFarm>> query(NbmhFarm entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<List<NbmhFarm>> list(NbmhFarm entity, List<OrderItem> orderItemList) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result delete(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }
}
