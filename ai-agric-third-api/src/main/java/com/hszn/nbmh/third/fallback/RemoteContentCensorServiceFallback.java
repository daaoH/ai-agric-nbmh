package com.hszn.nbmh.third.fallback;

import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.third.feign.RemoteContentCensorService;
import com.hszn.nbmh.third.params.out.CensorResultOut;

/**
 * 百度审核fallback类
 *
 * @author liwei
 * @version 1.0
 * @since 2022-09-05 14:27
 */
public class RemoteContentCensorServiceFallback implements RemoteContentCensorService {
    @Override
    public Result<CensorResultOut> imagePathDefined(String path) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<CensorResultOut> imageUrlDefined(String url) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result<CensorResultOut> textDefined(String text) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }
}
