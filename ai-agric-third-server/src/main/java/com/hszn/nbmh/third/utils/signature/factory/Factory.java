package com.hszn.nbmh.third.utils.signature.factory;

/**
 * @author 澄泓
 * @version JDK1.7
 * @description 基础信息初始化类
 * @date 2020/10/23 10:18
 */
public class Factory {
    // 请求域名
    private static String host;
    // 项目Id(应用Id）
    private static String appId;
    // 项目密钥(应用密钥）
    private static String appSecret;

    private static boolean debug = false;

    private Factory() {
    }

    public static void init(String host, String appId, String appSecret) {
        Factory.setHost(host);
        Factory.setAppId(appId);
        Factory.setAppSecret(appSecret);
    }

    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        Factory.host = host;
    }

    public static String getAppId() {
        return appId;
    }

    public static void setAppId(String appId) {
        Factory.appId = appId;
    }

    public static String getAppSecret() {
        return appSecret;
    }

    public static void setAppSecret(String appSecret) {
        Factory.appSecret = appSecret;
    }

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean debug) {
        Factory.debug = debug;
    }
}
