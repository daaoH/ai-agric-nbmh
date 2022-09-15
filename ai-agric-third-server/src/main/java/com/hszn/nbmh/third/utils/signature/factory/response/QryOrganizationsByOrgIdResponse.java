package com.hszn.nbmh.third.utils.signature.factory.response;


import com.hszn.nbmh.third.utils.signature.factory.response.data.QryOrganizationsData;

/**
 * @author 澄泓
 * @description 轩辕API
 * @date 2020/10/29 15:07
 */
public class QryOrganizationsByOrgIdResponse extends Response {
    private QryOrganizationsData data;

    public QryOrganizationsData getData() {
        return data;
    }

    public void setData(QryOrganizationsData data) {
        this.data = data;
    }
}
