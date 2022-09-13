package com.hszn.nbmh.prevent.api.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.mould.QueryCondition;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.constant.UrlPathConstant;
import com.hszn.nbmh.prevent.api.entity.NbmhPrescription;
import com.hszn.nbmh.prevent.api.fallback.PrescriptionServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 处方基础信息表 暴露接口
 * </p>
 *
 * @author MCR
 * @since 2022-09-07
 */

@FeignClient(value = ServiceNameConstant.PREVENT_SERVICE, path = UrlPathConstant.PRESCRIPTION, fallback = PrescriptionServiceFallback.class)
public interface RemotePrescriptionService {

    @PostMapping("/add")
    Result add(@RequestBody NbmhPrescription entity);

    @GetMapping("/{id}")
    Result<NbmhPrescription> getById(@PathVariable(value = "id") @NotNull Long id);

    @PutMapping
    Result update(@RequestBody NbmhPrescription entity);

    @PostMapping("/query")
    Result<IPage<NbmhPrescription>> query(@RequestBody QueryCondition<NbmhPrescription> queryCondition,
                                          @RequestParam @DecimalMin("1") int pageNum,
                                          @RequestParam @DecimalMin("1") int pageSize);

    @PostMapping("/list")
    Result<List<NbmhPrescription>> list(@RequestBody QueryCondition<NbmhPrescription> queryCondition);

    @DeleteMapping("delete/{id}")
    Result delete(@PathVariable Long id);
}
