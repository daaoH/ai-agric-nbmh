package com.hszn.nbmh.third.utils.signature.factory.account;

import com.alibaba.fastjson.annotation.JSONField;
import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.QryOrganizationsByOrgIdResponse;

/**
 * @description  轩辕API查询机构账号（按照账号ID查询）
 * @author  澄泓
 * @date  2020/10/23 19:55
 * @version JDK1.7
 */
public class QryOrganizationsByOrgId extends Request<QryOrganizationsByOrgIdResponse> {
    @JSONField(serialize = false)
    private String orgId;

    //禁止构造无参对象
    private QryOrganizationsByOrgId() {}

    public QryOrganizationsByOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Override
    public void build() {
        super.setUrl("/v1/organizations/"+orgId);
        super.setRequestType(RequestType.GET);
    }
}
