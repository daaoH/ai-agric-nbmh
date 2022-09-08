package com.hszn.nbmh.marketing.api.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 秒杀活动申请
 * </p>
 *
 * @author yuan
 * @since 2022-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NbmhSeckillApply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 活动ID
     */
    private Long seckillId;

    /**
     * 商品ID
     */
    private Long skuId;

    /**
     * 店铺ID
     */
    private Long shopId;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品原始价格
     */
    private Double originalPrice;

    /**
     * 价格
     */
    private Double price;

    /**
     * 秒杀数量
     */
    private Integer seckillNum;

    /**
     * 已售数量
     */
    private Integer salesNum;

    /**
     * 促销活动申请状态
     */
    private String applyStatus;

    /**
     * 驳回原因
     */
    private String rejectReason;

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
