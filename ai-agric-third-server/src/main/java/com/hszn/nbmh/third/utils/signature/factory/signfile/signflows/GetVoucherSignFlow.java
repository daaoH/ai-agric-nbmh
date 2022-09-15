package com.hszn.nbmh.third.utils.signature.factory.signfile.signflows;

import com.alibaba.fastjson.annotation.JSONField;
import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.GetVoucherSignFlowResponse;

/**
 * @author 澄泓
 * @version JDK1.7
 * @description 轩辕API流程签署数据存储凭据
 * @date 2020/10/28 10:58
 */
public class GetVoucherSignFlow extends Request<GetVoucherSignFlowResponse> {
    @JSONField(serialize = false)
    private String flowId;

    private GetVoucherSignFlow() {
    }

    public GetVoucherSignFlow(String flowId) {
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
        super.setUrl("/api/v2/signflows/" + flowId + "/getVoucher");
        super.setRequestType(RequestType.GET);
    }
}
