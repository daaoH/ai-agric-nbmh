package com.hszn.nbmh.third.utils.signature.factory.response.data;


import com.hszn.nbmh.third.utils.signature.factory.response.other.PdfSignInfos;

/**
 * @description  轩辕API
 * @author  澄泓
 * @date  2020/10/30 10:41
 * @version 
 */
public class PdfVerifyData {
    private PdfSignInfos signInfos;

    public PdfSignInfos getSignInfos() {
        return signInfos;
    }

    public void setSignInfos(PdfSignInfos signInfos) {
        this.signInfos = signInfos;
    }
}
