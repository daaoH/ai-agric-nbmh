package com.hszn.nbmh.pay.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hszn.nbmh.pay.configure.StaticParameter;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.Base64Utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//import com.bhome.broad_training.common.StaticParameter;
//import com.bhome.broad_training.exception.RequestWechatException;

/**
 * 微信付款请求工具类
 * @author shenlu
 * @date 2020/12/16 16:43
 */
public class PayRequestUtils {

    /**
     * V3  SHA256withRSA 移动端请求签名.
     * @param timestamp 当前时间戳
     * @param nonceStr 随机字符串
     * @param prepayId 统一下单接口返回的prepay_id参数值，提交格式如：prepay_id=***
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws SignatureException
     * @throws MalformedURLException
     */
    public static String appSign(long timestamp, String nonceStr, String prepayId) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, MalformedURLException {
        String signatureStr = Stream.of(StaticParameter.wechatAppId, String.valueOf(timestamp), nonceStr, prepayId)
                .collect(Collectors.joining("\n", "", "\n"));
        return getSign(signatureStr);
    }

    /**
     * V3  SHA256withRSA http请求签名.
     *
     * @param method       请求方法  GET  POST PUT DELETE 等
     * @param canonicalUrl 请求地址
     * @param timestamp    当前时间戳   因为要配置到TOKEN 中所以 签名中的要跟TOKEN 保持一致
     * @param nonceStr     随机字符串  要和TOKEN中的保持一致
     * @param body         请求体 GET 为 "" POST 为JSON
     * @return the string
     */
    private static String httpSign(String method, String canonicalUrl, String body, long timestamp, String nonceStr) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, MalformedURLException {
        URL url = new URL(canonicalUrl);
        String signUrl;
        if ("GET".equals(method)&&url.getQuery()!=null) {
            signUrl = url.getPath() + "?" + url.getQuery();
        }else{
            signUrl = url.getPath();
        }
        String signatureStr = Stream.of(method, signUrl, String.valueOf(timestamp), nonceStr, body)
                .collect(Collectors.joining("\n", "", "\n"));
        return getSign(signatureStr);
    }

    /**
     * 获取签名
     * @param signatureStr 签名字符串
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws SignatureException
     */
    public static String getSign(String signatureStr) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(StaticParameter.privateKey);
        sign.update(signatureStr.getBytes(StandardCharsets.UTF_8));
        return Base64Utils.encodeToString(sign.sign());
    }

    /**
     * 生成Token http请求
     *
     * @param method       请求方法  GET  POST PUT DELETE 等
     * @param canonicalUrl 请求地址
     * @param body         请求体 GET 为 "" POST 为JSON
     * @return the string
     */
    private static String httpToken(String method, String canonicalUrl, String body) throws MalformedURLException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Long timestamp = System.currentTimeMillis()/1000;
        String nonceStr = UUID.randomUUID().toString().replace("-","");
        String signature = httpSign(method,canonicalUrl,body,timestamp,nonceStr);
        final String TOKEN_PATTERN = "WECHATPAY2-SHA256-RSA2048 mchid=\"%s\",nonce_str=\"%s\",timestamp=\"%d\",serial_no=\"%s\",signature=\"%s\"";
        // 生成token
        return String.format(TOKEN_PATTERN,
                StaticParameter.wechatMchId,
                nonceStr, timestamp, StaticParameter.serialNo, signature);
    }

    public static<T> T wechatHttpPost(String url, String jsonStr, Class<T> t) throws RequestWechatException {
        //创建httpClient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        T instance = null;
        try {
            instance = t.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //生成Token http请求
        String token = null;
        try {
            token = httpToken("POST",url,jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpPost httppost = new HttpPost(url);
        httppost.addHeader("Content-Type", "application/json;charset=UTF-8");
        httppost.addHeader("Accept", "application/json");
        httppost.addHeader("Authorization", token);
        //设置连接超时时间和数据获取超时时间--单位：ms
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000000).setConnectionRequestTimeout(5000000)
                .setSocketTimeout(5000000).build();
        httppost.setConfig(requestConfig);
        //设置http request body请求体
        if (null != jsonStr) {
            //解决中文乱码问题
            StringEntity myEntity = new StringEntity(jsonStr, "UTF-8");
            httppost.setEntity(myEntity);
        }
        HttpResponse response = null;
        try {
            response = httpClient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RequestWechatException();
        }
        String result= null;
        try {
            result = EntityUtils.toString(response.getEntity(),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //得到返回的字符串
        JSONObject jsonObject = JSON.parseObject(result);
        if(instance instanceof JSONObject) {
            return (T) jsonObject;
        }

        T resultBean = (T) JSONObject.parseObject(jsonObject.toString(), t);
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultBean;
    }


    /**
     * httpget调用http接口
     * @param url
     * @param jsonStr
     * @param t
     * @param <T>
     * @return
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static<T> T wechatHttpGet(String url, String jsonStr, Class<T> t) throws RequestWechatException {
        String result = "";
        T instance = null;
        try {
            instance = t.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //生成Token http请求
        String token = null;
        try {
            token = httpToken("GET",url,jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //创建httpClient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建实例方法
        HttpGet httpget = new HttpGet(url);
        httpget.addHeader("Content-Type", "application/json;charset=UTF-8");
        httpget.addHeader("Accept", "application/json");
        httpget.addHeader("Authorization", token);
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpget);
        } catch (IOException e) {
            throw new RequestWechatException();
        }

        //如果状态码为200,就是正常返回
        if(response.getStatusLine().getStatusCode()==200){
            try {
                result= EntityUtils.toString(response.getEntity(),"UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        JSONObject jsonObject =JSON.parseObject(result);
        if(instance instanceof JSONObject) {
            return (T) jsonObject;
        }

        T resultBean = (T) JSONObject.parseObject(jsonObject.toString(), t);
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultBean;
    }

    /**
     * 获取证书。
     *
     * @param fis 证书文件流
     * @return X509证书
     */
    public static X509Certificate getCertificate(InputStream fis) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(fis);
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X509");
            X509Certificate cert = (X509Certificate) cf.generateCertificate(bis);
            cert.checkValidity();
            return cert;
        } catch (CertificateExpiredException e) {
            throw new RuntimeException("证书已过期", e);
        } catch (CertificateNotYetValidException e) {
            throw new RuntimeException("证书尚未生效", e);
        } catch (CertificateException e) {
            throw new RuntimeException("无效的证书文件", e);
        } finally {
            bis.close();
        }
    }

    /**
     * 获取私钥。
     *
     * @param filename 私钥文件路径  (required)
     * @return 私钥对象
     */
    public static PrivateKey getPrivateKey(String filename) throws IOException {

        String content = new String(Files.readAllBytes(Paths.get(filename)), "utf-8");
        try {
            String privateKey = content.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s+", "");

            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(
                    new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("当前Java环境不支持RSA", e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("无效的密钥格式");
        }
    }
}