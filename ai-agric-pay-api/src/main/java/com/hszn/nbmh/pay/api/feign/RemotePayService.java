package com.hszn.nbmh.pay.api.feign;

import com.alipay.api.AlipayApiException;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.pay.api.constant.UrlPathConstant;
import com.hszn.nbmh.pay.api.fallback.PayServiceFallback;
import com.hszn.nbmh.pay.api.params.input.PayOrderVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午9:59 22/8/21
 * @Modified By:
 */

@FeignClient(value=ServiceNameConstant.PAY_SERVICE, path=UrlPathConstant.PAYMENT, fallback=PayServiceFallback.class)
public interface RemotePayService {


    /**
     * 微信支付
     *
     * @return
     */
    @ApiOperation(value="微信支付")
    @PostMapping("/wxPay")
    Result wxPay(HttpServletRequest request, @RequestBody PayOrderVo payOrderVo) throws Exception;


    /**
     * 微信回调
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    /**
     * 微信支付异步结果通知
     */
    @ApiOperation(value="微信回调")
    @RequestMapping(value="/wxapppay/notify", method={RequestMethod.GET, RequestMethod.POST})
    String wxAppCallBack(HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * 支付宝支付
     *
     * @return
     */
    @ApiOperation(value="支付宝支付")
    @PostMapping("/aliPay")
    Result aliPay(HttpServletRequest request, @RequestBody PayOrderVo payOrderVo) throws IOException, AlipayApiException;


    /**
     * 支付宝回调
     *
     * @param request
     * @return
     */
    @ApiOperation(value="支付宝回调")
    @RequestMapping(value="/alipay/notify", method={RequestMethod.GET, RequestMethod.POST})
    Object aliPayNotify(HttpServletRequest request);

    /**
     * 申请退款
     *
     * @param orderId
     * @throws Exception
     */
    @ApiOperation(value="申请退款")
    @PutMapping("/refunds/{orderDetailsId}")
    void refunds(@PathVariable Long orderId) throws Exception;


}