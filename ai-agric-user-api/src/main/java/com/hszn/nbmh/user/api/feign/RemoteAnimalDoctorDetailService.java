package com.hszn.nbmh.user.api.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.user.api.constant.UserPathConstant;
import com.hszn.nbmh.user.api.entity.NbmhAnimalDoctorDetail;
import com.hszn.nbmh.user.api.fallback.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.util.List;

@FeignClient(contextId = "remoteAnimalDoctorDetailService", value = ServiceNameConstant.USER_SERVICE, path = UserPathConstant.NBMH_ANIMAL_DOCTOR_DETAIL, fallback = UserServiceFallback.class)
public interface RemoteAnimalDoctorDetailService {

    @PostMapping("/add")
    Result add(@RequestBody NbmhAnimalDoctorDetail nbmhAnimalDoctorDetail);

    @PostMapping("/{id}")
    Result<NbmhAnimalDoctorDetail> getById(@PathVariable(value = "id") @NotBlank Long id);

    @PutMapping
    Result update(@RequestBody NbmhAnimalDoctorDetail nbmhAnimalDoctorDetail);

    @PostMapping("/query")
    Result<IPage<NbmhAnimalDoctorDetail>> query(@RequestBody NbmhAnimalDoctorDetail nbmhAnimalDoctorDetail,
                                                @RequestParam @DecimalMin("1") int pageNum,
                                                @RequestParam @DecimalMin("1") int pageSize,
                                                @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList);

    @PostMapping("/list")
    Result<List<NbmhAnimalDoctorDetail>> list(@RequestBody NbmhAnimalDoctorDetail nbmhAnimalDoctorDetail,
                                              @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList);

    @DeleteMapping("delete/{id}")
    Result delete(@PathVariable Long id);

    @PostMapping
    Result updateAcceptOrderNum(@RequestParam(value = "doctorId") Long doctorId);

}