//package com.hszn.nbmh.user.api.fallback;
//
//import com.hszn.nbmh.common.core.enums.CommonEnum;
//import com.hszn.nbmh.common.core.mould.CodeImageRequest;
//import com.hszn.nbmh.common.core.utils.Result;
//import com.hszn.nbmh.user.api.feign.RemoteThirdService;
//import org.springframework.stereotype.Component;
//
///**
// * @Author：wangjun
// * @Description:
// * @Date:下午1:01 22/8/29
// * @Modified By:
// */
//@Component
//public class ThirdServiceFallback implements RemoteThirdService {
//
//
//    @Override
//    public Result generate(CodeImageRequest param) {
//        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
//    }
//
//}
