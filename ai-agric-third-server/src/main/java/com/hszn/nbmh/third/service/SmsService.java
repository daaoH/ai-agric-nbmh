package com.hszn.nbmh.third.service;

import com.hszn.nbmh.third.entity.SmsResponseEntity;
import com.hszn.nbmh.third.entity.SmsValidateEntity;

/**
 * <p>
 * 阿里云--短信服务 接口类
 * </p>
 *
 * @author MCR
 * @since 2022-08-19
 */
public interface SmsService {

    SmsResponseEntity sendSms(String phoneNumber);

    SmsValidateEntity validateCode(String phoneNumber, String code);

}
