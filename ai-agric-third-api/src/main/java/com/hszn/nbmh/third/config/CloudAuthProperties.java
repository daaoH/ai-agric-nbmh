package com.hszn.nbmh.third.config;

import lombok.Data;

/**
 * <p>
 * 阿里云--增强版实人认证 配置类
 * </p>
 *
 * @author MCR
 * @since 2022-08-17
 */
@Data
public class CloudAuthProperties {

    /**
     * 商户请求的唯一标识
     */
    private String outerOrderNo;

    /**
     * 是否需要证件OCR
     */
    private String ocr;

    /**
     * 证件类型，固定值
     */
    private String certType;

    /**
     * 认证模式
     */
    private String mode;

    /**
     * 场景Id
     */
    private Long sceneId;

}
