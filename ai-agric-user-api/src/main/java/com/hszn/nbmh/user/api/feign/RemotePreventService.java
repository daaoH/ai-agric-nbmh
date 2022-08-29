package com.hszn.nbmh.user.api.feign;

import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.user.api.fallback.PreventServiceFallback;
import com.hszn.nbmh.user.api.params.input.NbmhPreventStationParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 二维码 暴露接口
 * </p>
 *
 * @author wangjun
 * @since 2022-08-29
 */

@FeignClient(value=ServiceNameConstant.PREVENT_SERVICE, fallback=PreventServiceFallback.class)
public interface RemotePreventService {

    @PostMapping("/add")
    Result add(@RequestBody NbmhPreventStationParam entity);


}
