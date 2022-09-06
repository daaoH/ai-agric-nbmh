package com.hszn.nbmh.prevent.api.fallback;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.entity.NbmhButcherReport;
import com.hszn.nbmh.prevent.api.feign.RemoteButcherReportService;
import com.hszn.nbmh.prevent.api.params.out.NbmhButcherReportDetail;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 屠宰申报信息 暴露接口熔断类
 * </p>
 *
 * @author MCR
 * @since 2022-08-16
 */

@Component
public class ButcherReportServiceFallback implements RemoteButcherReportService {

    @Override
    public Result add(NbmhButcherReport entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result getById(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result update(NbmhButcherReport entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<IPage<NbmhButcherReport>> query(NbmhButcherReport entity, int pageNum, int pageSize, List<OrderItem> orderItemList) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<List<NbmhButcherReport>> list(NbmhButcherReport entity, List<OrderItem> orderItemList) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result delete(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<NbmhButcherReportDetail> detail(NbmhButcherReport nbmhButcherReport) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }
}
