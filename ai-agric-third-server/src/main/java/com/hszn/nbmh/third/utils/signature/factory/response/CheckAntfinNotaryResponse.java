package com.hszn.nbmh.third.utils.signature.factory.response;


import com.hszn.nbmh.third.utils.signature.factory.response.data.CheckAntfinNotaryData;

/**
 * @author 澄泓
 * @description 轩辕API
 * @date 2020/10/29 15:54
 */
public class CheckAntfinNotaryResponse extends Response {
    private CheckAntfinNotaryData data;

    public CheckAntfinNotaryData getData() {
        return data;
    }

    public void setData(CheckAntfinNotaryData data) {
        this.data = data;
    }
}
