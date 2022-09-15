package com.hszn.nbmh.third.utils.signature.factory.response;


import com.hszn.nbmh.third.utils.signature.factory.response.data.QrySealData;

/**
 * @author 澄泓
 * @description 轩辕API
 * @date 2020/10/29 18:30
 */
public class QryOrganizationsSealsResponse extends Response {
    private QrySealData data;

    public QrySealData getData() {
        return data;
    }

    public void setData(QrySealData data) {
        this.data = data;
    }
}
