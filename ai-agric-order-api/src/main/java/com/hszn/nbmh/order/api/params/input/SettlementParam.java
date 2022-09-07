package com.hszn.nbmh.order.api.params.input;

import com.hszn.nbmh.good.api.params.vo.ShopCartItemVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author：袁德民
 * @Description: 结算参数
 * @Date:下午5:38 22/9/7
 * @Modified By:
 */
@Schema(description = "结算参数")
@Data
public class SettlementParam implements Serializable {

    @Schema(description = "店铺和购物项信息")
    private List<ShopCartItemVo> cartItems;

    @Schema(description = "购物项总金额")
    private BigDecimal amount;
}
