package com.hszn.nbmh.third.utils.signature.factory.signfile.datasign;


import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.DataSignResponse;

/**
 * @author 澄泓
 * @version JDK1.7
 * @description 轩辕API平台方&平台用户文本签
 * @date 2020/10/28 16:54
 */
public class DataSign extends Request<DataSignResponse> {
    private String accountId;
    private String data;
    private String type;

    private DataSign() {
    }

    public DataSign(String data, String type) {
        this.data = data;
        this.type = type;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void build() {
        super.setUrl("/v1/dataSign");
        super.setRequestType(RequestType.POST);
    }
}
