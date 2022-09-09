package com.hszn.nbmh.user.api.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.mould.QueryCondition;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.user.api.constant.UserPathConstant;
import com.hszn.nbmh.user.api.entity.NbmhUserFollowExpert;
import com.hszn.nbmh.user.api.fallback.UserFollowExpertServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 关注的专家记录 暴露接口
 * </p>
 *
 * @author MCR
 * @since 2022-09-08
 */
@FeignClient(contextId = "remoteUserFollowExpertService", value = ServiceNameConstant.USER_SERVICE, path = UserPathConstant.NBMH_USER_FOLLOW_EXPERT, fallback = UserFollowExpertServiceFallback.class)
public interface RemoteUserFollowExpertService {

    @PostMapping("/add")
    Result add(@RequestBody NbmhUserFollowExpert entity);

    @PostMapping("/{id}")
    Result<NbmhUserFollowExpert> getById(@PathVariable(value = "id") @NotNull Long id);

    @PutMapping
    Result update(@RequestBody NbmhUserFollowExpert entity);

    @PostMapping("/query")
    Result<IPage<NbmhUserFollowExpert>> query(@RequestBody QueryCondition<NbmhUserFollowExpert> queryCondition,
                                              @RequestParam @DecimalMin("1") int pageNum,
                                              @RequestParam @DecimalMin("1") int pageSize);

    @PostMapping("/list")
    Result<List<NbmhUserFollowExpert>> list(@RequestBody QueryCondition<NbmhUserFollowExpert> queryCondition);

    @DeleteMapping("delete/{id}")
    Result delete(@PathVariable Long id);

}