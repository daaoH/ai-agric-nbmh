package com.hszn.nbmh.good.api.params.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author：袁德民
 * @Description:　属性值
 * @Date:下午9:16 22/9/7
 * @Modified By:
 */
@Schema(description = "属性值")
@Data
public class AttributeVo {

    /**
     * 商品参数名称
     */
    @Schema(description = "商品参数名称")
    private String attribute;

    /**
     * 商品参数值
     */
    @Schema(description = "商品参数值")
    private String value;
}
