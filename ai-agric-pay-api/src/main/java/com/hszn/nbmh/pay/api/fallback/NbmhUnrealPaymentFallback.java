package com.hszn.nbmh.pay.api.fallback;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryCondition;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.pay.api.entity.NbmhUnrealPayment;
import com.hszn.nbmh.pay.api.feign.RemoteUnrealPaymentService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 虚拟支付信息 暴露接口熔断类
 * </p>
 *
 * @author MCR
 * @since 2022-09-14
 */

@Component
public class NbmhUnrealPaymentFallback implements RemoteUnrealPaymentService {

    @Override
    public Result add(NbmhUnrealPayment entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<NbmhUnrealPayment> getById(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result update(NbmhUnrealPayment entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<IPage<NbmhUnrealPayment>> query(QueryCondition<NbmhUnrealPayment> queryCondition, int pageNum, int pageSize) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<List<NbmhUnrealPayment>> list(QueryCondition<NbmhUnrealPayment> queryCondition) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result delete(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }
}
