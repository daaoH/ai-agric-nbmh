package com.hszn.nbmh.good.api.feign;

import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.good.api.constant.GoodsPathConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

/**
 * @Author：袁德民
 * @Description:
 * @Date:上午11:01 22/8/25
 * @Modified By:
 */

@FeignClient(value = ServiceNameConstant.GOODS_SERVICE, path = GoodsPathConstant.GOODS_CATEGORY)
public interface RemoteGoodsCategoryService {


    @GetMapping("/getFirstCategory")
    Result getFirstCategory();

    @GetMapping("/getSecondCategory")
    Result getSecondCategory(@NotNull @RequestParam("pid") Integer pid);
}
