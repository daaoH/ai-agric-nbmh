package com.hszn.nbmh.third.utils.signature.factory.other;

import com.alibaba.fastjson.annotation.JSONField;
import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.FileInfoResponse;
import com.hszn.nbmh.third.utils.signature.factory.response.SearchWordsPositionResponse;

/**
 * @author 澄泓
 * @version JDK1.7
 * @description 轩辕API搜索关键字坐标
 * @date 2020/10/28 17:58
 */
public class FileInfoQuery extends Request<FileInfoResponse> {
    @JSONField(serialize = false)
    private String fileId;

    private FileInfoQuery() {
    }

    public FileInfoQuery(String fileId) {
        this.fileId = fileId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @Override
    public void build() {
        super.setUrl("/v1/files/" + fileId);
        super.setRequestType(RequestType.GET);
    }
}
