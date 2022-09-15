package com.hszn.nbmh.third.utils.signature.factory.signfile.signfields;

import com.alibaba.fastjson.annotation.JSONField;
import com.hszn.nbmh.third.utils.signature.bean.Signfields;
import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.CreateHandSignResponse;

/**
 * @author 澄泓
 * @version JDK1.7
 * @description 轩辕API添加手动盖章签署区
 * @date 2020/10/28 15:57
 */
public class CreateHandSign extends Request<CreateHandSignResponse> {
    @JSONField(serialize = false)
    private String flowId;
    private Signfields signfields;

    private CreateHandSign() {
    }

    ;

    public CreateHandSign(String flowId, Signfields signfields) {
        this.flowId = flowId;
        this.signfields = signfields;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public Signfields getSignfields() {
        return signfields;
    }

    public void setSignfields(Signfields signfields) {
        this.signfields = signfields;
    }

    @Override
    public void build() {
        super.setUrl("/v1/signflows/" + flowId + "/signfields/handSign");
        super.setRequestType(RequestType.POST);
    }
}
