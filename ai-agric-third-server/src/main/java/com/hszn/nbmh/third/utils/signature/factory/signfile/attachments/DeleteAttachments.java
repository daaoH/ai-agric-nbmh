package com.hszn.nbmh.third.utils.signature.factory.signfile.attachments;

import com.alibaba.fastjson.annotation.JSONField;
import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.DeleteAttachmentsResponse;

/**
 * @description  轩辕API流程附件删除
 * @author  澄泓
 * @date  2020/10/28 15:04
 * @version JDK1.7
 */
public class DeleteAttachments extends Request<DeleteAttachmentsResponse> {
    @JSONField(serialize = false)
    private String flowId;
    @JSONField(serialize = false)
    private String fileIds;

    private DeleteAttachments(){};
    public DeleteAttachments(String flowId, String fileIds) {
        this.flowId = flowId;
        this.fileIds = fileIds;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getFileIds() {
        return fileIds;
    }

    public void setFileIds(String fileIds) {
        this.fileIds = fileIds;
    }

    @Override
    public void build() {
        super.setUrl("/v1/signflows/"+flowId+"/attachments?fileIds="+fileIds);
        super.setRequestType(RequestType.DELETE);
    }
}
