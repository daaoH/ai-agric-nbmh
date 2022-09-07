package com.hszn.nbmh.good.api.params.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author：袁德民
 * @Description:商品规格信息
 * @Date:下午9:55 22/9/7
 * @Modified By:
 */
@Schema(description = "商品规格信息")
@Data
public class GoodSpecs {

    /**
     * 商品规格名称
     */
    @Schema(description = "商品规格名称")
    private String specification;

    /**
     * 商品规格值
     */
    @Schema(description = "商品规格值")
    private String value;

    /**
     * 价格
     */
    @Schema(description = "价格")
    private BigDecimal price;

    /**
     * 商品规格图片
     */
    @Schema(description = "商品规格图片")
    private String picUrl;
}
