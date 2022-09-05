package com.hszn.nbmh.good.api.params.vo;

import com.hszn.nbmh.shop.api.entity.NbmhShopInfo;
import lombok.Data;

import java.util.List;

/**
 * @Author：袁德民
 * @Description: 店铺购物项
 * @Date:下午8:16 22/9/5
 * @Modified By:
 */

@Data
public class ShopCartItemVo {

    /**
     * 店铺信息
     */
    private NbmhShopInfo shopInfo;

    /**
     * 购物车子项信息
     */
    List<CartItemVo> items;
}
