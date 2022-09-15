package com.hszn.nbmh.third.utils.signature.factory.response;


import com.hszn.nbmh.third.utils.signature.factory.response.data.CreateTemplateData;

/**
 * @author 澄泓
 * @description 轩辕API
 * @date 2020/10/29 18:15
 */
public class CreateTemplateResponse extends Response {

    private CreateTemplateData createTemplateData;

    public CreateTemplateData getCreateTemplateData() {
        return createTemplateData;
    }

    public void setCreateTemplateData(CreateTemplateData createTemplateData) {
        this.createTemplateData = createTemplateData;
    }
}
