package com.hszn.nbmh.marketing.api.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.*;

/**
 * <p>
 * 优惠券领取历史记录
 * </p>
 *
 * @author lw
 * @since 2022-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NbmhCouponHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 获取方式[0->后台赠送；1->主动领取]
     */
    private Integer acquireType;

    /**
     * 优惠券所属店铺ID
     */
    private Long couponShopId;

    /**
     * 优惠券使用类型
     */
    private Integer couponUseType;

    /**
     * 优惠卷名字
     */
    private String couponName;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 使用门槛
     */
    private BigDecimal minPoint;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 使用时间
     */
    private Date useTime;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单号
     */
    private Long orderSn;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 使用状态[0->未使用；1->已使用；2->已过期]
     */
    private Integer status;

    /**
     * 优惠券关联字段
     */
    @TableField(exist = false)
    private String relation;
}
