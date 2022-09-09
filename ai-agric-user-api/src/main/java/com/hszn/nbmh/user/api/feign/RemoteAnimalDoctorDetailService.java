package com.hszn.nbmh.user.api.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.mould.QueryCondition;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.user.api.constant.UserPathConstant;
import com.hszn.nbmh.user.api.entity.NbmhAnimalDoctorDetail;
import com.hszn.nbmh.user.api.fallback.AnimalDoctorDetailServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 兽医详细信息 暴露接口
 * </p>
 *
 * @author MCR
 * @since 2022-09-08
 */
@FeignClient(contextId = "remoteAnimalDoctorDetailService", value = ServiceNameConstant.USER_SERVICE, path = UserPathConstant.NBMH_ANIMAL_DOCTOR_DETAIL, fallback = AnimalDoctorDetailServiceFallback.class)
public interface RemoteAnimalDoctorDetailService {

    @PostMapping("/add")
    Result add(@RequestBody NbmhAnimalDoctorDetail entity);

    @PostMapping("/{id}")
    Result<NbmhAnimalDoctorDetail> getById(@PathVariable(value = "id") @NotNull Long id);

    @PutMapping
    Result update(@RequestBody NbmhAnimalDoctorDetail entity);

    @PostMapping("/query")
    Result<IPage<NbmhAnimalDoctorDetail>> query(@RequestBody QueryCondition<NbmhAnimalDoctorDetail> queryCondition,
                                                @RequestParam @DecimalMin("1") int pageNum,
                                                @RequestParam @DecimalMin("1") int pageSize);

    @PostMapping("/list")
    Result<List<NbmhAnimalDoctorDetail>> list(@RequestBody QueryCondition<NbmhAnimalDoctorDetail> queryCondition);

    @DeleteMapping("delete/{id}")
    Result delete(@PathVariable Long id);

    @PostMapping
    Result updateAcceptOrderNum(@RequestParam(value = "doctorId") Long doctorId);

}