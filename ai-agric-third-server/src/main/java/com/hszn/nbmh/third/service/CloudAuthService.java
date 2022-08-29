package com.hszn.nbmh.third.service;

import com.aliyun.cloudauth20200618.models.DescribeSmartVerifyResponse;
import com.aliyun.cloudauth20200618.models.InitSmartVerifyResponse;

/**
 * <p>
 * 增强版实人认证 接口层
 * </p>
 *
 * @author MCR
 * @since 2022-08-17
 */
public interface CloudAuthService {

    InitSmartVerifyResponse initSmartVerify(String metaInfo, String certName, String certNo, String mobile);

    DescribeSmartVerifyResponse describeSmartVerify(String certifyId);

}
