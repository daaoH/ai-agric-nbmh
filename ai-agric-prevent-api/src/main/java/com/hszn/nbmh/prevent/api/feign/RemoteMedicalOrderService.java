package com.hszn.nbmh.prevent.api.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.mould.QueryCondition;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.constant.UrlPathConstant;
import com.hszn.nbmh.prevent.api.entity.NbmhMedicalOrder;
import com.hszn.nbmh.prevent.api.fallback.MedicalOrderFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 诊断下单记录 暴露接口
 * </p>
 *
 * @author MCR
 * @since 2022-09-02
 */

@FeignClient(value = ServiceNameConstant.PREVENT_SERVICE, path = UrlPathConstant.MEDICAL_ORDER, fallback = MedicalOrderFallback.class)
public interface RemoteMedicalOrderService {

    @PostMapping("/add")
    Result add(@RequestBody NbmhMedicalOrder entity);

    @GetMapping("/{id}")
    Result getById(@PathVariable(value = "id") @NotNull Long id);

    @PutMapping
    Result update(@RequestBody NbmhMedicalOrder entity);

    @PostMapping("/query")
    Result<IPage<NbmhMedicalOrder>> query(@RequestBody QueryCondition<NbmhMedicalOrder> queryCondition,
                                          @RequestParam @DecimalMin("1") int pageNum,
                                          @RequestParam @DecimalMin("1") int pageSize);

    @PostMapping("/list")
    Result<List<NbmhMedicalOrder>> list(@RequestBody QueryCondition<NbmhMedicalOrder> queryCondition);

    @DeleteMapping("delete/{id}")
    Result delete(@PathVariable Long id);
}
