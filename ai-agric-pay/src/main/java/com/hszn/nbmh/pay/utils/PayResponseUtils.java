package com.hszn.nbmh.pay.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hszn.nbmh.pay.configure.StaticParameter;
import com.hszn.nbmh.pay.vo.CertificateVO;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 微信付款返回数据工具类
 *
 * @author shenlu
 * @date 2020/12/22 13:46
 */
public class PayResponseUtils {

    /**
     * 用微信V3密钥解密响应体.
     *
     * @param associatedData response.body.data[i].encrypt_certificate.associated_data
     * @param nonce          response.body.data[i].encrypt_certificate.nonce
     * @param ciphertext     response.body.data[i].encrypt_certificate.ciphertext
     * @return the string
     * @throws GeneralSecurityException the general security exception
     */
    public static String decryptResponseBody(String associatedData, String nonce, String ciphertext) {
        try {
            Cipher cipher=Cipher.getInstance("AES/GCM/NoPadding");

            SecretKeySpec key=new SecretKeySpec(StaticParameter.wechatV3Key.getBytes(StandardCharsets.UTF_8), "AES");
            GCMParameterSpec spec=new GCMParameterSpec(128, nonce.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.DECRYPT_MODE, key, spec);
            cipher.updateAAD(associatedData.getBytes(StandardCharsets.UTF_8));

            byte[] bytes;
            try {
                bytes=cipher.doFinal(Base64Utils.decodeFromString(ciphertext));
            } catch (GeneralSecurityException e) {
                throw new IllegalArgumentException(e);
            }
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new IllegalStateException(e);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 获取平台证书Map
     *
     * @return
     * @throws ParseException
     * @throws IllegalAccessException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws InstantiationException
     * @throws SignatureException
     * @throws InvalidKeyException
     * @throws CertificateException
     */
    public static Map<String, X509Certificate> refreshCertificate() throws ParseException, CertificateException, RequestWechatException {
        //获取平台证书json
        JSONObject jsonObject=PayRequestUtils.wechatHttpGet(StaticParameter.wechatCertificatesUrl, "", JSONObject.class);
        List<CertificateVO> certificateList=JSON.parseArray(jsonObject.getString("data"), CertificateVO.class);
        //最新证书响应实体类
        CertificateVO newestCertificate=null;
        //最新时间
        Date newestTime=null;
        for (CertificateVO certificate : certificateList) {
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            //如果最新时间是null
            if (newestTime == null) {
                newestCertificate=certificate;
                //设置最新启用时间
                newestTime=formatter.parse(certificate.getEffective_time());
            } else {
                Date effectiveTime=formatter.parse(certificate.getEffective_time());
                //如果启用时间大于最新时间
                if (effectiveTime.getTime() > newestTime.getTime()) {
                    //更换最新证书响应实体类
                    newestCertificate=certificate;
                }
            }
        }

        CertificateVO.EncryptCertificate encryptCertificate=newestCertificate.getEncrypt_certificate();
        //获取证书字公钥
        String publicKey=decryptResponseBody(encryptCertificate.getAssociated_data(), encryptCertificate.getNonce(), encryptCertificate.getCiphertext());
        CertificateFactory cf=CertificateFactory.getInstance("X509");

        //获取证书
        ByteArrayInputStream inputStream=new ByteArrayInputStream(publicKey.getBytes(StandardCharsets.UTF_8));
        X509Certificate certificate=null;
        try {
            certificate=(X509Certificate) cf.generateCertificate(inputStream);
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        //保存微信平台证书公钥
        Map<String, X509Certificate> certificateMap=new ConcurrentHashMap<>();
        // 清理HashMap
        certificateMap.clear();
        // 放入证书
        certificateMap.put(newestCertificate.getSerial_no(), certificate);
        return certificateMap;
    }
}





