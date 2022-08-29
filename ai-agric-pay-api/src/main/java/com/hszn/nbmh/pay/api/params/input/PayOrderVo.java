package com.hszn.nbmh.pay.api.params.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name="订单对象")
public class PayOrderVo {

    /**
     * 总价格
     **/
    @Schema(name="totalPrice", description="总价格")
    private BigDecimal totalPrice;

    /**
     * 单号
     **/
    @Schema(name="orderNo", description="单号")
    private String orderNo;

}
