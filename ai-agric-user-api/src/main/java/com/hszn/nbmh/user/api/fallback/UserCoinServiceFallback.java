package com.hszn.nbmh.user.api.fallback;

import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.user.api.entity.NbmhUserCoinRecord;
import com.hszn.nbmh.user.api.feign.RemoteUserCoinService;
import org.springframework.stereotype.Component;

/**
 * @Author：wangjun
 * @Description:
 * @Date:下午1:01 22/09/14
 * @Modified By:
 */
@Component
public class UserCoinServiceFallback implements RemoteUserCoinService {


    @Override
    public Result getByPage(QueryRequest<NbmhUserCoinRecord> param) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result submit(NbmhUserCoinRecord param) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }
}
