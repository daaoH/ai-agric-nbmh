package com.hszn.nbmh.order.api.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author yuan
 * @since 2022-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NbmhOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    private Long id;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 商铺ID
     */
    private String shopId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户帐号
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户备注
     */
    private String userNote;

    /**
     * 订单类型:100->普通订单;101->满减订单;102->秒杀订单;
     */
    private Integer type;

    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 实际金额
     */
    private BigDecimal discountsAmount;

    /**
     * 应付金额（实际支付金额）
     */
    private BigDecimal payAmount;

    /**
     * 运费金额
     */
    private BigDecimal freightAmount;

    /**
     * 促销优化金额（促销价、满减、会员价）
     */
    private BigDecimal promotionAmount;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 优惠券抵扣金额
     */
    private BigDecimal couponAmount;

    /**
     * 营销活动id
     */
    private Long promotionId;

    /**
     * 满减活动ID
     */
    private Long fullScaleId;

    /**
     * 满减优惠金额
     */
    private BigDecimal fullScaleAmount;

    /**
     * 支付方式:100->未支付;101->余额支付;102->微信支付;103->好友代付;
     */
    private Integer payType;

    /**
     * 支付流水号
     */
    private String transactionId;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 关闭时间
     */
    private Date closeTime;

    /**
     * 订单状态:100->待付款;101->待发货;102->配送中;103->待提货;104->等待评价;105->已完成;200->已退款;201->部分退款;300->支付超时关闭;301->换货成功关闭;
     */
    private Integer orderStatus;

    /**
     * 订单备注
     */
    private String note;

    /**
     * 评价时间
     */
    private Date commentTime;

    /**
     * 完成时间
     */
    private Date completeTime;

    /**
     * 退款金额
     */
    private BigDecimal refundAmount;

    /**
     * 退款流水号
     */
    private String refundTransactionId;

    /**
     * 预计到货时间
     */
    private Date estimatedDeliveryTime;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除状态：0正常 -1删除
     */
    private Integer status;

    @TableField(exist = false)
    private List<NbmhOrderItem> orderItems;


}
