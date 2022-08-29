package com.hszn.nbmh.third.service.impl;

import com.aliyun.cloudauth20200618.Client;
import com.aliyun.cloudauth20200618.models.DescribeSmartVerifyRequest;
import com.aliyun.cloudauth20200618.models.DescribeSmartVerifyResponse;
import com.aliyun.cloudauth20200618.models.InitSmartVerifyRequest;
import com.aliyun.cloudauth20200618.models.InitSmartVerifyResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.tea.TeaUnretryableException;
import com.aliyun.tearpc.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.hszn.nbmh.third.config.ALiYunProperties;
import com.hszn.nbmh.third.service.CloudAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 增强版实人认证 接口实现类
 * </p>
 *
 * @author MCR
 * @since 2022-08-17
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CloudAuthServiceImpl implements CloudAuthService {

    @Autowired
    private ALiYunProperties aLiYunProperties;

    @Override
    public InitSmartVerifyResponse initSmartVerify(String metaInfo, String certName, String certNo, String mobile) {

        // 通过以下代码创建API请求并设置参数。
        InitSmartVerifyRequest request = new InitSmartVerifyRequest();
        // 请输入场景ID+L。
        request.setSceneId(aLiYunProperties.getCloudAuth().getSceneId());
        request.setOuterOrderNo(aLiYunProperties.getCloudAuth().getOuterOrderNo());
        // 卡证核身类型，固定值。
        request.setMode(aLiYunProperties.getCloudAuth().getMode());
        request.setOcr(aLiYunProperties.getCloudAuth().getOcr());
        // 证件类型，固定值。
        request.setCertType(aLiYunProperties.getCloudAuth().getCertType());
        //request.setCertName("张三");
        //request.setCertNo("xxxx");
        // MetaInfo环境参数，需要通过客户端SDK获取。
        request.setMetaInfo(metaInfo);
        request.setMobile(mobile);
        //request.setIp("114.xxx.xxx.xxx");
        //request.setUserId("12345xxxx");
        //request.setCallbackUrl("https://www.aliyundoc.com");
        //request.setCallbackToken("xxxxxxx");

        // 推荐，支持服务路由。
        InitSmartVerifyResponse response = initSmartVerifyAutoRoute(aLiYunProperties.getAccessKey(), aLiYunProperties.getSecret(), request);

        // 不支持服务自动路由。
        //InitSmartVerifyResponse response = initSmartVerify("cloudauth.cn-shanghai.aliyuncs.com", request);

        System.out.println(response.getRequestId());
        System.out.println(response.getCode());
        System.out.println(response.getMessage());
        System.out.println(response.getResultObject() == null ? null : response.getResultObject().getCertifyId());

        return response;
    }

    @Override
    public DescribeSmartVerifyResponse describeSmartVerify(String certifyId) {
        // 通过以下代码创建API请求并设置参数。
        DescribeSmartVerifyRequest request = new DescribeSmartVerifyRequest();
        // 请输入场景ID+L。
        request.setSceneId(aLiYunProperties.getCloudAuth().getSceneId());

        request.setCertifyId(certifyId);

        // 推荐，支持服务路由。
        DescribeSmartVerifyResponse response = describeSmartVerifyAutoRoute(aLiYunProperties.getAccessKey(), aLiYunProperties.getSecret(), request);

        // 不支持服务自动路由。
//        DescribeSmartVerifyResponse response = describeSmartVerify("cloudauth.cn-shanghai.aliyuncs.com", request);

        System.out.println(response.getRequestId());
        System.out.println(response.getCode());
        System.out.println(response.getMessage());
        System.out.println(response.getResultObject() == null ? null : response.getResultObject().getPassed());
        System.out.println(response.getResultObject() == null ? null : response.getResultObject().getSubCode());
        System.out.println(response.getResultObject() == null ? null : response.getResultObject().getMaterialInfo());

        return response;
    }

    private static InitSmartVerifyResponse initSmartVerifyAutoRoute(String accessKey, String secret, InitSmartVerifyRequest request) {
        // 第一个为主区域Endpoint，第二个为备区域Endpoint。
        List<String> endpoints = Arrays.asList("cloudauth.cn-shanghai.aliyuncs.com", "cloudauth.cn-beijing.aliyuncs.com");
        InitSmartVerifyResponse lastResponse = null;
        for (String endpoint : endpoints) {
            try {
                InitSmartVerifyResponse response = initSmartVerify(accessKey, secret, endpoint, request);
                lastResponse = response;

                // 服务端错误，切换到下个区域调用。
                if (response != null && "500".equals(response.getCode())) {
                    continue;
                }

                return response;
            } catch (Exception e) {
                // 网络异常，切换到下个区域调用。
                if (e.getCause() instanceof TeaException) {
                    TeaException teaException = ((TeaException) e.getCause());
                    if (teaException.getData() != null && "ServiceUnavailable".equals(
                            teaException.getData().get("Code"))) {
                        continue;
                    }
                }

                if (e.getCause() instanceof TeaUnretryableException) {
                    continue;
                }
            }
        }

        return lastResponse;
    }

    private static InitSmartVerifyResponse initSmartVerify(String accessKey, String secret, String endpoint, InitSmartVerifyRequest request) throws Exception {
        Config config = new Config();
        config.setAccessKeyId(accessKey);
        config.setAccessKeySecret(secret);
        config.setEndpoint(endpoint);
        // 设置http代理。
        //config.setHttpProxy("http://xx.xx.xx.xx:xxxx");
        // 设置https代理。
        //config.setHttpsProxy("https://xx.xx.xx.xx:xxxx");
        Client client = new Client(config);

        // 创建RuntimeObject实例并设置运行参数。
        RuntimeOptions runtime = new RuntimeOptions();
        runtime.readTimeout = 10000;
        runtime.connectTimeout = 10000;

        return client.initSmartVerify(request, runtime);
    }

    private static DescribeSmartVerifyResponse describeSmartVerifyAutoRoute(String accessKey, String secret, DescribeSmartVerifyRequest request) {
        // 第一个为主区域Endpoint，第二个为备区域Endpoint。
        List<String> endpoints = Arrays.asList("cloudauth.cn-shanghai.aliyuncs.com", "cloudauth.cn-beijing.aliyuncs.com");
        DescribeSmartVerifyResponse lastResponse = null;
        for (String endpoint : endpoints) {
            try {
                DescribeSmartVerifyResponse response = describeSmartVerify(accessKey, secret, endpoint, request);
                lastResponse = response;

                // 服务端错误，切换到下个区域调用。
                if (response != null && "500".equals(response.getCode())) {
                    continue;
                }

                return response;
            } catch (Exception e) {
                // 网络异常，切换到下个区域调用。
                if (e.getCause() instanceof TeaException) {
                    TeaException teaException = ((TeaException) e.getCause());
                    if (teaException.getData() != null && "ServiceUnavailable".equals(
                            teaException.getData().get("Code"))) {
                        continue;
                    }
                }

                if (e.getCause() instanceof TeaUnretryableException) {
                    continue;
                }
            }
        }

        return lastResponse;
    }

    private static DescribeSmartVerifyResponse describeSmartVerify(String accessKey, String secret, String endpoint, DescribeSmartVerifyRequest request) throws Exception {
        Config config = new Config();
        config.setAccessKeyId(accessKey);
        config.setAccessKeySecret(secret);
        config.setEndpoint(endpoint);
        // 设置http代理。
        //config.setHttpProxy("http://xx.xx.xx.xx:xxxx");
        // 设置https代理。
        //config.setHttpsProxy("https://xx.xx.xx.xx:xxxx");
        Client client = new Client(config);

        // 创建RuntimeObject实例并设置运行参数。
        RuntimeOptions runtime = new RuntimeOptions();
        runtime.readTimeout = 10000;
        runtime.connectTimeout = 10000;

        return client.describeSmartVerify(request, runtime);
    }

}
