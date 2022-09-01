package com.hszn.nbmh.good.api.params.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author：袁德民
 * @Description: 根据参数获取商品信息
 * @Date:下午5:55 22/9/1
 * @Modified By:
 */

@Schema(description = "查询商品列表参数")
@Data
public class QueryGoodsParams implements Serializable {

    @Schema(description = "发货地省")
    private String province;

    @Schema(description = "发货地市")
    private String city;

    @Schema(description = "发货地县/区")
    private String town;

    @Schema(description = "类别id")
    private Integer catogeryId;
}
