package com.hszn.nbmh.pay.configure;


import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 静态参数类
 */


public class StaticParameter {
    /**
     * 微信商户号
     */
    public static String wechatMchId;

    /**
     * 商户在微信公众平台申请服务号对应的APPID
     */
    public static String wechatAppId;

    /**
     * 商户平台设置的密钥key
     */
    public static String wechatKey;

    /**
     * 回调报文解密V3密钥key
     */
    public static String wechatV3Key;

    /**
     * 微信获取平台证书列表地址
     */
    public static String wechatCertificatesUrl;

    /**
     * 微信APP下单URL
     */
    public static String wechatUnifiedOrderUrl;

    /**
     * 微信APP查询订单URL
     */
    public static String wechatQueryOrderUrl;

    /**
     * 异步接收微信支付结果通知的回调地址-小程序
     */
    public static String wechatJsNotifyUrl;

    /**
     * 异步接收微信支付结果通知的回调地址-App
     */
    public static String wechatAppNotifyUrl;

    /**
     * 微信证书私钥
     */
    public static PrivateKey privateKey;

    /**
     * 微信API密钥路径
     */
    public static String wechatKeyPath;

    /**
     * 微信证书序列号
     */
    public static String serialNo;

    // 定义全局容器 保存微信平台证书公钥  注意线程安全
    public static Map<String, X509Certificate> certificateMap=new ConcurrentHashMap<>();

}
