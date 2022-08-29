package com.hszn.nbmh.third.fallback;

import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.third.feign.RemoteOssUploadService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 阿里云 oss云存储 熔断
 * </p>
 *
 * @author wangjun
 * @since 2022-08-18
 */

@Component
public class OssUploadServiceFallback implements RemoteOssUploadService {

    @Override
    public Result getHost() {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result fileUpload(MultipartFile file) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Result filesUpload(List<MultipartFile> files) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }
}
