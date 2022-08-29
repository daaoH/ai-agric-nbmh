package com.hszn.nbmh.pay.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.hszn.nbmh.common.core.utils.SnowFlakeIdUtil;
import com.hszn.nbmh.pay.api.params.input.PayOrderVo;
import com.hszn.nbmh.pay.configure.AliPayConfig;
import com.hszn.nbmh.pay.configure.StaticParameter;
import com.hszn.nbmh.pay.service.PayService;
import com.hszn.nbmh.pay.utils.WXConfigUtil;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.ScheduledUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wangjun
 * @since 2022-8-18
 */
@Slf4j
@Service("payService")
@RequiredArgsConstructor
public class PayServiceImpl implements PayService {

    private final AliPayConfig aliPayConfig;

    /**
     * 微信支付
     *
     * @param payOrderVo payOrderVo
     * @return
     */
    @Override
    @Transactional
    public Map wxPay(HttpServletRequest request, PayOrderVo payOrderVo) throws Exception {
        Map<String, String> returnMap=new HashMap<>();
        String ipAddress="127.0.0.1";
        String body="农宝牧盒商品购买-订单号" + payOrderVo.getOrderNo() + "支付总金额" + payOrderVo.getTotalPrice();
        //TODO 校验订单金额是否匹配
        //TODO 支付记录是否创建

        //支付参数
        WXConfigUtil config=new WXConfigUtil();
        WXPay wxpay=new WXPay(config);
        //请求参数封装
        Map<String, String> data=new HashMap<>();
        data.put("appid", "wxb7cb4c1c97351ac5");
        data.put("mch_id", StaticParameter.wechatMchId);
        data.put("nonce_str", WXPayUtil.generateNonceStr());
        data.put("body", body);
        data.put("out_trade_no", payOrderVo.getOrderNo());
        data.put("total_fee", String.valueOf(payOrderVo.getTotalPrice().multiply(new BigDecimal(100)).intValue()));
        data.put("spbill_create_ip", ipAddress); //自己的服务器IP地址
        data.put("notify_url", StaticParameter.wechatAppNotifyUrl);//异步通知地址（请注意必须是外网）
        data.put("trade_type", "APP");//交易类型
        data.put("sign", WXPayUtil.generateSignature(data, config.getKey()));//签名
        try {
            //使用官方API请求预付订单
            Map<String, String> response=wxpay.unifiedOrder(data);
            String returnCode=response.get("return_code");    //获取返回码
            //若返回码为SUCCESS，则会返回一个result_code,再对该result_code进行判断
            if (returnCode.equals("SUCCESS")) {
                //主要返回以下5个参数(必须按照顺序，否则APP报错：-1)
                String resultCode=response.get("result_code");
                returnMap.put("appid", response.get("appid"));
                returnMap.put("noncestr", response.get("nonce_str"));
                if ("SUCCESS".equals(resultCode)) {//resultCode 为SUCCESS，才会返回prepay_id和trade_type
                    returnMap.put("package", "Sign=WXPay");
                    returnMap.put("partnerid", response.get("mch_id"));
                    returnMap.put("prepayid", response.get("prepay_id"));
                    returnMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));//单位为秒
                    String sign=WXPayUtil.generateSignature(returnMap, config.getKey());// 二次签名
                    returnMap.put("sign", sign); //签名
                    return returnMap;
                } else {
                    //此时返回没有预付订单的数据
                    return returnMap;
                }
            } else {
                return returnMap;
            }
        } catch (Exception e) {
            //系统等其他错误的时候
        }
        return returnMap;
    }


    /**
     * 微信APP支付回调
     *
     * @param notifyData
     * @return
     */
    @Override
    @Transactional
    public String wxAppCallBack(String notifyData) throws Exception {
        WXConfigUtil config=null;
        try {
            config=new WXConfigUtil();
        } catch (Exception e) {
            e.printStackTrace();
        }
        WXPay wxpay=new WXPay(config);
        String xmlBack="";
        Map<String, String> notifyMap=null;
        // 调用官方SDK转换成map类型数据
        notifyMap=WXPayUtil.xmlToMap(notifyData);
        //验证签名是否有效，有效则进一步处理
        if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {
            //状态
            String return_code=notifyMap.get("return_code");
            //商户订单号
            String out_trade_no=notifyMap.get("out_trade_no");
            if (return_code.equals("SUCCESS")) {
                if (out_trade_no != null) {
                    // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户的订单状态从退款改成支付成功
                    // 注意特殊情况：微信服务端同样的通知可能会多次发送给商户系统，所以数据持久化之前需要检查是否已经处理过了，处理了直接返回成功标志
                    //业务数据持久化
                    String attach=notifyMap.get("attach");//附加数据，用于区分是那张表订单

                    if (true) {
                        //TODO 库存处理
                        //TODO 订单状态更新
                        //TODO 消息构造参数集合
                    } else {
                        return xmlBack="<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[商户订单数据异常]]></return_msg>" + "</xml> ";
                    }
                    xmlBack="<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                } else {
                    xmlBack="<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                }
            }
            return xmlBack;
        } else {
            //签名错误，如果数据里没有sign字段，也认为是签名错误
            //失败的数据要不要存储？
            return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
    }

    /**
     * 退款处理
     *
     * @param orderId
     * @throws IOException
     * @throws AlipayApiException
     */
    @Override
    public void refunds(Long orderId) throws Exception {
        //TODO 根据订单标识获取订单
        /**
         * 区分类型 进入对应支付退款(参数=PayType)
         * 以下方法参数为订单信息 需要修改参数
         */String payType="";
        if (payType.equals("wxpay")) {
            wxRefunds(orderId);//
        } else if (payType.equals("alipay")) {
            alipayRefunds(orderId);
        } else {
            throw new Exception("该订单信息异常，请确认订单");
        }

    }

    /**
     * 微信处理退款
     *
     * @param "订单信息"
     * @throws IOException
     */
    public void wxRefunds(Long orderId) throws Exception {
        //TODO 获取订单信息 计算退款金额
        //退款金额
        BigDecimal totalPrice=new BigDecimal(0);

        JSONObject order=new JSONObject();
        //订单号
        order.put("out_trade_no", "");
        //生成退款单号
        SnowFlakeIdUtil idWorker=new SnowFlakeIdUtil(0L, 0);
        Long TOrderNoLong=idWorker.nextId();
        String tOrderNo="VRF" + TOrderNoLong.toString();
        order.put("out_refund_no", tOrderNo + "");
        //订单金额信息
        JSONObject amount=new JSONObject();
        BigDecimal num=new BigDecimal("100");
        BigDecimal orderFund=new BigDecimal("0.0");//订单金额总金额
        BigDecimal refund=orderFund.multiply(num).setScale(0, BigDecimal.ROUND_DOWN);
        amount.put("refund", refund);
        amount.put("total", totalPrice.multiply(num).setScale(0, BigDecimal.ROUND_DOWN));
        amount.put("currency", "CNY");
        order.put("amount", amount);
        PrivateKey merchantPrivateKey=PemUtil.loadPrivateKey(new FileInputStream(ResourceUtils.getFile(StaticParameter.wechatKeyPath)));
        // 使用定时更新的签名验证器，不需要传入证书
        ScheduledUpdateCertificatesVerifier verifier=new ScheduledUpdateCertificatesVerifier(
                new WechatPay2Credentials(StaticParameter.wechatMchId, new PrivateKeySigner(StaticParameter.serialNo, merchantPrivateKey)),
                StaticParameter.wechatV3Key.getBytes(StandardCharsets.UTF_8));
        WechatPayHttpClientBuilder builder=WechatPayHttpClientBuilder.create()
                .withMerchant(StaticParameter.wechatMchId, StaticParameter.serialNo, merchantPrivateKey)
                .withValidator(new WechatPay2Validator(verifier));
        HttpClient httpClient=builder.build();
        HttpPost httpPost=new HttpPost("https://api.mch.weixin.qq.com/v3/refund/domestic/refunds");
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Content-type", "application/json; charset=utf-8");
        httpPost.setEntity(new StringEntity(order.toJSONString(), "UTF-8"));

        HttpResponse response=httpClient.execute(httpPost);
        String bodyAsString=EntityUtils.toString(response.getEntity());
        JSONObject bodyAsJSON=JSONObject.parseObject(bodyAsString);

        final String status=bodyAsJSON.getString("status");
        if (status.equals("CLOSED")) {
            throw new Exception("退款关闭!");
        }
        if (status.equals("ABNORMAL")) {
            throw new Exception("退款异常!");
        }
        // TODO 库存回填
        // TODO 更新订单状态(退款)
        // TODO 消息推送
    }


    /**
     * 阿里支付
     *
     * @param payOrderVo
     * @return
     */
    @Override
    @Transactional
    public String aliPay(PayOrderVo payOrderVo) throws AlipayApiException {
        //订单名称，必填
        String subject="农宝牧盒商城订单支付";
        //商品描述，可空
        String body="农宝牧盒商品购买-订单号" + payOrderVo.getOrderNo() + "支付总金额" + payOrderVo.getTotalPrice();
        // TODO 商城订单处理
        ///TODO 创建支付记录

        //获得初始化的AlipayClient
        AlipayClient alipayClient=new DefaultAlipayClient(aliPayConfig.SERVERURL, aliPayConfig.APPID, aliPayConfig.APPPRIVATEKEY, aliPayConfig.FORMAT, aliPayConfig.CHARSET, aliPayConfig.APPPUBLICKEY, aliPayConfig.SIGNTYPE);
        //设置请求参数
        AlipayTradeAppPayRequest alipayRequest=new AlipayTradeAppPayRequest();
        alipayRequest.setNotifyUrl(aliPayConfig.getNotifyUrl());

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no=payOrderVo.getOrderNo();
        //付款金额，必填
        String total_amount=payOrderVo.getTotalPrice().stripTrailingZeros().toPlainString();

        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express="5m";
        JSONObject bizContent=new JSONObject();
        bizContent.put("out_trade_no", out_trade_no);
        bizContent.put("total_amount", total_amount);//total_amount
        bizContent.put("body", body);
        bizContent.put("timeout_express", timeout_express);
        bizContent.put("subject", subject);
        bizContent.put("product_code", "QUICK_MSECURITY_PAY");
        alipayRequest.setBizContent(bizContent.toString());
        //请求
        AlipayTradeAppPayResponse response=alipayClient.sdkExecute(alipayRequest);
        if (response.isSuccess()) {
            return response.getBody();
        } else {
            throw new AlipayApiException("支付宝支付失败！请稍后重试！");
        }
    }

    /**
     * 阿里支付回调
     *
     * @param request
     * @return
     */
    @Override
    @Transactional
    public Object aliPayCallback(HttpServletRequest request) {
        Map<String, String> params=new HashMap<String, String>();
        Map requestParams=request.getParameterMap();
        for (Iterator iter=requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name=(String) iter.next();
            String[] values=(String[]) requestParams.get(name);
            String valueStr="";
            for (int i=0; i < values.length; i++) {
                valueStr=(i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            params.put(name, valueStr);
        }
        try {
            //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
            boolean flag=AlipaySignature.rsaCheckV1(params, aliPayConfig.APPLYPUBLICKEY, aliPayConfig.CHARSET, aliPayConfig.SIGNTYPE);
            if (flag) {
                /**
                 *这里是RSA验证签名
                 *Configs.getAlipayPublicKey() 其实就是支付宝的应用公钥（记住不是支付宝的公钥，是支付宝应用公钥）
                 *Configs.getSignType()也就是支付宝文本类型里面的签名类型：RSA2
                 */
                //boolean alipayRSACheckedV2=AlipaySignature.rsaCheckV2(params, aliPayConfig.APPLYPUBLICKEY, aliPayConfig.CHARSET, aliPayConfig.SIGNTYPE);
                /**
                 * 如果验证上面的boolean为true的话，我们就应该更改下订单的状态，减少下库存这些操作
                 *  签名校验成功，说明是微信服务器发出的数据
                 * 订单号
                 */
                String orderNo=params.get("out_trade_no");
                /**
                 * 如果验证上面的boolean为true的话，我们就应该更改下订单的状态，减少下库存这些操作
                 *  签名校验成功，说明是微信服务器发出的数据
                 * 退款单号
                 */
                String outRequestNo=params.get("out_biz_no");
                if (ObjectUtils.isNotEmpty(outRequestNo)) {//进入退款回调
                    //TODO 获取退款订单信息集合
                    //TODO 批量更新订单状态
                    //TODO 库存回填
                    //TODO 消息推送
                    return "success";
                } else {
                    //TODO 获取订单信息集合
                    if (ObjectUtils.isNotEmpty("")) {//校验
                        return "fail";
                    }
                    /**
                     *更新订单状态更新库存锁
                     */
                    // TODO 库存处理
                    //TODO  订单状态处理(批量)
                    //TODO 消息推送
                    return "success";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
        return "fail";
    }

    /**
     * 支付宝处理退款
     *
     * @param orderId)
     * @throws IOException
     */
    public void alipayRefunds(Long orderId) throws Exception {
        //TODO 获取订单信息对应退款金额
        AlipayClient alipayClient=new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", aliPayConfig.APPID, aliPayConfig.APPPRIVATEKEY, aliPayConfig.FORMAT, aliPayConfig.CHARSET, aliPayConfig.APPLYPUBLICKEY, aliPayConfig.SIGNTYPE);
        AlipayTradeRefundRequest request=new AlipayTradeRefundRequest();
        JSONObject bizContent=new JSONObject();
        bizContent.put("out_trade_no", "");//订单号
//        BigDecimal paymoney=new BigDecimal("0.0");//订单总金额
        BigDecimal paymoney=new BigDecimal(0.01); //测试金额
        // paymoney.setScale(2, BigDecimal.ROUND_DOWN) //正式金额
        bizContent.put("refund_amount", paymoney);
        //生成退款单号
        SnowFlakeIdUtil idWorker=new SnowFlakeIdUtil(0L, 0);
        Long TOrderNoLong=idWorker.nextId();
        String tOrderNo="ARF" + TOrderNoLong.toString();
        bizContent.put("out_request_no", tOrderNo);
        request.setBizContent(bizContent.toString());
        //TODO  更新标识该订单为申请退款单 更新退款(参数:tOrderNo)
        AlipayTradeRefundResponse response=alipayClient.execute(request);
        if (!response.isSuccess()) {
            throw new Exception("退款处理失败！请联系平台管理员！");
        }
    }

}
