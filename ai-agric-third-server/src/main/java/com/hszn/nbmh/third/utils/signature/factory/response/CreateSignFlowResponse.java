package com.hszn.nbmh.third.utils.signature.factory.response;


import com.hszn.nbmh.third.utils.signature.factory.response.data.CreateSignFlowData;

/**
 * @description  轩辕API
 * @author  澄泓
 * @date  2020/10/30 11:20
 * @version 
 */
public class CreateSignFlowResponse extends Response {
    private CreateSignFlowData data;

    public CreateSignFlowData getData() {
        return data;
    }

    public void setData(CreateSignFlowData data) {
        this.data = data;
    }
}
