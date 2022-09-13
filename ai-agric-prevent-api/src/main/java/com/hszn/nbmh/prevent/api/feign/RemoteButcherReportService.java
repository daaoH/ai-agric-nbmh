package com.hszn.nbmh.prevent.api.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.constant.UrlPathConstant;
import com.hszn.nbmh.prevent.api.entity.NbmhButcherReport;
import com.hszn.nbmh.prevent.api.fallback.ButcherReportServiceFallback;
import com.hszn.nbmh.prevent.api.params.out.NbmhButcherReportDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 屠宰申报信息 暴露接口
 * </p>
 *
 * @author MCR
 * @since 2022-08-16
 */

@FeignClient(value = ServiceNameConstant.PREVENT_SERVICE, path = UrlPathConstant.BUTCHER_REPORT, fallback = ButcherReportServiceFallback.class)
public interface RemoteButcherReportService {

    @PostMapping("/add")
    Result add(@RequestBody NbmhButcherReport entity);

    @GetMapping("/{id}")
    Result<NbmhButcherReport> getById(@PathVariable(value = "id") @NotNull Long id);

    @PutMapping
    Result update(@RequestBody NbmhButcherReport entity);

    @PostMapping("/query")
    Result<IPage<NbmhButcherReport>> query(@RequestBody NbmhButcherReport entity, @RequestParam @DecimalMin("1") int pageNum, @RequestParam @DecimalMin("1") int pageSize, @RequestParam List<OrderItem> orderItemList);

    @PostMapping("/list")
    Result<List<NbmhButcherReport>> list(@RequestBody NbmhButcherReport entity, @RequestParam List<OrderItem> orderItemList);

    @DeleteMapping("delete/{id}")
    Result delete(@PathVariable Long id);

    @PostMapping("/detail")
    Result<NbmhButcherReportDetail> detail(@RequestBody NbmhButcherReport nbmhButcherReport);
}
