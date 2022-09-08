package com.hszn.nbmh.order.api.params.input;

import com.hszn.nbmh.good.api.params.vo.ShopCartItemVo;
import com.hszn.nbmh.order.api.entity.NbmhReceipt;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午4:13 22/9/7
 * @Modified By:
 */
@Schema(description = "提交订单前参数")
@Data
public class PreOrderParam implements Serializable {

    /**
     * 店铺和购物项信息
     */
    @Schema(description = "店铺和购物项信息")
    private ShopCartItemVo cartItems;

    /**
     * 购物项总金额
     */
    @Schema(description = "购物项总金额")
    private BigDecimal amount;

    /**
     * 优惠券
     */
    @Schema(description = "优惠券id")
    private Long couponId;

    /**
     * 活动id
     */
    @Schema(description = "活动id")
    private Long activityId;

    /**
     * 活动类型
     */
    @Schema(description = "活动类型")
    private Integer activityType;

    /**
     * 物流模板id
     */
    @Schema(description = "物流模板id")
    private Long freightId;

    /**
     * 运费金额
     */
    @Schema(description = "运费金额")
    private BigDecimal freightAmount;

    /**
     * 发票信息
     */
    @Schema(description = "发票数据")
    private NbmhReceipt receipt;

    /**
     * 用户备注
     */
    @Schema(description = "用户备注")
    private String userNote;

}
