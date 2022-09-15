package com.hszn.nbmh.third.utils.signature.factory.signfile.signflows;

import com.alibaba.fastjson.annotation.JSONField;
import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.QrySignFlowResponse;

/**
 * @author 澄泓
 * @version JDK1.7
 * @description 轩辕API签署流程查询
 * @date 2020/10/28 10:10
 */
public class QrySignFlow extends Request<QrySignFlowResponse> {
    @JSONField(serialize = false)
    private String flowId;

    private QrySignFlow() {
    }

    public QrySignFlow(String flowId) {
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
        super.setUrl("/v1/signflows/" + flowId);
        super.setRequestType(RequestType.GET);
    }
}
