package com.hszn.nbmh.third.utils.signature.factory.response;


import com.hszn.nbmh.third.utils.signature.factory.response.other.SearchWordsPositionDataList;

/**
 * @author 澄泓
 * @description 轩辕API
 * @date 2020/10/29 17:11
 */
public class SearchWordsPositionResponse extends Response {
    private SearchWordsPositionDataList data;

    public SearchWordsPositionDataList getData() {
        return data;
    }

    public void setData(SearchWordsPositionDataList data) {
        this.data = data;
    }
}
