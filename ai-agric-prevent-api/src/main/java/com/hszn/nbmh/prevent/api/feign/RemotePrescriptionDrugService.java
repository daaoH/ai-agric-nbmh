package com.hszn.nbmh.prevent.api.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.constant.UrlPathConstant;
import com.hszn.nbmh.prevent.api.entity.NbmhPrescriptionDrug;
import com.hszn.nbmh.prevent.api.fallback.PrescriptionDrugServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 * 处方药品列表 暴露接口
 * </p>
 *
 * @author MCR
 * @since 2022-09-07
 */

@FeignClient(value = ServiceNameConstant.PREVENT_SERVICE, path = UrlPathConstant.PRESCRIPTION_DRUG, fallback = PrescriptionDrugServiceFallback.class)
public interface RemotePrescriptionDrugService {

    @PostMapping("/add")
    Result add(@RequestBody NbmhPrescriptionDrug entity);

    @GetMapping("/{id}")
    Result getById(@PathVariable(value = "id") @NotBlank Long id);

    @PutMapping
    Result update(@RequestBody NbmhPrescriptionDrug entity);

    @PostMapping("/query")
    Result<IPage<NbmhPrescriptionDrug>> query(@RequestBody NbmhPrescriptionDrug entity, @RequestParam @DecimalMin("1") int pageNum, @RequestParam @DecimalMin("1") int pageSize, @RequestParam List<OrderItem> orderItemList);

    @PostMapping("/list")
    Result<List<NbmhPrescriptionDrug>> list(@RequestBody NbmhPrescriptionDrug entity, @RequestParam List<OrderItem> orderItemList);

    @DeleteMapping("delete/{id}")
    Result delete(@PathVariable Long id);
}
