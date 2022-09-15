package com.hszn.nbmh.third.utils.signature.factory.response;


import com.hszn.nbmh.third.utils.signature.factory.response.data.QrySealData;

/**
 * @description  轩辕API
 * @author  澄泓
 * @date  2020/10/29 18:31
 * @version 
 */
public class QryPersonSealsResponse extends Response {
    private QrySealData data;

    public QrySealData getData() {
        return data;
    }

    public void setData(QrySealData data) {
        this.data = data;
    }
}
