package com.hszn.nbmh.order.api.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 订单中所包含的商品
 * </p>
 *
 * @author yuan
 * @since 2022-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NbmhOrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商铺ID
     */
    private String shopId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品图片
     */
    private String goodsPic;

    /**
     * 商品名
     */
    private String goodsName;

    /**
     * 货号
     */
    private String goodsSn;

    /**
     * 销售价格
     */
    private BigDecimal goodsPrice;

    /**
     * 指导价（划线价）
     */
    private BigDecimal goodsOriginalPrice;

    /**
     * 购买数量
     */
    private Integer goodsQuantity;

    /**
     * 商品sku编号
     */
    private Long goodsSkuId;

    /**
     * 商品sku条码
     */
    private String goodsSkuCode;

    /**
     * 商品促销分解金额
     */
    private BigDecimal promotionAmount;

    /**
     * 优惠券优惠分解金额
     */
    private BigDecimal couponAmount;

    /**
     * 该商品经过优惠后的最终金额
     */
    private BigDecimal realAmount;

    /**
     * 商品规格
     */
    private String specs;

    /**
     * 供应商id
     */
    private Long providerId;

    /**
     * 退款金额
     */
    private BigDecimal refundAmount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 状态：0正常 -1删除
     */
    private Integer status;


}
