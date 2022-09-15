package com.hszn.nbmh.third.utils.signature.factory.response;


import com.hszn.nbmh.third.utils.signature.factory.response.data.CreateFileByTemplateData;

/**
 * @description  轩辕API
 * @author  澄泓
 * @date  2020/10/29 16:45
 * @version 
 */
public class CreateFileByTemplateResponse extends Response {
    private CreateFileByTemplateData data;

    public CreateFileByTemplateData getData() {
        return data;
    }

    public void setData(CreateFileByTemplateData data) {
        this.data = data;
    }
}
