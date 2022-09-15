package com.hszn.nbmh.third.utils.signature.factory.response;

import com.hszn.nbmh.third.utils.signature.factory.response.data.GetFileUploadUrlData;

/**
 * @author 澄泓
 * @description 轩辕API
 * @date 2020/10/29 16:41
 */
public class GetFileUploadUrlResponse extends Response {
    private GetFileUploadUrlData data;

    public GetFileUploadUrlData getData() {
        return data;
    }

    public void setData(GetFileUploadUrlData data) {
        this.data = data;
    }
}
