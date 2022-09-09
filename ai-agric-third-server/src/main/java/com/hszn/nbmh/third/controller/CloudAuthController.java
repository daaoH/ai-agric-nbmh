package com.hszn.nbmh.third.controller;

import com.alibaba.fastjson.JSON;
import com.aliyun.cloudauth20200618.models.DescribeSmartVerifyResponse;
import com.aliyun.cloudauth20200618.models.InitSmartVerifyResponse;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.third.entity.MetaInfo;
import com.hszn.nbmh.third.enums.ALiyunEnum;
import com.hszn.nbmh.third.service.CloudAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * 增强版实人认证 业务控制器
 * </p>
 *
 * @author MCR
 * @since 2022-08-17
 */
@Tag(name = "增强版实人认证")
@Validated
@RestController
@RequestMapping("/cloud-auth")
public class CloudAuthController {

    @Autowired
    private CloudAuthService cloudAuthService;


    @Inner(false)
    @Operation(summary = "初始化认证,获取CertifyId", method = "POST")
    @Parameters({@Parameter(description = "用户手机号码", name = "mobile")})
    @PostMapping("/initSmartVerify")
    public Result initSmartVerify(@RequestBody MetaInfo metaInfo,
                                  @RequestParam @NotBlank(message = "用户手机号码不能为空") @Pattern(regexp = "^1[345678]\\d{9}$", message = "手机号码格式有误") String mobile) {

        InitSmartVerifyResponse response = cloudAuthService.initSmartVerify(JSON.toJSONString(metaInfo), "", "", mobile);
        if (response != null && response.getCode().equals("200")) {
            return Result.ok(response.getResultObject().getCertifyId());
        }

        return Result.failed(ALiyunEnum.CERTIFY_FAIL.getMsg());
    }

    @Inner(false)
    @Operation(summary = "查询认证结果", method = "POST")
    @Parameters({@Parameter(description = "certifyId", name = "certifyId")})
    @PostMapping("/describeSmartVerify")
    public Result describeSmartVerify(@RequestParam @NotBlank(message = "certifyId不能为空") String certifyId) {

        DescribeSmartVerifyResponse response = cloudAuthService.describeSmartVerify(certifyId);
        if (response != null && response.getCode().equals("200")) {
            return Result.ok(response);
        }

        return Result.failed(ALiyunEnum.AUTH_FAIL.getMsg());
    }

}
