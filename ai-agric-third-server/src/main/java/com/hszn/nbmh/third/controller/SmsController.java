package com.hszn.nbmh.third.controller;

import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.third.entity.SmsResponseEntity;
import com.hszn.nbmh.third.entity.SmsValidateEntity;
import com.hszn.nbmh.third.service.SmsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * 阿里云--短信服务 业务控制器
 * </p>
 *
 * @author MCR
 * @since 2022-08-19
 */
@Tag(name = "阿里云--短信服务")
@Validated
@RestController
@RequestMapping("/aliyun-sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @Operation(summary = "发送短信", method = "POST")
    @Parameters({@Parameter(description = "用户手机号码", name = "phoneNumber")})
    @PostMapping("/sendSms")
    @Inner(false)
    public Result sendSms(@RequestParam @NotBlank(message = "手机号码不能为空")
                          @Pattern(regexp = "^1[345678]\\d{9}$", message = "手机号码格式有误") String phoneNumber) {

        SmsResponseEntity response = smsService.sendSms(phoneNumber);
        if (response != null) {
            return Result.ok(response);
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "短信验证码验证", method = "POST")
    @Parameters({@Parameter(description = "用户手机号码", name = "phoneNumber"), @Parameter(description = "验证码", name = "code")})
    @PostMapping("/validateCode")
    @Inner(false)
    public Result validateCode(@RequestParam @NotBlank(message = "手机号不能为空")
                               @Pattern(regexp = "^1[345678]\\d{9}$", message = "手机号码格式有误") String phoneNumber,
                               @RequestParam @NotBlank(message = "验证码不能为空") String code) {

        SmsValidateEntity response = smsService.validateCode(phoneNumber, code);
        if (response != null) {
            return Result.ok(response);
        }

        return Result.failed(CommonEnum.SMS_VALIDATE_FAIL.getMsg());
    }

}
