package com.hszn.nbmh.prevent.api.feign;

import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.prevent.api.constant.UrlPathConstant;
import com.hszn.nbmh.prevent.api.entity.NbmhVaccin;
import com.hszn.nbmh.prevent.api.fallback.VaccinServiceFallback;
import com.hszn.nbmh.prevent.api.params.input.VaccinParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午9:59 22/8/16
 * @Modified By:
 */

@FeignClient(value=ServiceNameConstant.PREVENT_SERVICE, path=UrlPathConstant.VACCIN, fallback=VaccinServiceFallback.class)
public interface RemoteVaccinService {

    /**
     * 防疫-登记
     *
     * @param param
     * @return
     */
    @PostMapping("/submit")
    @Operation(summary="防疫员端-免疫登记")
    @Parameter(description="集合对象")
    Result submit(@RequestBody VaccinParam param);

    /**
     * 防疫记录(分页数据)
     *
     * @param param
     * @return
     */
    @PostMapping("/record")
    @Operation(summary="防疫员端-防疫记录")
    @Parameter(description="分页对象<泛型对象>")
    Result record(@RequestBody QueryRequest<VaccinParam> param);


    @PostMapping("/details")
    @Operation(summary="防疫员端-防疫详情")
    @Parameter(description="对象")
    Result details(@RequestBody NbmhVaccin vaccin);


    /**
     * 防疫员 -已防疫数量(统计)
     *
     * @param param
     * @return
     */
    @PostMapping("/num")
    @Operation(summary="防疫员 -已防疫数量(统计)")
    @Parameter(description="对象")
    Result getNum(@RequestBody NbmhVaccin param);
}