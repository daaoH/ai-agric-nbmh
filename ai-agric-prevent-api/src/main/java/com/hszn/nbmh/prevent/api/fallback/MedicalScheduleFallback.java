package com.hszn.nbmh.prevent.api.fallback;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.entity.NbmhMedicalSchedule;
import com.hszn.nbmh.prevent.api.feign.RemoteMedicalScheduleService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 兽医接诊时间设置 暴露接口熔断类
 * </p>
 *
 * @author MCR
 * @since 2022-09-02
 */

@Component
public class MedicalScheduleFallback implements RemoteMedicalScheduleService {

    @Override
    public Result add(NbmhMedicalSchedule entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result getById(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result update(NbmhMedicalSchedule entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<IPage<NbmhMedicalSchedule>> query(NbmhMedicalSchedule entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<List<NbmhMedicalSchedule>> list(NbmhMedicalSchedule entity, List<OrderItem> orderItemList) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result delete(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }
}
