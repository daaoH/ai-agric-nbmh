package com.hszn.nbmh.prevent.api.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.mould.QueryCondition;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.constant.UrlPathConstant;
import com.hszn.nbmh.prevent.api.entity.NbmhAnimalLibrary;
import com.hszn.nbmh.prevent.api.fallback.AnimalLibraryServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    Result<NbmhAnimalLibrary> getById(@PathVariable(value = "id") @NotNull Long id);

    @PutMapping
    Result update(@RequestBody NbmhAnimalLibrary entity);

    @PostMapping("/query")
    Result<IPage<NbmhAnimalLibrary>> query(@RequestBody QueryCondition<NbmhAnimalLibrary> queryCondition,
                                           @RequestParam @DecimalMin("1") int pageNum,
                                           @RequestParam @DecimalMin("1") int pageSize);

    @PostMapping("/list")
    Result<List<NbmhAnimalLibrary>> list(@RequestBody QueryCondition<NbmhAnimalLibrary> queryCondition);

    @DeleteMapping("delete/{id}")
    Result delete(@PathVariable Long id);

    @PostMapping("/audit")
    Result audit(@RequestBody NbmhAnimalLibrary nbmhAnimalLibrary);
}
