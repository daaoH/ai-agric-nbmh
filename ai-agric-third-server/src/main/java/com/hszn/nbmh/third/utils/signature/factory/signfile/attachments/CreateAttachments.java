package com.hszn.nbmh.third.utils.signature.factory.signfile.attachments;


import com.alibaba.fastjson.annotation.JSONField;
import com.hszn.nbmh.third.utils.signature.bean.Attachments;
import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.data.CreateAttachmentsResponse;

/**
 * @description  轩辕API流程附件添加
 * @author  澄泓
 * @date  2020/10/28 14:16
 * @version JDK1.7
 */
public class CreateAttachments extends Request<CreateAttachmentsResponse> {
    @JSONField(serialize = false)
    private String flowId;
    private Attachments attachments;

    private CreateAttachments(){};
    public CreateAttachments(String flowId, Attachments attachments) {
        this.flowId = flowId;
        this.attachments = attachments;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public Attachments getAttachments() {
        return attachments;
    }

    public void setAttachments(Attachments attachments) {
        this.attachments = attachments;
    }

    @Override
    public void build() {
        super.setUrl("/v1/signflows/"+flowId+"/attachments");
        super.setRequestType(RequestType.POST);
    }
}
