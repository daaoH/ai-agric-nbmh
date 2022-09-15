package com.hszn.nbmh.third.utils.signature.factory.signfile.signfields;

import com.alibaba.fastjson.annotation.JSONField;
import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.DeleteSignFieldsResponse;

/**
 * @author 澄泓
 * @version JDK1.7
 * @description 轩辕API删除签署区
 * @date 2020/10/28 16:23
 */
public class DeleteSignFields extends Request<DeleteSignFieldsResponse> {
    @JSONField(serialize = false)
    private String flowId;
    @JSONField(serialize = false)
    private String signfieldIds;

    private DeleteSignFields() {
    }

    public DeleteSignFields(String flowId, String signfieldIds) {
        this.flowId = flowId;
        this.signfieldIds = signfieldIds;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getSignfieldIds() {
        return signfieldIds;
    }

    public void setSignfieldIds(String signfieldIds) {
        this.signfieldIds = signfieldIds;
    }

    @Override
    public void build() {
        super.setUrl("/v1/signflows/" + flowId + "/signfields?signfieldIds=" + signfieldIds);
        super.setRequestType(RequestType.DELETE);
    }
}
