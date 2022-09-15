package com.hszn.nbmh.third.utils.signature.factory.signfile.signflows;

import com.alibaba.fastjson.annotation.JSONField;
import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.StartSignFlowResponse;

/**
 * @author 澄泓
 * @version JDK1.7
 * @description 轩辕API签署流程开启
 * @date 2020/10/27 17:34
 */
public class StartSignFlow extends Request<StartSignFlowResponse> {
    @JSONField(serialize = false)
    private String flowId;

    //禁止构造无参对象
    private StartSignFlow() {
    }

    public StartSignFlow(String flowId) {
        this.flowId = flowId;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    @Override
    public void build() {
        super.setUrl("/v1/signflows/" + flowId + "/start");
        super.setRequestType(RequestType.PUT);
    }
}
