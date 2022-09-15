package com.hszn.nbmh.third.utils.signature.factory.response;


import com.hszn.nbmh.third.utils.signature.factory.response.data.GetVoucherSignFlowData;

/**
 * @author 澄泓
 * @description 轩辕API
 * @date 2020/10/30 11:22
 */
public class GetVoucherSignFlowResponse extends Response {
    private GetVoucherSignFlowData data;

    public GetVoucherSignFlowData getData() {
        return data;
    }

    public void setData(GetVoucherSignFlowData data) {
        this.data = data;
    }
}
