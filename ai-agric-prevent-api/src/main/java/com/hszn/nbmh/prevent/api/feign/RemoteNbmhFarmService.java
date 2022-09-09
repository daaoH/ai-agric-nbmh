package com.hszn.nbmh.prevent.api.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.constant.UrlPathConstant;
import com.hszn.nbmh.prevent.api.entity.NbmhFarm;
import com.hszn.nbmh.prevent.api.fallback.NbmhFarmFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 养殖场信息 暴露接口
 * </p>
 *
 * @author MCR
 * @since 2022-08-16
 */

@FeignClient(value = ServiceNameConstant.PREVENT_SERVICE, path = UrlPathConstant.FARM, fallback = NbmhFarmFallback.class)
public interface RemoteNbmhFarmService {

    @PostMapping("/add")
    Result add(@RequestBody NbmhFarm entity);

    @GetMapping("/{id}")
    Result getById(@PathVariable(value = "id") @NotNull Long id);

    @PutMapping
     Result update(@RequestBody NbmhFarm entity);

    @PostMapping("/query")
     Result<IPage<NbmhFarm>> query(@RequestBody NbmhFarm entity, @RequestParam @DecimalMin("1") int pageNum, @RequestParam @DecimalMin("1") int pageSize, @RequestParam List<OrderItem> orderItemList);

    @PostMapping("/list")
     Result<List<NbmhFarm>> list(@RequestBody NbmhFarm entity, @RequestParam List<OrderItem> orderItemList);

    @DeleteMapping("delete/{id}")
     Result delete(@PathVariable Long id);
}
