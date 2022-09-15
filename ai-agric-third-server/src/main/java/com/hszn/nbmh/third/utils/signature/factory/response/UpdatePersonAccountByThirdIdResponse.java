package com.hszn.nbmh.third.utils.signature.factory.response;


import com.hszn.nbmh.third.utils.signature.factory.response.data.UpdatePersonAccountData;

/**
 * @author 澄泓
 * @description 轩辕API
 * @date 2020/10/29 15:52
 */
public class UpdatePersonAccountByThirdIdResponse extends Response {
    private UpdatePersonAccountData data;

    public UpdatePersonAccountData getData() {
        return data;
    }

    public void setData(UpdatePersonAccountData data) {
        this.data = data;
    }
}
