package com.hszn.nbmh.third.utils.signature.factory.signfile.signflows;

import com.alibaba.fastjson.annotation.JSONField;
import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.ArchiveSignFlowResponse;

/**
 * @author 澄泓
 * @version JDK1.7
 * @description 轩辕API签署流程归档
 * @date 2020/10/28 10:51
 */
public class ArchiveSignFlow extends Request<ArchiveSignFlowResponse> {
    @JSONField(serialize = false)
    private String flowId;

    private ArchiveSignFlow() {
    }

    public ArchiveSignFlow(String flowId) {
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
        super.setUrl("/v1/signflows/" + flowId + "/archive");
        super.setRequestType(RequestType.PUT);
    }
}
