package com.hszn.nbmh.third.utils.signature.factory.response;


import com.hszn.nbmh.third.utils.signature.factory.response.data.Data;

/**
 * @description  轩辕API
 * @author  澄泓
 * @date  2020/10/30 11:29
 * @version 
 */
public class StartSignFlowResponse extends Response {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
