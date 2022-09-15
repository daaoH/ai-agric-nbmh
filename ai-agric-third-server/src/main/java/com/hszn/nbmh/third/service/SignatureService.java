package com.hszn.nbmh.third.service;

import com.hszn.nbmh.third.entity.NbmhSignatureInfo;
import com.hszn.nbmh.third.utils.signature.bean.CallBackBean;
import com.hszn.nbmh.third.utils.signature.exception.DefineException;
import com.hszn.nbmh.third.utils.signature.factory.account.CreatePersonByThirdPartyUserId;

/**
 * 签名服务
 *
 * @author liwei
 * @version 1.0
 * @since 2022-09-08 14:42
 */
public interface SignatureService {

    /**
     * 创建用户信息
     *
     * @return
     */
    void signatureStart() throws DefineException;

    String createFlowOneStep(String fileId, String accountId) throws DefineException;


    String getFileId();

    void callbackHandler(CallBackBean bean) throws DefineException;

    NbmhSignatureInfo currentInfo();
}
