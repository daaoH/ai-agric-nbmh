package com.hszn.nbmh.prevent.api.fallback;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryCondition;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.entity.NbmhPrescriptionDrug;
import com.hszn.nbmh.prevent.api.feign.RemotePrescriptionDrugService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 处方药品列表 暴露接口熔断类
 * </p>
 *
 * @author MCR
 * @since 2022-09-07
 */

@Component
public class PrescriptionDrugServiceFallback implements RemotePrescriptionDrugService {

    @Override
    public Result add(NbmhPrescriptionDrug entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<NbmhPrescriptionDrug> getById(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result update(NbmhPrescriptionDrug entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<IPage<NbmhPrescriptionDrug>> query(QueryCondition<NbmhPrescriptionDrug> queryCondition, int pageNum, int pageSize) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<List<NbmhPrescriptionDrug>> list(QueryCondition<NbmhPrescriptionDrug> queryCondition) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result delete(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }
}
