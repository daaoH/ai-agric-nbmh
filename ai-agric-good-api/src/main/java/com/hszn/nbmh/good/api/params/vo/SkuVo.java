package com.hszn.nbmh.good.api.params.vo;

import com.hszn.nbmh.good.api.entity.NbmhGoodsSku;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午8:04 22/9/1
 * @Modified By:
 */
@Schema(description = "sku信息")
@Data
public class SkuVo extends NbmhGoodsSku implements Serializable {

    @Schema(description = "规格信息")
    SpecsVo specsVo;

}
