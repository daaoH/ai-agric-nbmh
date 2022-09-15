package com.hszn.nbmh.third.utils.signature.factory.signfile.documents;

import com.alibaba.fastjson.annotation.JSONField;
import com.hszn.nbmh.third.utils.signature.bean.Docs;
import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.CreateDocumentsResponse;

/**
 * @author 澄泓
 * @version JDK1.7
 * @description 轩辕API流程文档添加
 * @date 2020/10/28 11:06
 */
public class CreateDocuments extends Request<CreateDocumentsResponse> {
    @JSONField(serialize = false)
    private String flowId;
    private Docs docs;

    private CreateDocuments() {
    }

    public CreateDocuments(String flowId, Docs docs) {
        this.flowId = flowId;
        this.docs = docs;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public Docs getDocs() {
        return docs;
    }

    public void setDocs(Docs docs) {
        this.docs = docs;
    }

    @Override
    public void build() {
        super.setUrl("/v1/signflows/" + flowId + "/documents");
        super.setRequestType(RequestType.POST);
    }
}
