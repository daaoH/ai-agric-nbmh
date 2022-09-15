package com.hszn.nbmh.third.utils.signature.factory.response;


import com.hszn.nbmh.third.utils.signature.factory.response.data.PdfVerifyData;

/**
 * @author 澄泓
 * @description 轩辕API
 * @date 2020/10/30 10:40
 */
public class PdfVerifyResponse extends Response {
    private PdfVerifyData data;

    public PdfVerifyData getData() {
        return data;
    }

    public void setData(PdfVerifyData data) {
        this.data = data;
    }
}
