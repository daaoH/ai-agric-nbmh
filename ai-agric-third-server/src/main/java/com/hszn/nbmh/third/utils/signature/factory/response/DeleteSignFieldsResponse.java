package com.hszn.nbmh.third.utils.signature.factory.response;

import com.hszn.nbmh.third.utils.signature.factory.response.data.DeleteSignFieldsData;

/**
 * @author 澄泓
 * @description 轩辕API
 * @date 2020/10/30 11:07
 */
public class DeleteSignFieldsResponse extends Response {
    private DeleteSignFieldsData data;

    public DeleteSignFieldsData getData() {
        return data;
    }

    public void setData(DeleteSignFieldsData data) {
        this.data = data;
    }
}
