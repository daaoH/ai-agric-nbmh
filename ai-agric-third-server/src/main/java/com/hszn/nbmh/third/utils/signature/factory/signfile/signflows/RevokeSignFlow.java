package com.hszn.nbmh.third.utils.signature.factory.signfile.signflows;

import com.alibaba.fastjson.annotation.JSONField;
import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.RevokeSignFlowResponse;

/**
 * @author 澄泓
 * @version JDK1.7
 * @description 轩辕API签署流程撤销
 * @date 2020/10/28 10:32
 */
public class RevokeSignFlow extends Request<RevokeSignFlowResponse> {
    @JSONField(serialize = false)
    private String flowId;
    private String operatorId;
    private String revokeReason;

    private RevokeSignFlow() {
    }

    public RevokeSignFlow(String flowId) {
        this.flowId = flowId;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getRevokeReason() {
        return revokeReason;
    }

    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
    }

    @Override
    public void build() {
        super.setUrl("/v1/signflows/" + flowId + "/revoke");
        super.setRequestType(RequestType.PUT);
    }
}
