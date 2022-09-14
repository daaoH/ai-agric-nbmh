package com.hszn.nbmh.order.api.params.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author：袁德民
 * @Description: sku库存数据
 * @Date:下午2:32 22/9/14
 * @Modified By:
 */
@Schema(description = "sku库存数据")
@AllArgsConstructor
@Data
public class SkuStockVo {

    @Schema(description = "id")
    private Long skuId;

    @Schema(description = "数量")
    private Integer num;
}
