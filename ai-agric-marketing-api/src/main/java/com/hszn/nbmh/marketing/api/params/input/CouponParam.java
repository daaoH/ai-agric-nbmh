package com.hszn.nbmh.marketing.api.params.input;

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
@Schema(name = "CouponParam", description = "优惠券入参类")
public class CouponParam {
    /**
     * 本店店铺id
     */
    @Schema(name = "shopId", description = "本店店铺id")
    private String shopId;

    /**
     * 优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]
     */
    @Schema(name = "couponType", description = "优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]")
    private Integer couponType;

    /**
     * 优惠券图片
     */
    @Schema(name = "couponImg", description = "优惠券图片")
    private String couponImg;

    /**
     * 优惠卷名字
     */
    @Schema(name = "couponName", description = "优惠卷名字")
    private String couponName;

    /**
     * 数量
     */
    @Schema(name = "num", description = "数量")
    private Integer num;

    /**
     * 金额
     */
    @Schema(name = "amount", description = "金额")
    private BigDecimal amount;

    /**
     * 每人限领张数
     */
    @Schema(name = "perLimit", description = "每人限领张数")
    private Integer perLimit;

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
     * 使用类型[0->全场通用；1->指定分类；2->指定商品]
     */
    @Schema(name = "useType", description = "使用类型[0->全场通用；1->指定分类；2->指定商品]")
    private Integer useType;

    /**
     * 备注
     */
    @Schema(name = "remark", description = "备注")
    private String remark;

    /**
     * 发行数量
     */
    @Schema(name = "publishCount", description = "发行数量")
    private Integer publishCount;

    /**
     * 已使用数量
     */
    @Schema(name = "useCount", description = "已使用数量")
    private Integer useCount;

    /**
     * 领取数量
     */
    @Schema(name = "receiveCount", description = "领取数量")
    private Integer receiveCount;

    /**
     * 可以领取的开始日期
     */
    @Schema(name = "enableStartTime", description = "可以领取的开始日期")
    private Date enableStartTime;

    /**
     * 可以领取的结束日期
     */
    @Schema(name = "enableEndTime", description = "可以领取的结束日期")
    private Date enableEndTime;

    /**
     * 优惠码
     */
    @Schema(name = "code", description = "优惠码")
    private String code;

    /**
     * 可以领取的会员等级[0->不限等级，其他-对应等级]
     */
    @Schema(name = "userLevel", description = "可以领取的会员等级[0->不限等级，其他-对应等级]")
    private Integer userLevel;

    /**
     * 关联商品种类
     */
    @Schema(name = "categoryList", description = "优惠券关联商品分类")
    private List<Category> categoryList;
    /**
     * 关联商品
     */
    @Schema(name = "goodsList", description = "优惠券关联商品")
    private List<Goods> goodsList;

    @Data
    @Schema(name = "Category", description = "优惠券关联商品分类")
    public static class Category {
        @Schema(name = "categoryId", description = "产品分类id")
        private Long categoryId;
        @Schema(name = "categoryName", description = "产品分类名称")
        private String categoryName;
    }

    @Data
    @Schema(name = "Goods", description = "优惠券关联商品")
    public static class Goods {
        @Schema(name = "goodsId", description = "商品id")
        private Long goodsId;
        @Schema(name = "goodsName", description = "商品名称")
        private String goodsName;
    }
}
