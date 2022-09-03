package com.hszn.nbmh.marketing.api.params.out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 优惠券入参类
 *
 * @author liwei
 * @version 1.0
 * @since 2022-09-03 10:15
 */
@Data
@Schema(name = "CouponAcceptOut", description = "优惠券领取记录出参")
public class CouponAcceptOut {

    @Schema(name = "id", description = "优惠券id")
    private Long id;

    @Schema(name = "userId", description = "用户id")
    private Long userId;
    /**
     * 本店店铺id
     */
    @Schema(name = "shopId", description = "店铺id")
    private String shopId;

    /**
     * 每人限领张数
     */
    @Schema(name = "perLimit", description = "每人限领张数")
    private Integer perLimit;

    /**
     * 发行数量
     */
    @Schema(name = "publishCount", description = "发行数量")
    private Integer publishCount;

    /**
     * 可以领取的会员等级[0->不限等级，其他-对应等级]
     */
    @Schema(name = "userLevel", description = "可以领取的会员等级[0->不限等级，其他-对应等级]")
    private Integer userLevel;

}
