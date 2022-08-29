package com.hszn.nbmh.cms.api.fallback;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.cms.api.entity.NbmhAgreement;
import com.hszn.nbmh.cms.api.feign.RemoteAdService;
import com.hszn.nbmh.cms.api.feign.RemoteAgreementService;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 用户协议表 暴露接口熔断类
 * </p>
 *
 * @author MCR
 * @since 2022-08-20
 */

@Component
public class NbmhAgreementServiceFallback implements RemoteAgreementService {

    @Override
    public Result add(NbmhAgreement entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result getById(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result update(NbmhAgreement entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<IPage<NbmhAgreement>> query(NbmhAgreement entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<List<NbmhAgreement>> list(NbmhAgreement entity, List<OrderItem> orderItemList) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result delete(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }
}
