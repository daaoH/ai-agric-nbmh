package com.hszn.nbmh.prevent.api.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.constant.UrlPathConstant;
import com.hszn.nbmh.prevent.api.entity.NbmhEarRecord;
import com.hszn.nbmh.prevent.api.fallback.NbmhEarRecordFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 * 耳标补打信息 暴露接口
 * </p>
 *
 * @author MCR
 * @since 2022-08-16
 */

@FeignClient(value = ServiceNameConstant.PREVENT_SERVICE, path = UrlPathConstant.EAR_RECORD, fallback = NbmhEarRecordFallback.class)
public interface RemoteNbmhEarRecordService {

    @PostMapping("/add")
    Result add(@RequestBody NbmhEarRecord entity);

    @GetMapping("/{id}")
    Result getById(@PathVariable(value = "id") @NotBlank Long id);

    @PutMapping
     Result update(@RequestBody NbmhEarRecord entity);

    @PostMapping("/query")
     Result<IPage<NbmhEarRecord>> query(@RequestBody NbmhEarRecord entity, @RequestParam @DecimalMin("1") int pageNum, @RequestParam @DecimalMin("1") int pageSize, @RequestParam List<OrderItem> orderItemList);

    @PostMapping("/list")
     Result<List<NbmhEarRecord>> list(@RequestBody NbmhEarRecord entity, @RequestParam List<OrderItem> orderItemList);

    @DeleteMapping("delete/{id}")
     Result delete(@PathVariable Long id);
}
