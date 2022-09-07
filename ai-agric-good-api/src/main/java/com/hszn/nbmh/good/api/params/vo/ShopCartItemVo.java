package com.hszn.nbmh.good.api.params.vo;

import com.hszn.nbmh.shop.api.entity.NbmhShopInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Author：袁德民
 * @Description: 店铺购物项
 * @Date:下午8:16 22/9/5
 * @Modified By:
 */
@Schema(description = "购物车数据项")
@Data
public class ShopCartItemVo {

    /**
     * 店铺信息
     */
    @Schema(description = "店铺信息")
    private NbmhShopInfo shopInfo;

    /**
     * 购物车子项信息
     */
    @Schema(description = "购物车子项信息")
    List<CartItemVo> items;
}
