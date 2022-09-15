package com.hszn.nbmh.third.utils.signature.factory.response;


import com.hszn.nbmh.third.utils.signature.factory.response.data.DataSignData;

/**
 * @author 澄泓
 * @description 轩辕API
 * @date 2020/10/30 10:18
 */
public class DataSignResponse extends Response {
    private DataSignData data;

    public DataSignData getData() {
        return data;
    }

    public void setData(DataSignData data) {
        this.data = data;
    }
}
