package com.hszn.nbmh.third.utils.signature.factory.signfile.attachments;

import com.alibaba.fastjson.annotation.JSONField;
import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.QryAttachmentsResponse;

/**
 * @author 澄泓
 * @version JDK1.7
 * @description 轩辕API流程附件列表
 * @date 2020/10/28 14:33
 */
public class QryAttachments extends Request<QryAttachmentsResponse> {
    @JSONField(serialize = false)
    private String flowId;

    private QryAttachments() {
    }

    ;

    public QryAttachments(String flowId) {
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
        super.setUrl("/v1/signflows/" + flowId + "/attachments");
        super.setRequestType(RequestType.GET);
    }
}
