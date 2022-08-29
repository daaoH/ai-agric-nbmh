//package com.hszn.nbmh.third.feign;
//
//import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
//import com.hszn.nbmh.common.core.mould.CodeImageRequest;
//import com.hszn.nbmh.common.core.utils.Result;
//import com.hszn.nbmh.third.constant.UrlPathConstant;
//import com.hszn.nbmh.third.fallback.CodeImageServiceFallback;
//import org.springframework.cloud.openfeign.FeignClient;
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
//@FeignClient(value=ServiceNameConstant.THIRD_SERVICE, path=UrlPathConstant.QRCODE, fallback=CodeImageServiceFallback.class)
//public interface RemoteCodeImageService {
//
//
//    @PostMapping("/generate")
//    Result generate(@RequestBody CodeImageRequest param);
//
//
//}
