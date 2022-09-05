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
    private Long shopId;

    /**
     * 使用类型
     */
    @Schema(name = "useType", description = "使用类型")
    private Integer useType;

    /**
     * 优惠卷名字
     */
    @Schema(name = "couponName", description = "优惠卷名字")
    private String couponName;

    /**
     * 金额
     */
    @Schema(name = "amount", description = "金额")
    private BigDecimal amount;

    /**
     * 使用门槛
     */
    @Schema(name = "minPoint", description = "使用门槛")
    private BigDecimal minPoint;

    /**
     * 开始时间
     */
    @Schema(name = "startTime", description = "开始时间")
    private Date startTime;

    /**
     * 结束时间
     */
    @Schema(name = "endTime", description = "结束时间")
    private Date endTime;

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
