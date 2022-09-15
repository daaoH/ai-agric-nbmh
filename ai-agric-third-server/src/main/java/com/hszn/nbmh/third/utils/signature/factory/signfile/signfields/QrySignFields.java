package com.hszn.nbmh.third.utils.signature.factory.signfile.signfields;

import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.QrySignFieldsResponse;

/**
 * @author 澄泓
 * @version JDK1.7
 * @description 轩辕API查询签署区列表
 * @date 2020/10/28 15:15
 */
public class QrySignFields extends Request<QrySignFieldsResponse> {
    private String flowId;
    private String accountId;
    private String signfieldIds;

    private QrySignFields() {
    }

    public QrySignFields(String flowId) {
        this.flowId = flowId;
    }

    public String getFlowId() {
        return flowId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getSignfieldIds() {
        return signfieldIds;
    }

    public void setSignfieldIds(String signfieldIds) {
        this.signfieldIds = signfieldIds;
    }

    @Override
    public void build() {
        StringBuilder url = new StringBuilder("/v1/signflows/" + flowId + "/signfields?");
        if (accountId != null) {
            url.append("&accountId=" + accountId);
        }
        if (signfieldIds != null) {
            url.append("&signfieldIds=" + signfieldIds);
        }
        super.setUrl(url.toString());
        super.setRequestType(RequestType.GET);
    }
}
