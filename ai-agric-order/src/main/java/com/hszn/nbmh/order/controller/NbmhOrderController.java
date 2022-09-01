package com.hszn.nbmh.order.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.order.api.entity.NbmhOrder;
import com.hszn.nbmh.order.api.params.OrderSearchParam;
import com.hszn.nbmh.order.service.INbmhOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author yuan
 * @since 2022-09-01
 */
@RestController
@RequestMapping("/nbmh-order")
@Tag(name = "/nbmh-order", description = "订单模块接口")
public class NbmhOrderController {

    private final INbmhOrderService orderService;

    public NbmhOrderController(INbmhOrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(description = "订单搜索")
    @PostMapping("/search")
    public Result<Page<NbmhOrder>> search(@RequestBody QueryRequest<OrderSearchParam> param) {
        return Result.ok(orderService.search(param));
    }
}
