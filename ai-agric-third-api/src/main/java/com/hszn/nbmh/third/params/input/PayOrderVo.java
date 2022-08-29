package com.hszn.nbmh.third.params.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayOrderVo {

    /**
     * 总价格
     **/
    private BigDecimal totalPrice;

    /**
     * 单号
     **/
    private String orderNo;

}
