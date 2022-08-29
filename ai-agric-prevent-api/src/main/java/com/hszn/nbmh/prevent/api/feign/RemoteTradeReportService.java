package com.hszn.nbmh.prevent.api.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.constant.UrlPathConstant;
import com.hszn.nbmh.prevent.api.entity.NbmhTradeReport;
import com.hszn.nbmh.prevent.api.fallback.TradeReportServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 * 活体交易信息 暴露接口
 * </p>
 *
 * @author MCR
 * @since 2022-08-16
 */

@FeignClient(value = ServiceNameConstant.PREVENT_SERVICE, path = UrlPathConstant.TRADE_REPORT, fallback = TradeReportServiceFallback.class)
public interface RemoteTradeReportService {

    @PostMapping("/add")
    Result add(@RequestBody NbmhTradeReport entity);

    @GetMapping("/{id}")
    Result getById(@PathVariable(value = "id") @NotBlank Long id);

    @PutMapping
     Result update(@RequestBody NbmhTradeReport entity);

    @PostMapping("/query")
     Result<IPage<NbmhTradeReport>> query(@RequestBody NbmhTradeReport entity, @RequestParam @DecimalMin("1") int pageNum, @RequestParam @DecimalMin("1") int pageSize, @RequestParam List<OrderItem> orderItemList);

    @PostMapping("/list")
     Result<List<NbmhTradeReport>> list(@RequestBody NbmhTradeReport entity, @RequestParam List<OrderItem> orderItemList);

    @DeleteMapping("delete/{id}")
     Result delete(@PathVariable Long id);
}
