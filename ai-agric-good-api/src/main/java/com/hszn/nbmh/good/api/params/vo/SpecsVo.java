package com.hszn.nbmh.good.api.params.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.jasypt.encryption.BigDecimalEncryptor;

import java.math.BigDecimal;
import java.util.List;

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
    private String name;

    /**
     * 商品规格项集合
     */
    @Schema(description = "商品规格项集合")
    private List<GoodSpecs> goodSpecs;

}
