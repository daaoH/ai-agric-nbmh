package com.hszn.nbmh.prevent.api.fallback;

import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.entity.NbmhAnimal;
import com.hszn.nbmh.prevent.api.feign.RemoteAnimalService;
import com.hszn.nbmh.prevent.api.params.input.AnimalParam;
import org.springframework.stereotype.Component;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:13 22/8/17
 * @Modified By:
 */

@Component
public class AnimalServiceFallback implements RemoteAnimalService {


    @Override
    public Result updateById(QueryRequest<AnimalParam> param) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result getCensusByUserId(Long userId, Integer type) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result updateById(NbmhAnimal param) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }
}
