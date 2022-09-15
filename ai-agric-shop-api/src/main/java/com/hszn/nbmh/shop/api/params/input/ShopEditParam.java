package com.hszn.nbmh.shop.api.params.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 店铺修改信息
 *
 * @author liwei
 * @version 1.0
 * @since 2022-08-31 17:32
 */
@Data
@Schema(description = "店铺修改信息")
public class ShopEditParam {
    /**
     * 店铺ID
     */
    @Schema(description = "店铺id")
    private Long id;

    /**
     * 店铺logo
     */
    @Schema(description = "店铺logo")
    private String logoUrl;

    /**
     * 店铺名称
     */
    @Schema(description = "店铺名称")
    private String shopName;

    /**
     * 经营年限(比如5-10年）
     */
    @Schema(description = "经营年限(比如5-10年）")
    private String businessYear;

    /**
     * 团队人员
     */
    @Schema(description = "团队人员")
    private String shopTeam;

    /**
     * 营业时间,自行分割
     */
    @Schema(description = "营业时间")
    private String businessHours;

    /**
     * 店铺电话
     */
    @Schema(description = "经营电话")
    private String shopPhone;

    /**
     * 店铺简介
     */
    @Schema(description = "店铺简介")
    private String remark;

    /**
     * 店铺状态
     */
    @Schema(description = "店铺状态")
    private Integer status;

}
