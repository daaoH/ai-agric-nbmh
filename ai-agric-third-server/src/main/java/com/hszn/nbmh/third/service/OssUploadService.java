package com.hszn.nbmh.third.service;

import com.hszn.nbmh.common.core.mould.OssData;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author wangjun
 */
public interface OssUploadService {
    /**
     * 上传文件
     *
     * @param file 文件
     * @return {@code FileData}
     */
    OssData upload(MultipartFile file);
}
