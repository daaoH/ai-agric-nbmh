package com.hszn.nbmh.third.utils.signature.factory.response;


import com.hszn.nbmh.third.utils.signature.factory.response.data.QrySignAntPushInfoData;

/**
 * @author 澄泓
 * @description 轩辕API
 * @date 2020/10/29 15:57
 */
public class QrySignAntPushInfoResponse extends Response {
    private QrySignAntPushInfoData data;

    public QrySignAntPushInfoData getData() {
        return data;
    }

    public void setData(QrySignAntPushInfoData data) {
        this.data = data;
    }
}
