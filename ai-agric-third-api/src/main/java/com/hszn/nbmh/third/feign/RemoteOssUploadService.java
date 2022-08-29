package com.hszn.nbmh.third.feign;

import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.third.constant.UrlPathConstant;
import com.hszn.nbmh.third.fallback.OssUploadServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 阿里云 oss云存储 暴露接口
 * </p>
 *
 * @author wangjun
 * @since 2022-08-18
 */

@FeignClient(value=ServiceNameConstant.THIRD_SERVICE, path=UrlPathConstant.OSSUPLOAD, fallback=OssUploadServiceFallback.class)
public interface RemoteOssUploadService {

    @GetMapping("getHost")
    Result getHost();

    @PostMapping("/fileUpload")
    Result fileUpload(@RequestParam("file") MultipartFile file);

    @PostMapping("/filesUpload")
    Result filesUpload(@RequestParam("files") List<MultipartFile> files);

}
