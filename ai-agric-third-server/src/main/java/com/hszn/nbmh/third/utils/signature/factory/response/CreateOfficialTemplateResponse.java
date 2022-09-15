package com.hszn.nbmh.third.utils.signature.factory.response;


import com.hszn.nbmh.third.utils.signature.factory.response.data.CreateTemplateData;

/**
 * @description  轩辕API
 * @author  澄泓
 * @date  2020/10/29 17:33
 * @version 
 */
public class CreateOfficialTemplateResponse extends Response {
    private CreateTemplateData data;

    public CreateTemplateData getData() {
        return data;
    }

    public void setData(CreateTemplateData data) {
        this.data = data;
    }
}
