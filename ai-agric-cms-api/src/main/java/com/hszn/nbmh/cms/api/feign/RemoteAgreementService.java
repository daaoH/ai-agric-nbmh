package com.hszn.nbmh.cms.api.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.cms.api.constant.UrlPathConstant;
import com.hszn.nbmh.cms.api.entity.NbmhAgreement;
import com.hszn.nbmh.cms.api.fallback.NbmhAgreementServiceFallback;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.mould.QueryCondition;
import com.hszn.nbmh.common.core.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 用户协议 暴露接口
 * </p>
 *
 * @author MCR
 * @since 2022-08-20
 */

@FeignClient(value = ServiceNameConstant.PREVENT_SERVICE, path = UrlPathConstant.NBMH_AGREEMENT, fallback = NbmhAgreementServiceFallback.class)
public interface RemoteAgreementService {

    @PostMapping("/add")
    Result add(@RequestBody NbmhAgreement entity);

    @GetMapping("/{id}")
    Result<NbmhAgreement> getById(@PathVariable(value = "id") @NotNull Long id);

    @PutMapping
    Result update(@RequestBody NbmhAgreement entity);

    @PostMapping("/query")
    Result<IPage<NbmhAgreement>> query(@RequestBody QueryCondition<NbmhAgreement> queryCondition,
                                       @RequestParam @DecimalMin("1") int pageNum,
                                       @RequestParam @DecimalMin("1") int pageSize);

    @PostMapping("/list")
    Result<List<NbmhAgreement>> list(@RequestBody QueryCondition<NbmhAgreement> queryCondition);

    @DeleteMapping("delete/{id}")
    Result delete(@PathVariable Long id);
}
