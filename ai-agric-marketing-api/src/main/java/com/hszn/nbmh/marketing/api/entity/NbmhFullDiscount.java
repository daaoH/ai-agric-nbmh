package com.hszn.nbmh.marketing.api.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 满减活动表
 * </p>
 *
 * @author yuan
 * @since 2022-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NbmhFullDiscount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 店铺ID
     */
    private Long shopId;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 活动标题
     */
    private String title;

    /**
     * 活动结束时间
     */
    private Date endTime;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 范围关联的ID
     */
    private String scopeId;

    /**
     * 关联范围类型
     */
    private String scopeType;

    /**
     * 活动开始时间
     */
    private Date startTime;

    /**
     * 优惠券ID
     */
    private String couponId;

    /**
     * 活动说明
     */
    private String description;

    /**
     * 减现金
     */
    private Double fullMinus;

    /**
     * 优惠门槛金额
     */
    private Double fullMoney;

    /**
     * 打折
     */
    private Double fullRate;

    /**
     * 赠品ID
     */
    private String giftId;

    /**
     * 是否赠优惠券
     */
    private Boolean couponFlag;

    /**
     * 是否包邮
     */
    private Boolean freeFreightFlag;

    /**
     * 活动是否减现金
     */
    private Boolean fullMinusFlag;

    /**
     * 是否打折
     */
    private Boolean fullRateFlag;

    /**
     * 是否有赠品
     */
    private Boolean giftFlag;

    /**
     * 是否赠送积分
     */
    private Boolean pointFlag;

    /**
     * 赠送多少积分
     */
    private Integer point;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 状态 0正常 -1删除
     */
    private Integer status;


}
