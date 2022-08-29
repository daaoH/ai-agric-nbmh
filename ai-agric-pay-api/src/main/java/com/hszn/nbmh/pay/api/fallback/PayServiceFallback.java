package com.hszn.nbmh.pay.api.fallback;

import com.alipay.api.AlipayApiException;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.pay.api.feign.RemotePayService;
import com.hszn.nbmh.pay.api.params.input.PayOrderVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:13 22/8/21
 * @Modified By:
 */

@Component
public class PayServiceFallback implements RemotePayService {


    @Override
    public Result wxPay(HttpServletRequest request, PayOrderVo payOrderVo) throws Exception {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public String wxAppCallBack(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "微信回调异常!";
    }

    @Override
    public Result aliPay(HttpServletRequest request, PayOrderVo payOrderVo) throws IOException, AlipayApiException {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public Object aliPayNotify(HttpServletRequest request) {
        return Result.failed(CommonEnum.FALL_BACK_MSG.getMsg());
    }

    @Override
    public void refunds(Long orderId) throws Exception {
    }
}
