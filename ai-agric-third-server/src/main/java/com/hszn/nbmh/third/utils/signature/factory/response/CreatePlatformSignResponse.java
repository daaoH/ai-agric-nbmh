package com.hszn.nbmh.third.utils.signature.factory.response;


import com.hszn.nbmh.third.utils.signature.factory.response.data.CreateSignData;

/**
 * @author 澄泓
 * @description 轩辕API
 * @date 2020/10/30 11:07
 */
public class CreatePlatformSignResponse extends Response {
    private CreateSignData data;

    public CreateSignData getData() {
        return data;
    }

    public void setData(CreateSignData data) {
        this.data = data;
    }
}
