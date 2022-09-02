package com.hszn.nbmh.prevent.api.fallback;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.entity.NbmhMedicalOrder;
import com.hszn.nbmh.prevent.api.feign.RemoteMedicalOrderService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 诊断下单记录 暴露接口熔断类
 * </p>
 *
 * @author MCR
 * @since 2022-09-02
 */

@Component
public class MedicalOrderFallback implements RemoteMedicalOrderService {

    @Override
    public Result add(NbmhMedicalOrder entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result getById(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result update(NbmhMedicalOrder entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<IPage<NbmhMedicalOrder>> query(NbmhMedicalOrder entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<List<NbmhMedicalOrder>> list(NbmhMedicalOrder entity, List<OrderItem> orderItemList) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result delete(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }
}
