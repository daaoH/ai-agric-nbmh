package com.hszn.nbmh.prevent.api.feign;

import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.constant.UrlPathConstant;
import com.hszn.nbmh.prevent.api.entity.NbmhAnimal;
import com.hszn.nbmh.prevent.api.fallback.AnimalServiceFallback;
import com.hszn.nbmh.prevent.api.params.input.AnimalParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午9:59 22/8/17
 * @Modified By:
 */

@FeignClient(value=ServiceNameConstant.PREVENT_SERVICE, path=UrlPathConstant.ANIMAL, fallback=AnimalServiceFallback.class)
public interface RemoteAnimalService {

    /**
     * 防疫动物记录(分页数据)
     *
     * @param param
     * @return
     */
    @PutMapping("updateById")
    @Operation(summary="防疫员端-动物列表(分页数据)")
    @Parameter(description="分页对象<泛型对象>")
    Result updateById(@RequestBody QueryRequest<AnimalParam> param);

    /**
     * 根据农户统计动物信息
     *
     * @param userId 用户
     * @param type   用户动物种类 0猪 1牛
     * @return
     */
    @GetMapping("getCensusByUserId/{userId}/{type}")
    @Operation(summary="根据农户统计动物信息")
    @Parameter(description="用户id-userId,动物类型-type(0猪 1牛)")
    Result getCensusByUserId(@PathVariable("userId") Long userId, @PathVariable("type") Integer type);


    @PutMapping("updateById")
    @Operation(summary="更新动物信息")
    @Parameter(description="对象")
    Result updateById(@RequestBody NbmhAnimal param);
}