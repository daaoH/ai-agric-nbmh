package com.hszn.nbmh.third.utils.signature.factory.signfile.signfields;

import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.QrySignFieldResponse;

/**
 * @author 澄泓
 * @version JDK1.7
 * @description 轩辕API查询签署区列表
 * @date 2020/10/28 15:15
 */
public class QrySignField extends Request<QrySignFieldResponse> {
    private String flowId;

    private QrySignField() {
    }

    public QrySignField(String flowId) {
        this.flowId = flowId;
    }

    public String getFlowId() {
        return flowId;
    }

    @Override
    public void build() {
        super.setUrl("/v1/signflows/" + flowId + "/documents");
        super.setRequestType(RequestType.GET);
    }
}
