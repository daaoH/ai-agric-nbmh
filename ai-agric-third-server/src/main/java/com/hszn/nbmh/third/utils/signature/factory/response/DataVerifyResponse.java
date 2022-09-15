package com.hszn.nbmh.third.utils.signature.factory.response;


import com.hszn.nbmh.third.utils.signature.factory.response.data.DataVerifyData;

/**
 * @author 澄泓
 * @description 轩辕API
 * @date 2020/10/30 10:19
 */
public class DataVerifyResponse extends Response {
    private DataVerifyData data;

    public DataVerifyData getData() {
        return data;
    }

    public void setData(DataVerifyData data) {
        this.data = data;
    }
}
