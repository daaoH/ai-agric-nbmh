package com.hszn.nbmh.order.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.core.utils.SnowFlakeIdUtil;
import com.hszn.nbmh.order.api.entity.NbmhOrder;
import com.hszn.nbmh.order.api.params.OrderSearchParam;
import com.hszn.nbmh.order.api.params.input.CreateOrderParam;
import com.hszn.nbmh.order.service.INbmhOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author yuan
 * @since 2022-09-01
 */
@Tag(name = "nbmh-order", description = "订单模块接口")
@RestController
@RequestMapping("/nbmh-order")
public class NbmhOrderController {

    @Autowired
    private INbmhOrderService orderService;

    SnowFlakeIdUtil snowFlakeUtil = new SnowFlakeIdUtil(0, 0);

    @Operation(description = "订单搜索")
    @PostMapping("/search")
    public Result<Page<NbmhOrder>> search(@RequestBody QueryRequest<OrderSearchParam> param) {
        return Result.ok(orderService.search(param));
    }


    @Operation(description = "生成订单")
    @PostMapping("/createOrder")
    public Result createOrder(@RequestBody CreateOrderParam order){
        String orderId = orderService.createOrder(order);
        return Result.ok(orderId);
    }

}
