package com.hszn.nbmh.order.api.params.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author：袁德民
 * @Description: 计算运费
 * @Date:下午2:11 22/9/14
 * @Modified By:
 */
@Schema(description = "计算运费返回数据")
@Data
public class FreightVo {

    @Schema(description = "说明")
    private String remark;

    @Schema(description = "运费")
    private BigDecimal price;
}
