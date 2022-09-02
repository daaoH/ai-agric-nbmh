package com.hszn.nbmh.prevent.api.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.constant.UrlPathConstant;
import com.hszn.nbmh.prevent.api.entity.NbmhMedicalSchedule;
import com.hszn.nbmh.prevent.api.fallback.MedicalScheduleFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 * 兽医接诊时间设置 暴露接口
 * </p>
 *
 * @author MCR
 * @since 2022-09-02
 */

@FeignClient(value = ServiceNameConstant.PREVENT_SERVICE, path = UrlPathConstant.MEDICAL_SCHEDULE, fallback = MedicalScheduleFallback.class)
public interface RemoteMedicalScheduleService {

    @PostMapping("/add")
    Result add(@RequestBody NbmhMedicalSchedule entity);

    @GetMapping("/{id}")
    Result getById(@PathVariable(value = "id") @NotBlank Long id);

    @PutMapping
    Result update(@RequestBody NbmhMedicalSchedule entity);

    @PostMapping("/query")
    Result<IPage<NbmhMedicalSchedule>> query(@RequestBody NbmhMedicalSchedule entity, @RequestParam @DecimalMin("1") int pageNum, @RequestParam @DecimalMin("1") int pageSize, @RequestParam List<OrderItem> orderItemList);

    @PostMapping("/list")
    Result<List<NbmhMedicalSchedule>> list(@RequestBody NbmhMedicalSchedule entity, @RequestParam List<OrderItem> orderItemList);

    @DeleteMapping("delete/{id}")
    Result delete(@PathVariable Long id);
}
