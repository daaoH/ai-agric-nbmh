package com.hszn.nbmh.third.utils.signature.factory.signfile.signfields;

import com.alibaba.fastjson.annotation.JSONField;
import com.hszn.nbmh.third.utils.signature.bean.Signfields;
import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.CreateAutoSignResponse;

/**
 * @author 澄泓
 * @version JDK1.7
 * @description 轩辕API添加签署方自动盖章签署区
 * @date 2020/10/28 15:45
 */
public class CreateAutoSign extends Request<CreateAutoSignResponse> {
    @JSONField(serialize = false)
    private String flowId;
    private Signfields signfields;

    private CreateAutoSign() {
    }

    ;

    public CreateAutoSign(String flowId, Signfields signfields) {
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
        super.setUrl("/v1/signflows/" + flowId + "/signfields/autoSign");
        super.setRequestType(RequestType.POST);
    }
}
