package com.hszn.nbmh.third.utils.signature.factory.signfile.documents;

import com.alibaba.fastjson.annotation.JSONField;
import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.DownDocumentsResponse;

/**
 * @author 澄泓
 * @version JDK1.7
 * @description 轩辕API流程文档下载
 * @date 2020/10/28 11:17
 */
public class DownDocuments extends Request<DownDocumentsResponse> {
    @JSONField(serialize = false)
    private String flowId;

    private DownDocuments() {
    }

    public DownDocuments(String flowId) {
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
        super.setUrl("/v1/signflows/" + flowId + "/documents");
        super.setRequestType(RequestType.GET);
    }
}
