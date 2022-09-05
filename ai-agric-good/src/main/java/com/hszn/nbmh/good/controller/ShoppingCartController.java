package com.hszn.nbmh.good.controller;

import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.exception.ServiceException;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.good.api.entity.NbmhGoodsSku;
import com.hszn.nbmh.good.api.enums.GoodAuthEnum;
import com.hszn.nbmh.good.api.enums.GoodStatusEnum;
import com.hszn.nbmh.good.api.params.vo.CartVo;
import com.hszn.nbmh.good.service.INbmhGoodsSkuService;
import com.hszn.nbmh.good.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午3:17 22/9/2
 * @Modified By:
 */
@Tag(name = "shopping-cart", description = "购物车相关接口")
@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {


    @Autowired
    private ShoppingCartService cartService;

    @Operation(description = "获取我的购物车")
    @PostMapping("/myCart")
    public Result<CartVo> queryMyCart(){
        CartVo cartVo = cartService.getCart();
        return Result.ok(cartVo);
    }


    @Operation(description = "加入购物车")
    @Parameters({
            @Parameter(name = "skuId", description = "商品id"),
            @Parameter(name = "num", description = "购买数量")
    })
    @PostMapping("/addCart")
    public Result addCart(@NotNull(message = "产品ID") Long skuId, @NotNull(message = "购买数量不能为空") @Min(value = 1, message = "加入购物车数量必须大于0") Integer num){

        cartService.addCart(skuId, num);
        return Result.ok();
    }

}
