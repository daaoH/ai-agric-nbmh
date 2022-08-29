package com.hszn.nbmh.common.core.mould;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author wangjun
 */
@Data
public class OssData {

    /**
     * 服务器域名
     */
    @Schema(name="host", description="服务器域名")
    private String host;
    /**
     * 上传文件路径
     */
    @Schema(name="path", description="文件路径")
    private String path;
    /**
     * 上传文件名
     */
    @Schema(name="fileName", description="文件名")
    private String fileName;

    /**
     * 文件类型
     */
    @Schema(name="type", description="文件类型")
    private String type;

}
