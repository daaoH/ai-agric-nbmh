package com.hszn.nbmh.third.utils.signature.factory.response.other;

import com.alibaba.fastjson.JSON;
import com.hszn.nbmh.third.utils.signature.factory.response.data.SearchWordsPositionData;

import java.util.ArrayList;

/**
 * @author 澄泓
 * @description 轩辕API
 * @date 2020/10/29 17:24
 */
public class SearchWordsPositionDataList extends ArrayList {
    @Override
    public SearchWordsPositionData get(int index) {
        Object o = super.get(index);
        SearchWordsPositionData searchWordsPositionData = JSON.parseObject(o.toString(), SearchWordsPositionData.class);
        return searchWordsPositionData;
    }
}
