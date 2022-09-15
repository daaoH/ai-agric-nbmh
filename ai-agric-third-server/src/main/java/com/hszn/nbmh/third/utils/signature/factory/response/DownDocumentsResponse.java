package com.hszn.nbmh.third.utils.signature.factory.response;


import com.hszn.nbmh.third.utils.signature.factory.response.data.DownDocumentsData;

/**
 * @author 澄泓
 * @description 轩辕API
 * @date 2020/10/30 10:34
 */
public class DownDocumentsResponse extends Response {

    private DownDocumentsData data;

    public DownDocumentsData getData() {
        return data;
    }

    public void setData(DownDocumentsData data) {
        this.data = data;
    }
}
