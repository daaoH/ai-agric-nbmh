//package com.hszn.nbmh.user.api.feign;
//
//import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
//import com.hszn.nbmh.common.core.mould.CodeImageRequest;
//import com.hszn.nbmh.common.core.utils.Result;
//import com.hszn.nbmh.user.api.fallback.ThirdServiceFallback;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
///**
// * <p>
// * 二维码 暴露接口
// * </p>
// *
// * @author wangjun
// * @since 2022-08-18
// */
//
//@FeignClient(value=ServiceNameConstant.THIRD_SERVICE, fallback=ThirdServiceFallback.class)
//public interface RemoteThirdService {
//
//    @PostMapping("/cloud-qrcode/generate")
//    Result generate(@RequestBody CodeImageRequest param);
//
//}
