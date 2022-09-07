package com.hszn.nbmh.order.api.params.out;

import com.hszn.nbmh.good.api.params.vo.ShopCartItemVo;
import com.hszn.nbmh.marketing.api.entity.NbmhCoupon;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author：袁德民
 * @Description: 结算返回数据
 * @Date:下午5:43 22/9/7
 * @Modified By:
 */
@Schema(description = "结算返回数据")
@Data
public class SettlementReturn implements Serializable {

    @Schema(description = "商店和购物项")
    private ShopCartItemVo cartItems;

    @Schema(description = "可用优惠券")
    private List<NbmhCoupon> coupons;

    @Schema(description = "运费")
    private BigDecimal freightAmount;

    //各种活动


}
