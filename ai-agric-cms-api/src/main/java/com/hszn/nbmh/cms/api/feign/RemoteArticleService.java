package com.hszn.nbmh.cms.api.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.cms.api.constant.UrlPathConstant;
import com.hszn.nbmh.cms.api.entity.NbmhArticle;
import com.hszn.nbmh.cms.api.fallback.NbmhArticleServiceFallback;
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
 * 文章信息 暴露接口
 * </p>
 *
 * @author MCR
 * @since 2022-08-20
 */

@FeignClient(value = ServiceNameConstant.PREVENT_SERVICE, path = UrlPathConstant.NBMH_ARTICLE, fallback = NbmhArticleServiceFallback.class)
public interface RemoteArticleService {

    @PostMapping("/add")
    Result add(@RequestBody NbmhArticle entity);

    @GetMapping("/{id}")
    Result<NbmhArticle> getById(@PathVariable(value = "id") @NotNull Long id);

    @PutMapping
    Result update(@RequestBody NbmhArticle entity);

    @PostMapping("/query")
    Result<IPage<NbmhArticle>> query(@RequestBody QueryCondition<NbmhArticle> queryCondition,
                                     @RequestParam @DecimalMin("1") int pageNum,
                                     @RequestParam @DecimalMin("1") int pageSize);

    @PostMapping("/list")
    Result<List<NbmhArticle>> list(@RequestBody QueryCondition<NbmhArticle> queryCondition);

    @DeleteMapping("delete/{id}")
    Result delete(@PathVariable Long id);
}
