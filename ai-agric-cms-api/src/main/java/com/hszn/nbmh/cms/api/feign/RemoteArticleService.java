package com.hszn.nbmh.cms.api.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.cms.api.constant.UrlPathConstant;
import com.hszn.nbmh.cms.api.entity.NbmhArticle;
import com.hszn.nbmh.cms.api.fallback.NbmhArticleServiceFallback;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
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
    Result getById(@PathVariable(value = "id") @NotBlank Long id);

    @PutMapping
     Result update(@RequestBody NbmhArticle entity);

    @PostMapping("/query")
     Result<IPage<NbmhArticle>> query(@RequestBody NbmhArticle entity, @RequestParam @DecimalMin("1") int pageNum, @RequestParam @DecimalMin("1") int pageSize, @RequestParam List<OrderItem> orderItemList);

    @PostMapping("/list")
     Result<List<NbmhArticle>> list(@RequestBody NbmhArticle entity, @RequestParam List<OrderItem> orderItemList);

    @DeleteMapping("delete/{id}")
     Result delete(@PathVariable Long id);
}
