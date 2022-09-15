package com.hszn.nbmh.third.utils.signature.factory.filetemplate;


import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.CreateTemplateByUploadUrlResponse;

/**
 * @author 澄泓
 * @version JDK1.7
 * @description 轩辕API通过上传方式创建模板
 * @date 2020/10/27 9:37
 */
public class CreateTemplateByUploadUrl extends Request<CreateTemplateByUploadUrlResponse> {
    private String contentMd5;
    private String contentType;
    private String fileName;
    private boolean convert2Pdf;

    //禁止构造无参对象
    private CreateTemplateByUploadUrl() {
    }

    public CreateTemplateByUploadUrl(String contentMd5, String contentType, String fileName, boolean convert2Pdf) {
        this.contentMd5 = contentMd5;
        this.contentType = contentType;
        this.fileName = fileName;
        this.convert2Pdf = convert2Pdf;
    }

    public String getContentMd5() {
        return contentMd5;
    }

    public void setContentMd5(String contentMd5) {
        this.contentMd5 = contentMd5;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isConvert2Pdf() {
        return convert2Pdf;
    }

    public void setConvert2Pdf(boolean convert2Pdf) {
        this.convert2Pdf = convert2Pdf;
    }

    @Override
    public void build() {
        super.setUrl("/v1/docTemplates/createByUploadUrl");
        super.setRequestType(RequestType.POST);
    }
}
