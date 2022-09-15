package com.hszn.nbmh.third.utils.signature.factory.response;

import com.hszn.nbmh.third.utils.signature.factory.response.data.CreateFlowOneStepData;

/**
 * @description  轩辕API
 * @author  澄泓
 * @date  2020/10/30 10:02
 * @version 
 */
public class CreateFlowOneStepResponse extends Response {
    private CreateFlowOneStepData data;

    public CreateFlowOneStepData getData() {
        return data;
    }

    public void setData(CreateFlowOneStepData data) {
        this.data = data;
    }
}
