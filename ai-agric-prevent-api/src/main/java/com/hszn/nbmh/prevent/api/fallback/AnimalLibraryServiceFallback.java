package com.hszn.nbmh.prevent.api.fallback;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryCondition;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.entity.NbmhAnimalLibrary;
import com.hszn.nbmh.prevent.api.feign.RemoteAnimalLibraryService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 动物基因库/病例库 暴露接口熔断类
 * </p>
 *
 * @author MCR
 * @since 2022-09-02
 */

@Component
public class AnimalLibraryServiceFallback implements RemoteAnimalLibraryService {

    @Override
    public Result add(NbmhAnimalLibrary entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result getById(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result update(NbmhAnimalLibrary entity) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<IPage<NbmhAnimalLibrary>> query(QueryCondition<NbmhAnimalLibrary> queryCondition, int pageNum, int pageSize) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<List<NbmhAnimalLibrary>> list(QueryCondition<NbmhAnimalLibrary> queryCondition) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result delete(Long id) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result audit(NbmhAnimalLibrary nbmhAnimalLibrary) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }
}
