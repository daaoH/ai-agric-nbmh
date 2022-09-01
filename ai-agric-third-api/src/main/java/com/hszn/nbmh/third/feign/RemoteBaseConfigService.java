package com.hszn.nbmh.third.feign;

import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.third.constant.UrlPathConstant;
import com.hszn.nbmh.third.fallback.BaseConfigServiceFallback;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>
 * 配置  暴露接口
 * </p>
 *
 * @author wangjun
 * @since 2022-9-1
 */

@FeignClient(value=ServiceNameConstant.THIRD_SERVICE, path=UrlPathConstant.BASRE_CONFIG, fallback=BaseConfigServiceFallback.class)
public interface RemoteBaseConfigService {


    @GetMapping("/getBySubject/{subject}")
    @Operation(summary="根据业务类型获取数据集")
    Result getBySubject(@PathVariable("subject") String subject);

}
