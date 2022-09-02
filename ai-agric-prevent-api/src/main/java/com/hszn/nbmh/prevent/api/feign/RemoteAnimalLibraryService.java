package com.hszn.nbmh.prevent.api.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.constant.UrlPathConstant;
import com.hszn.nbmh.prevent.api.entity.NbmhAnimalLibrary;
import com.hszn.nbmh.prevent.api.fallback.AnimalLibraryServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 * 动物基因库/病例库 暴露接口
 * </p>
 *
 * @author MCR
 * @since 2022-09-02
 */

@FeignClient(value = ServiceNameConstant.PREVENT_SERVICE, path = UrlPathConstant.ANIMAL_LIBRARY, fallback = AnimalLibraryServiceFallback.class)
public interface RemoteAnimalLibraryService {

    @PostMapping("/add")
    Result add(@RequestBody NbmhAnimalLibrary entity);

    @GetMapping("/{id}")
    Result getById(@PathVariable(value = "id") @NotBlank Long id);

    @PutMapping
    Result update(@RequestBody NbmhAnimalLibrary entity);

    @PostMapping("/query")
    Result<IPage<NbmhAnimalLibrary>> query(@RequestBody NbmhAnimalLibrary entity, @RequestParam @DecimalMin("1") int pageNum, @RequestParam @DecimalMin("1") int pageSize, @RequestParam List<OrderItem> orderItemList);

    @PostMapping("/list")
    Result<List<NbmhAnimalLibrary>> list(@RequestBody NbmhAnimalLibrary entity, @RequestParam List<OrderItem> orderItemList);

    @DeleteMapping("delete/{id}")
    Result delete(@PathVariable Long id);
}
