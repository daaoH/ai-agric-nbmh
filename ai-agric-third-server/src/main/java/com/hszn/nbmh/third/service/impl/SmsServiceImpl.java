package com.hszn.nbmh.third.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.third.config.ALiYunProperties;
import com.hszn.nbmh.third.entity.SmsResponseEntity;
import com.hszn.nbmh.third.entity.SmsValidateEntity;
import com.hszn.nbmh.third.service.SmsService;
import com.hszn.nbmh.third.utils.GeneratorCodeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 阿里云--短信服务 接口实现类
 * </p>
 *
 * @author MCR
 * @since 2022-08-19
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SmsServiceImpl implements SmsService {

    @Autowired
    private ALiYunProperties aLiYunProperties;

    @Resource
    private RedisTemplate  redisTemplate;

    @Override
    public SmsResponseEntity sendSms(String phoneNumber) {

        String code = GeneratorCodeUtil.createRandom(phoneNumber);
        String codeKey = buildKey(phoneNumber);

        Map<String, String> map = new HashMap<>();
        map.put("code", code);

        com.aliyun.dysmsapi20170525.Client client = createClient(aLiYunProperties.getAccessKey(), aLiYunProperties.getSecret());
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setTemplateCode(aLiYunProperties.getSms().getTemplateCode())
                .setTemplateParam(JSON.toJSONString(map))
                .setSignName(aLiYunProperties.getSms().getSignName())
                .setPhoneNumbers(phoneNumber);
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            SendSmsResponse response = client.sendSmsWithOptions(sendSmsRequest, runtime);
            log.info("");
            if (response != null && response.getBody().getCode().equals("OK")) {
                redisTemplate.opsForValue().set(codeKey, code, aLiYunProperties.getSms().getTimeout(), TimeUnit.MINUTES);
                return SmsResponseEntity.builder().sendResult(true).msg("发送成功").build();
            }
        } catch (TeaException error) {
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        }

        return SmsResponseEntity.builder().sendResult(false).msg("发送失败").build();
    }

    @Override
    public SmsValidateEntity validateCode(String phoneNumber, String code) {
        String redisSmsCode = (String) redisTemplate.opsForValue().get(buildKey(phoneNumber));

        if (code.equals(redisSmsCode)) {
            return SmsValidateEntity.builder().validateResult(true).msg(CommonEnum.SUCCESS_RESPONSE.getMsg()).build();
        }

        return SmsValidateEntity.builder().validateResult(false).msg(CommonEnum.SMS_VALIDATE_FAIL.getMsg()).build();
    }

    /**
     * 使用AK&SK初始化账号Client
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) {
        Config config = new Config()
                // 您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        try {
            return new com.aliyun.dysmsapi20170525.Client(config);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String buildKey(String phoneNumber) {
        return "phone:sms:" + phoneNumber;
    }

}
