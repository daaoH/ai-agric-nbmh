package com.hszn.nbmh.prevent.api.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.constant.UrlPathConstant;
import com.hszn.nbmh.prevent.api.entity.NbmhPreventStation;
import com.hszn.nbmh.prevent.api.fallback.PreventStationServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 * 防疫站信息 暴露接口
 * </p>
 *
 * @author MCR
 * @since 2022-08-16
 */

@FeignClient(value = ServiceNameConstant.PREVENT_SERVICE, path = UrlPathConstant.PREVENT_STATION, fallback = PreventStationServiceFallback.class)
public interface RemotePreventStationService {

    @PostMapping("/add")
    Result add(@RequestBody NbmhPreventStation entity);

    @GetMapping("/{id}")
    Result getById(@PathVariable(value = "id") @NotBlank Long id);

    @PutMapping
     Result update(@RequestBody NbmhPreventStation entity);

    @PostMapping("/query")
     Result<IPage<NbmhPreventStation>> query(@RequestBody NbmhPreventStation entity, @RequestParam @DecimalMin("1") int pageNum, @RequestParam @DecimalMin("1") int pageSize, @RequestParam List<OrderItem> orderItemList);

    @PostMapping("/list")
     Result<List<NbmhPreventStation>> list(@RequestBody NbmhPreventStation entity, @RequestParam List<OrderItem> orderItemList);

    @DeleteMapping("delete/{id}")
     Result delete(@PathVariable Long id);
}
