package com.hszn.nbmh.cms.api.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.cms.api.constant.UrlPathConstant;
import com.hszn.nbmh.cms.api.entity.NbmhArticleType;
import com.hszn.nbmh.cms.api.fallback.NbmhArticleTypeServiceFallback;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 * 文章类型 暴露接口
 * </p>
 *
 * @author MCR
 * @since 2022-08-20
 */

@FeignClient(value = ServiceNameConstant.PREVENT_SERVICE, path = UrlPathConstant.NBMH_ARTICLE_TYPE, fallback = NbmhArticleTypeServiceFallback.class)
public interface RemoteArticleTypeService {

    @PostMapping("/add")
    Result add(@RequestBody NbmhArticleType entity);

    @GetMapping("/{id}")
    Result getById(@PathVariable(value = "id") @NotBlank Long id);

    @PutMapping
     Result update(@RequestBody NbmhArticleType entity);

    @PostMapping("/query")
     Result<IPage<NbmhArticleType>> query(@RequestBody NbmhArticleType entity, @RequestParam @DecimalMin("1") int pageNum, @RequestParam @DecimalMin("1") int pageSize, @RequestParam List<OrderItem> orderItemList);

    @PostMapping("/list")
     Result<List<NbmhArticleType>> list(@RequestBody NbmhArticleType entity, @RequestParam List<OrderItem> orderItemList);

    @DeleteMapping("delete/{id}")
     Result delete(@PathVariable Long id);
}
