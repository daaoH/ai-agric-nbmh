package com.hszn.nbmh.third.utils.signature.factory.antfinsign;

import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.QrySignAntPushInfoResponse;

/**
 * @author 澄泓
 * @version JDK1.7
 * @description 轩辕API查询签署文件上链信息
 * @date 2020/10/28 17:37
 */
public class QrySignAntPushInfo extends Request<QrySignAntPushInfoResponse> {
    private String flowId;

    public QrySignAntPushInfo(String flowId) {
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
        super.setUrl("/v1/querySignAntPushInfo");
        super.setRequestType(RequestType.POST);
    }
}
