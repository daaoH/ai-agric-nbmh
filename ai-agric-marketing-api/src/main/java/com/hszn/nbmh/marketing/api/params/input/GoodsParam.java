package com.hszn.nbmh.marketing.api.params.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品入参,用于挑选可用优惠券
 *
 * @author liwei
 * @version 1.0
 * @since 2022-09-05 15:48
 */
@Data
@Schema(name = "GoodsParam", description = "商品入参,用于挑选优惠券")
public class GoodsParam {
    @Schema(name = "shopId", description = "店铺ID")
    private Long shopId;
    @Schema(name = "goodsId", description = "商品ID")
    private Long goodsId;
    @Schema(name = "categoryId", description = "商品分类")
    private Long categoryId;
    @Schema(name = "totalPrice", description = "商品总价")
    private BigDecimal totalPrice;
}
