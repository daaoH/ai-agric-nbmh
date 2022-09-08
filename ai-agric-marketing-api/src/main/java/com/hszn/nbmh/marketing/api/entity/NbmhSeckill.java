package com.hszn.nbmh.marketing.api.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yuan
 * @since 2022-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NbmhSeckill implements Serializable {

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
     * 活动名称
     */
    private String activityName;

    /**
     * 店铺名称
     */
    private String shopName;

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
     * 活动结束时间
     */
    private Date endTime;

    /**
     * 报名截至时间
     */
    private Date applyEndTime;

    /**
     * 开启几点场
     */
    private String hours;

    /**
     * 申请规则
     */
    private String seckillRule;

    /**
     * 店铺ID集合以逗号分隔
     */
    private String shopIds;

    /**
     * 商品数量
     */
    private Integer goodsNum;

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
