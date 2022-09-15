package com.hszn.nbmh.third.utils.signature.factory.response.data;


import com.hszn.nbmh.third.utils.signature.factory.response.Response;

/**
 * @author 澄泓
 * @description 轩辕API
 * @date 2020/10/30 10:11
 */
public class CreateAttachmentsResponse extends Response {
    private CreateAttachmentsData data;

    public CreateAttachmentsData getData() {
        return data;
    }

    public void setData(CreateAttachmentsData data) {
        this.data = data;
    }
}
