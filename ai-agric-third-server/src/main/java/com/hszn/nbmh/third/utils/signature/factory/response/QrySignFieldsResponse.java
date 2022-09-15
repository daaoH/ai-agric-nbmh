package com.hszn.nbmh.third.utils.signature.factory.response;

import com.hszn.nbmh.third.utils.signature.factory.response.data.QrySignFieldData;

/**
 * @author 澄泓
 * @description 轩辕API
 * @date 2020/10/30 11:10
 */
public class QrySignFieldsResponse extends Response {
    private QrySignFieldData data;

    public QrySignFieldData getData() {
        return data;
    }

    public void setData(QrySignFieldData data) {
        this.data = data;
    }
}
