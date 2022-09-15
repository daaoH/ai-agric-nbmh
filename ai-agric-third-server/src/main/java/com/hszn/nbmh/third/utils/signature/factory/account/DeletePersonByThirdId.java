package com.hszn.nbmh.third.utils.signature.factory.account;

import com.alibaba.fastjson.annotation.JSONField;
import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.DeletePersonByThirdIdResponse;

/**
 * @description  轩辕API注销个人账户（按照第三方用户ID注销）
 * @author  澄泓
 * @date  2020/10/23 17:48
 * @version JDK1.7
 */
public class DeletePersonByThirdId extends Request<DeletePersonByThirdIdResponse> {
    @JSONField(serialize = false)
    private String thirdPartyUserId;

    //禁止构造无参对象
    private DeletePersonByThirdId() {
    }

    public DeletePersonByThirdId(String thirdPartyUserId) {
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
        super.setUrl("/v1/accounts/deleteByThirdId?thirdPartyUserId="+thirdPartyUserId);
        super.setRequestType(RequestType.DELETE);
    }
}
