package com.hszn.nbmh.user.api.fallback;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryCondition;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.user.api.entity.NbmhAnimalDoctorDetail;
import com.hszn.nbmh.user.api.feign.RemoteAnimalDoctorDetailService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 兽医详情 暴露接口熔断类
 * </p>
 *
 * @author MCR
 * @since 2022-09-06
 */
@Component
public class AnimalDoctorDetailServiceFallback implements RemoteAnimalDoctorDetailService {


    @Override
    public Result add(NbmhAnimalDoctorDetail entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<NbmhAnimalDoctorDetail> getById(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result update(NbmhAnimalDoctorDetail entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<IPage<NbmhAnimalDoctorDetail>> query(QueryCondition<NbmhAnimalDoctorDetail> queryCondition, int pageNum, int pageSize) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<List<NbmhAnimalDoctorDetail>> list(QueryCondition<NbmhAnimalDoctorDetail> queryCondition) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result delete(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result updateAcceptOrderNum(Long doctorId) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

}
