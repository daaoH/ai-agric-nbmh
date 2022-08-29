package com.hszn.nbmh.common.core.mould;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name="生成二维码参数")
public class CodeImageRequest {

    /**
     * 类型 1:用户信息二维码
     **/
    @Schema(name="type", description="类型  1:用户信息二维码")
    private Integer type;

    /**
     * 二维码内容即要存储在二维码中的内容(扫描二维码之后获取的内容)
     **/
    @Schema(name="content", description="二维码内容即要存储在二维码中的内容(扫描二维码之后获取的内容)")
    private String content;

    /**
     * 二维码宽
     **/
    @Schema(name="width", description="二维码宽(默认值300)")
    private float width;

    /**
     * 二维码高
     **/
    @Schema(name="height", description="二维码高(默认值300)")
    private float height;
}
