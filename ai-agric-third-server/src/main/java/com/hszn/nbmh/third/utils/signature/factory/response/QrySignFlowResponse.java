package com.hszn.nbmh.third.utils.signature.factory.response;


import com.hszn.nbmh.third.utils.signature.factory.response.data.QrySignFlowData;

/**
 * @description  轩辕API
 * @author  澄泓
 * @date  2020/10/30 11:24
 * @version 
 */
public class QrySignFlowResponse extends Response {
    private QrySignFlowData data;

    public QrySignFlowData getData() {
        return data;
    }

    public void setData(QrySignFlowData data) {
        this.data = data;
    }
}
