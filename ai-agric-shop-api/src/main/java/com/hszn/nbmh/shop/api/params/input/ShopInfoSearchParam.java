package com.hszn.nbmh.shop.api.params.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 商店查询条件类
 *
 * @author liwei
 * @version 1.0
 * @since 2022-08-31 21:26
 */
@Data
@Schema(description = "商店查询")
public class ShopInfoSearchParam {
    /**
     * 店铺ID
     */
    @Schema(description = "店铺id")
    private Long id;

    /**
     * 商户id
     */
    @Schema(description = "商户id")
    private Long userId;

    /**
     * 租户id
     */
    @Schema(description = "租户id")
    private String tenantId;

    /**
     * 店铺名称
     */
    @Schema(description = "店铺名称")
    private String shopName;

    /**
     * 商家类型 1个体工商 2企业
     */
    @Schema(description = "商家类型 1个体工商 2企业")
    private Integer shopType;

    /**
     * 0审核中，1正常 ，2已打烊，3禁用
     */
    @Schema(description = "0审核中，1正常 ，2已打烊，3禁用")
    private Integer status;

    /**
     * 认证状态 0未认证 1身份认证 2企业认证
     */
    @Schema(description = "认证状态 0未认证 1身份认证 2企业认证")
    private Integer authStatus;
}
