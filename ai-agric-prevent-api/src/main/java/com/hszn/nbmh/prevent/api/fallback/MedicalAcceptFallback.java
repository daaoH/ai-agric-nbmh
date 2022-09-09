package com.hszn.nbmh.prevent.api.fallback;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryCondition;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.entity.NbmhMedicalAccept;
import com.hszn.nbmh.prevent.api.feign.RemoteMedicalAcceptService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 动物诊疗接单记录 暴露接口熔断类
 * </p>
 *
 * @author MCR
 * @since 2022-09-02
 */

@Component
public class MedicalAcceptFallback implements RemoteMedicalAcceptService {

    @Override
    public Result add(NbmhMedicalAccept entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result getById(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result update(NbmhMedicalAccept entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<IPage<NbmhMedicalAccept>> query(QueryCondition<NbmhMedicalAccept> queryCondition, int pageNum, int pageSize) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<List<NbmhMedicalAccept>> list(QueryCondition<NbmhMedicalAccept> queryCondition) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result delete(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result acceptOrder(NbmhMedicalAccept nbmhMedicalAccept) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }
}
