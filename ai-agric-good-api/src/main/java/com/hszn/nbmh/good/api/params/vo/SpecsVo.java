package com.hszn.nbmh.good.api.params.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author：袁德民
 * @Description: 商品规格信息
 * @Date:下午7:26 22/8/25
 * @Modified By:
 */
@Schema(description = "商品规格信息")
@Data
public class SpecsVo {

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
     * 商品规格图片
     */
    @Schema(description = "商品规格图片")
    private String picUrl;
}
