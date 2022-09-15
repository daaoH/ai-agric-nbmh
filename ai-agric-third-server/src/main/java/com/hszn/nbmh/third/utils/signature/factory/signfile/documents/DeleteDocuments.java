package com.hszn.nbmh.third.utils.signature.factory.signfile.documents;

import com.alibaba.fastjson.annotation.JSONField;
import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.DeleteDocumentsResponse;

/**
 * @author 澄泓
 * @version JDK1.7
 * @description 轩辕API流程文档删除
 * @date 2020/10/28 11:23
 */
public class DeleteDocuments extends Request<DeleteDocumentsResponse> {
    @JSONField(serialize = false)
    private String flowId;
    @JSONField(serialize = false)
    private String fileIds;

    private DeleteDocuments() {
    }

    public DeleteDocuments(String flowId, String fileIds) {
        this.flowId = flowId;
        this.fileIds = fileIds;
    }

    public String getFlowId() {
        return flowId;
    }

    public String getFileIds() {
        return fileIds;
    }

    @Override
    public void build() {
        super.setUrl("/v1/signflows/" + flowId + "/documents?fileIds=" + fileIds);
        super.setRequestType(RequestType.DELETE);
    }
}
