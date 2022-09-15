package com.hszn.nbmh.third.utils.signature.factory.signfile.signers;

import com.alibaba.fastjson.annotation.JSONField;
import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.QrySignersResponse;

/**
 * @author 澄泓
 * @version JDK1.7
 * @description 轩辕API流程签署人列表
 * @date 2020/10/28 16:31
 */
public class QrySigners extends Request<QrySignersResponse> {
    @JSONField(serialize = false)
    private String flowId;

    private QrySigners() {
    }

    ;

    public QrySigners(String flowId) {
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
        super.setUrl("/v1/signflows/" + flowId + "/signers");
        super.setRequestType(RequestType.GET);
    }
}
