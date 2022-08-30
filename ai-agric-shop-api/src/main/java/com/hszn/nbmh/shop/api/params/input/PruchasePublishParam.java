package com.hszn.nbmh.shop.api.params.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author：袁德民
 * @Description:
 * @Date:上午11:32 22/8/30
 * @Modified By:
 */
@Schema(description = "采购发布查询参数")
@Data
public class PruchasePublishParam implements Serializable {

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "采购类别")
    private Integer type;

    @Schema(description = "货源地省")
    private String deliverProvince;

    @Schema(description = "货源地市")
    private String deliverCity;

    @Schema(description = "货源地县")
    private String deliverTown;

    @Schema(description = "收货地省")
    private String receiveProvince;

    @Schema(description = "收货地市")
    private String receiveCity;

    @Schema(description = "收货地县")
    private String receiveTown;

    @Schema(description = "最新发布")
    private Boolean newPublish;
}
