package com.hszn.nbmh.third.utils.signature.factory.account;

import com.alibaba.fastjson.annotation.JSONField;
import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.QryPersonByThirdIdResponse;

/**
 * @author 澄泓
 * @version JDK1.7
 * @description 轩辕API查询个人账户（按照第三方用户ID查询）
 * @date 2020/10/23 17:21
 */
public class QryPersonByThirdId extends Request<QryPersonByThirdIdResponse> {
    //非body入参不参与签名，不做序列化
    @JSONField(serialize = false)
    private String thirdPartyUserId;

    //禁止构造无参对象
    private QryPersonByThirdId() {
    }

    public QryPersonByThirdId(String thirdPartyUserId) {
        this.thirdPartyUserId = thirdPartyUserId;
    }

    public String getThirdPartyUserId() {
        return thirdPartyUserId;
    }

    public void setThirdPartyUserId(String thirdPartyUserId) {
        this.thirdPartyUserId = thirdPartyUserId;
    }

    @Override
    public void build() {
        super.setUrl("/v1/accounts/getByThirdId?thirdPartyUserId=" + thirdPartyUserId);
        super.setRequestType(RequestType.GET);
    }
}
