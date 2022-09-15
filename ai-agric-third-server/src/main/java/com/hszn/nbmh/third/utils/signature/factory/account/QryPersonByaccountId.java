package com.hszn.nbmh.third.utils.signature.factory.account;

import com.alibaba.fastjson.annotation.JSONField;
import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.QryPersonByaccountIdResponse;

/**
 * @author 澄泓
 * @version JDK1.7
 * @description 轩辕API查询个人账户（按照账户ID查询）
 * @date 2020/10/23 17:03
 */
public class QryPersonByaccountId extends Request<QryPersonByaccountIdResponse> {
    @JSONField(serialize = false)
    private String accountId;

    //禁止构造无参对象
    private QryPersonByaccountId() {
    }

    ;

    public QryPersonByaccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public void build() {
        super.setUrl("/v1/accounts/" + accountId);
        super.setRequestType(RequestType.GET);
    }
}
