package com.hszn.nbmh.third.service;

import com.hszn.nbmh.third.entity.SmsResponseEntity;
import com.hszn.nbmh.third.entity.SmsValidateEntity;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 阿里云--短信服务 接口类
 * </p>
 *
 * @author MCR
 * @since 2022-08-19
 */
@Validated
public interface SmsService {

    SmsResponseEntity sendSms(@NotBlank String phoneNumber);

    SmsValidateEntity validateCode(@NotBlank String phoneNumber, @NotBlank String code);

}
