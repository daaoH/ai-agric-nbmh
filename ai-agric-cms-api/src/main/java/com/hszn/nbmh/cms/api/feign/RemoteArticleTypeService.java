package com.hszn.nbmh.cms.api.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.cms.api.constant.UrlPathConstant;
import com.hszn.nbmh.cms.api.entity.NbmhArticleType;
import com.hszn.nbmh.cms.api.fallback.NbmhArticleTypeServiceFallback;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.mould.QueryCondition;
import com.hszn.nbmh.common.core.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 文章类型 暴露接口
 * </p>
 *
 * @author MCR
 * @since 2022-08-20
 */

@FeignClient(value = ServiceNameConstant.CMS_SERVICE, path = UrlPathConstant.NBMH_ARTICLE_TYPE, fallback = NbmhArticleTypeServiceFallback.class)
public interface RemoteArticleTypeService {

    @PostMapping("/add")
    Result add(@RequestBody NbmhArticleType entity);

    @GetMapping("/{id}")
    Result<NbmhArticleType> getById(@PathVariable(value = "id") @NotNull Long id);

    @PutMapping
    Result update(@RequestBody NbmhArticleType entity);

    @PostMapping("/query")
    Result<IPage<NbmhArticleType>> query(@RequestBody QueryCondition<NbmhArticleType> queryCondition,
                                         @RequestParam @DecimalMin("1") int pageNum,
                                         @RequestParam @DecimalMin("1") int pageSize);

    @PostMapping("/list")
    Result<List<NbmhArticleType>> list(@RequestBody QueryCondition<NbmhArticleType> queryCondition);

    @DeleteMapping("delete/{id}")
    Result delete(@PathVariable Long id);
}
