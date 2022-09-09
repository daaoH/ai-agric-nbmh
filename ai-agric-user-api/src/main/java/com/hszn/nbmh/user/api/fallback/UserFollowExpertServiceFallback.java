package com.hszn.nbmh.user.api.fallback;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryCondition;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.user.api.entity.NbmhUserFollowExpert;
import com.hszn.nbmh.user.api.feign.RemoteUserFollowExpertService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 关注的专家记录 暴露接口熔断类
 * </p>
 *
 * @author MCR
 * @since 2022-09-06
 */
@Component
public class UserFollowExpertServiceFallback implements RemoteUserFollowExpertService {


    @Override
    public Result add(NbmhUserFollowExpert entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result getById(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result update(NbmhUserFollowExpert entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<IPage<NbmhUserFollowExpert>> query(QueryCondition<NbmhUserFollowExpert> queryCondition, int pageNum, int pageSize) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<List<NbmhUserFollowExpert>> list(QueryCondition<NbmhUserFollowExpert> queryCondition) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result delete(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

}
