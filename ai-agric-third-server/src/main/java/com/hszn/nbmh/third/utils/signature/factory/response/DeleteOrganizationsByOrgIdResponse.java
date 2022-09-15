package com.hszn.nbmh.third.utils.signature.factory.response;

import com.hszn.nbmh.third.utils.signature.factory.response.data.Data;

/**
 * @description  轩辕API
 * @author  澄泓
 * @date  2020/10/29 14:14
 * @version 
 */
public class DeleteOrganizationsByOrgIdResponse extends Response{
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
