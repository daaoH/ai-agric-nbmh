package com.hszn.nbmh.third.feign;

import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.third.constant.UrlPathConstant;
import com.hszn.nbmh.third.fallback.RemoteContentCensorServiceFallback;
import com.hszn.nbmh.third.params.out.CensorResultOut;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 百度内容审核接口对外服务
 *
 * @author liwei
 * @version 1.0
 * @since 2022-09-05 14:23
 */
@FeignClient(value = ServiceNameConstant.THIRD_SERVICE, path = UrlPathConstant.CONTENT_CENSOR_URL, fallback = RemoteContentCensorServiceFallback.class)
public interface RemoteContentCensorService {
    @GetMapping("/imagePathDefined")
    Result<CensorResultOut> imagePathDefined(@RequestParam("path") String path);

    @GetMapping("/imageUrlDefined")
    Result<CensorResultOut> imageUrlDefined(@RequestParam("url") String url);

    @GetMapping("/textDefined")
    Result<CensorResultOut> textDefined(@RequestParam("text") String text);
}
