package com.hszn.nbmh.pay.service;

import com.alipay.api.AlipayApiException;
import com.hszn.nbmh.pay.api.params.input.PayOrderVo;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * <p>
 * 支付
 * </p>
 *
 * @author wangjun
 * @since 2022-8-18
 */
public interface PayService {

    /**
     * 微信支付
     *
     * @param payOrderVo
     * @return
     * @throws IOException
     */
    Map wxPay(HttpServletRequest request, PayOrderVo payOrderVo) throws Exception;

    /**
     * 微信APP支付回调
     *
     * @param resXml
     * @return
     */
    String wxAppCallBack(String resXml) throws Exception;


    /**
     * 退款
     *
     * @param orderId
     * @return
     */
    void refunds(Long orderId) throws Exception;


    /**
     * 支付宝支付
     *
     * @param payOrderVo
     * @return
     * @throws IOException
     * @throws AlipayApiException
     */
    String aliPay(PayOrderVo payOrderVo) throws IOException, AlipayApiException;


    /**
     * 支付宝支付回调
     *
     * @param request
     * @return
     */
    Object aliPayCallback(HttpServletRequest request) throws AlipayApiException;
}
