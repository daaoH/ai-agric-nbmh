package com.hszn.nbmh.third.utils.signature.factory.signfile.signers;


import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.GetFileSignUrlResponse;

/**
 * @author 澄泓
 * @version JDK1.7
 * @description 轩辕API获取签署地址
 * @date 2020/10/27 17:20
 */
public class GetFileSignUrl extends Request<GetFileSignUrlResponse> {
    private String flowId;
    private String accountId;
    private String organizeId;
    private Integer urlType;
    private String appScheme;

    //禁止构造无参对象
    public GetFileSignUrl() {
    }

    public GetFileSignUrl(String flowId, String accountId) {
        this.flowId = flowId;
        this.accountId = accountId;
    }

    public String getFlowId() {
        return flowId;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
    }

    public Integer getUrlType() {
        return urlType;
    }

    public void setUrlType(Integer urlType) {
        this.urlType = urlType;
    }

    public String getAppScheme() {
        return appScheme;
    }

    public void setAppScheme(String appScheme) {
        this.appScheme = appScheme;
    }

    public void build() {
        StringBuilder url = new StringBuilder("/v1/signflows/" + flowId + "/executeUrl?accountId=" + accountId);
        if (organizeId != null) {
            url.append("&organizeId=" + organizeId);
        }
        if (urlType != null) {
            url.append("&urlType=" + urlType);
        }
        if (appScheme != null) {
            url.append("&appScheme=" + appScheme);
        }
        super.setUrl(url.toString());
        super.setRequestType(RequestType.GET);
    }
}
