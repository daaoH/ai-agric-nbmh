package com.hszn.nbmh.third.utils.signature.factory.response;

import com.hszn.nbmh.third.utils.signature.factory.response.data.CreateOrganizationsByThirdPartyUserIdData;

/**
 * @author 澄泓
 * @description 轩辕API
 * @date 2020/10/29 14:10
 */
public class CreateOrganizationsByThirdPartyUserIdResponse extends Response {
    private CreateOrganizationsByThirdPartyUserIdData data;

    public CreateOrganizationsByThirdPartyUserIdData getData() {
        return data;
    }

    public void setData(CreateOrganizationsByThirdPartyUserIdData data) {
        this.data = data;
    }
}
