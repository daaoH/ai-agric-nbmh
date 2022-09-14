package com.hszn.nbmh.pay.api.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.mould.QueryCondition;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.pay.api.constant.UrlPathConstant;
import com.hszn.nbmh.pay.api.entity.NbmhUnrealPayment;
import com.hszn.nbmh.pay.api.fallback.NbmhUnrealPaymentFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 虚拟支付信息 暴露接口
 * </p>
 *
 * @author MCR
 * @since 2022-09-14
 */

@FeignClient(value = ServiceNameConstant.PAY_SERVICE, path = UrlPathConstant.UNREAL_PAYMENT, fallback = NbmhUnrealPaymentFallback.class)
public interface RemoteUnrealPaymentService {

    @PostMapping("/add")
    Result add(@RequestBody NbmhUnrealPayment entity);

    @GetMapping("/{id}")
    Result<NbmhUnrealPayment> getById(@PathVariable(value = "id") @NotNull Long id);

    @PutMapping
    Result update(@RequestBody NbmhUnrealPayment entity);

    @PostMapping("/query")
    Result<IPage<NbmhUnrealPayment>> query(@RequestBody QueryCondition<NbmhUnrealPayment> queryCondition,
                                           @RequestParam @DecimalMin("1") int pageNum,
                                           @RequestParam @DecimalMin("1") int pageSize);

    @PostMapping("/list")
    Result<List<NbmhUnrealPayment>> list(@RequestBody QueryCondition<NbmhUnrealPayment> queryCondition);

    @DeleteMapping("delete/{id}")
    Result delete(@PathVariable Long id);
}
