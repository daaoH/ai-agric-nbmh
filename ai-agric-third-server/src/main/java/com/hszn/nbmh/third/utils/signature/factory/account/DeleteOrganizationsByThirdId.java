package com.hszn.nbmh.third.utils.signature.factory.account;

import com.alibaba.fastjson.annotation.JSONField;
import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.DeleteOrganizationsByThirdIdResponse;

/**
 * @author 澄泓
 * @version JDK1.7
 * @description 轩辕API注销机构账号（按照第三方机构ID注销）
 * @date 2020/10/23 20:19
 */
public class DeleteOrganizationsByThirdId extends Request<DeleteOrganizationsByThirdIdResponse> {
    @JSONField(serialize = false)
    private String thirdPartyUserId;

    //禁止构造无参对象
    private DeleteOrganizationsByThirdId() {
    }

    public DeleteOrganizationsByThirdId(String thirdPartyUserId) {
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
        super.setUrl("/v1/organizations/deleteByThirdId?thirdPartyUserId=" + thirdPartyUserId);
        super.setRequestType(RequestType.DELETE);
    }
}
