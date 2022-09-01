package com.hszn.nbmh.order.api.params;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 订单查询条件
 *
 * @author liwei
 * @version 1.0
 * @since 2022-09-01 16:48
 */
@Schema(name = "OrderSearchParam", description = "订单查询条件")
@Data
public class OrderSearchParam {
    @Schema(name = "orderStatus", description = "订单状态")
    private Integer orderStatus;

    @Schema(name = "name", description = "根据名称模糊搜索")
    private String name;
}
