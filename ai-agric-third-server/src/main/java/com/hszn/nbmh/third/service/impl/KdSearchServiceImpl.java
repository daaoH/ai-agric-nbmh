package com.hszn.nbmh.third.service.impl;

import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSON;
import com.hszn.nbmh.common.core.exception.ServiceException;
import com.hszn.nbmh.third.config.KdSearchProperties;
import com.hszn.nbmh.third.entity.KdSearchEntity;
import com.hszn.nbmh.third.service.KdSearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 快递鸟--快递查询服务 接口实现类
 * </p>
 *
 * @author MCR
 * @since 2022-08-26
 */
@Service
public class KdSearchServiceImpl implements KdSearchService {

    @Autowired
    private KdSearchProperties kdSearchProperties;


    @Override
    public KdSearchEntity searchKdInfo(String shipperCode, String logisticCode, String mobile) {

        String customerName = "";
        if (shipperCode.equals("SF")) {
            if (StringUtils.isBlank(mobile)) {
                throw new ServiceException("寄件人手机号码必填");
            }

            if (mobile.length() != 11) {
                throw new ServiceException("寄件人手机号码位数不正确");
            }

            customerName = mobile.substring(7);

        }
        // 组装应用级参数
        String RequestData = "{" +
                "'CustomerName': '" + customerName + "'," +
                "'OrderCode': ''," +
                "'ShipperCode': '" + shipperCode + "'," +
                "'LogisticCode': '" + logisticCode + "'," +
                "}";

        // 组装系统级参数
        Map<String, String> params = new HashMap<String, String>();
        try {
            params.put("RequestData", urlEncoder(RequestData, "UTF-8"));
            params.put("EBusinessID", kdSearchProperties.getEBusinessId());
            //免费即时查询接口指令1002/在途监控即时查询接口指令8001/地图版即时查询接口指令8003
            params.put("RequestType", kdSearchProperties.getRequestType());
            String dataSign = encrypt(RequestData, kdSearchProperties.getApiKey(), "UTF-8");
            params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
            params.put("DataType", "2");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 以form表单形式提交post请求，post请求体中包含了应用级参数和系统级参数
        String result = sendPost(kdSearchProperties.getReqUrl(), params);
        if (StringUtils.isBlank(result)) {
            throw new ServiceException("获取快递物流信息失败");
        }

        return JSON.parseObject(result, KdSearchEntity.class);
    }

    /**
     * MD5加密
     * str 内容
     * charset 编码方式
     *
     * @throws Exception
     */
    private String MD5(String str, String charset) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes(charset));
        byte[] result = md.digest();
        StringBuffer sb = new StringBuffer(32);
        for (int i = 0; i < result.length; i++) {
            int val = result[i] & 0xff;
            if (val <= 0xf) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString().toLowerCase();
    }

    /**
     * base64编码
     * str 内容
     * charset 编码方式
     *
     * @throws UnsupportedEncodingException
     */
    private String base64(String str, String charset) throws UnsupportedEncodingException {
        String encoded = Base64.encode(str.getBytes(charset));
        return encoded;
    }

    private String urlEncoder(String str, String charset) throws UnsupportedEncodingException {
        String result = URLEncoder.encode(str, charset);
        return result;
    }

    /**
     * 电商Sign签名生成
     * content 内容
     * keyValue ApiKey
     * charset 编码方式
     *
     * @return DataSign签名
     * @throws UnsupportedEncodingException ,Exception
     */
    private String encrypt(String content, String keyValue, String charset) throws Exception {
        if (keyValue != null) {
            return base64(MD5(content + keyValue, charset), charset);
        }
        return base64(MD5(content, charset), charset);
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * url 发送请求的 URL
     * params 请求的参数集合
     *
     * @return 远程资源的响应结果
     */
    private String sendPost(String url, Map<String, String> params) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // POST方法
            conn.setRequestMethod("POST");
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8);
            // 发送请求参数
            if (params != null) {
                StringBuilder param = new StringBuilder();
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    if (param.length() > 0) {
                        param.append("&");
                    }
                    param.append(entry.getKey());
                    param.append("=");
                    param.append(entry.getValue());
                    System.out.println(entry.getKey() + ":" + entry.getValue());
                }
                System.out.println("param:" + param.toString());
                out.write(param.toString());
            }
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();
    }
}
