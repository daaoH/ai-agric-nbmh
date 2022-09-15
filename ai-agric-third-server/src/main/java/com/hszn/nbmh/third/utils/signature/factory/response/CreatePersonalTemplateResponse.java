package com.hszn.nbmh.third.utils.signature.factory.response;

import com.hszn.nbmh.third.utils.signature.factory.response.data.CreateTemplateData;

/**
 * @author 澄泓
 * @description 轩辕API
 * @date 2020/10/29 18:12
 */
public class CreatePersonalTemplateResponse extends Response {
    private CreateTemplateData data;

    public CreateTemplateData getData() {
        return data;
    }

    public void setData(CreateTemplateData data) {
        this.data = data;
    }
}
