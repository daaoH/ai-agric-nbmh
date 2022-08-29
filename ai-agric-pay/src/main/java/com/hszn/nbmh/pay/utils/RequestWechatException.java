package com.hszn.nbmh.pay.utils;


/**
 * 请求微信异常,只有在请求微信地址不通时才会抛出该异常
 *
 * @author shenlu
 * @date 2020/12/29 9:37
 */
public class RequestWechatException extends Exception {

    public RequestWechatException() {
        super("请求微信异常");
    }
}