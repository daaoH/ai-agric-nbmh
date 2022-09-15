package com.hszn.nbmh.third.utils.signature.factory.response;

import com.hszn.nbmh.third.utils.signature.factory.response.data.FileInfoData;

/**
 * @author liwei
 * @version 1.0
 * @since 2022-09-07 20:44
 */
public class FileInfoResponse extends Response {
    private FileInfoData data;

    public FileInfoData getData() {
        return data;
    }

    public void setData(FileInfoData data) {
        this.data = data;
    }
}
