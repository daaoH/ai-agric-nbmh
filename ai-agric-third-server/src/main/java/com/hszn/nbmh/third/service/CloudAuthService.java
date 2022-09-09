package com.hszn.nbmh.third.service;

import com.aliyun.cloudauth20200618.models.DescribeSmartVerifyResponse;
import com.aliyun.cloudauth20200618.models.InitSmartVerifyResponse;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 增强版实人认证 接口层
 * </p>
 *
 * @author MCR
 * @since 2022-08-17
 */
@Validated
public interface CloudAuthService {

    InitSmartVerifyResponse initSmartVerify(String metaInfo, String certName, String certNo, @NotBlank String mobile);

    DescribeSmartVerifyResponse describeSmartVerify(@NotBlank String certifyId);

}
