package com.hszn.nbmh.third.utils.signature.factory.other;

import com.alibaba.fastjson.annotation.JSONField;
import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.SearchWordsPositionResponse;

/**
 * @author 澄泓
 * @version JDK1.7
 * @description 轩辕API搜索关键字坐标
 * @date 2020/10/28 17:58
 */
public class SearchWordsPosition extends Request<SearchWordsPositionResponse> {
    @JSONField(serialize = false)
    private String fileId;
    @JSONField(serialize = false)
    private String keywords;

    private SearchWordsPosition() {
    }

    ;

    public SearchWordsPosition(String fileId, String keywords) {
        this.fileId = fileId;
        this.keywords = keywords;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public void build() {
        super.setUrl("/v1/documents/" + fileId + "/searchWordsPosition?keywords=" + keywords);
        super.setRequestType(RequestType.GET);
    }
}
