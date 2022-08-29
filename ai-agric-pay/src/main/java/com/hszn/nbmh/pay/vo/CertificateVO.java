package com.hszn.nbmh.pay.vo;


import lombok.Data;

/**
 * 微信平台证书VO
 *
 * @author shenlu
 * @date 2020/12/21 16:14
 */
@Data
public class CertificateVO {

    /**
     * 平台证书序列号
     */
    private String serial_no;

    /**
     * 平台证书有效时间
     */
    private String effective_time;

    /**
     * 平台证书过期时间
     */
    private String expire_time;

    /**
     * 加密证书
     */
    private EncryptCertificate encrypt_certificate;

    /**
     * 加密证书
     */
    @Data
    public class EncryptCertificate {

        /**
         * 算法
         */
        private String algorithm;

        /**
         * 随机字符串
         */
        private String nonce;

        /**
         * 相关数据
         */
        private String associated_data;

        /**
         * 密文
         */
        private String ciphertext;
    }
}
