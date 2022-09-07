package com.hszn.nbmh.order.api.params.input;

import com.hszn.nbmh.user.api.entity.NbmhUserAddress;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午2:38 22/9/4
 * @Modified By:
 */
@Schema(description = "创建订单参数")
@Data
public class CreateOrderParam implements Serializable {

    /**
     * 购物车商品项
     */
    @Schema(description = "购物项数据")
    private List<PreOrderParam> orderItems;

    /**
     *　收货人地址
     */
    @Schema(description = "收货地址")
    private NbmhUserAddress userAddress;

    /**
     * 总价
     */
    @Schema(description = "总价")
    private BigDecimal amount;
}
