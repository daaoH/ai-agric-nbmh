package com.hszn.nbmh.order.api.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 发票信息
 * </p>
 *
 * @author yuan
 * @since 2022-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NbmhReceipt implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 发票内容
     */
    private String receiptContent;

    /**
     * 发票详情
     */
    private String receiptDetail;

    /**
     * 发票金额
     */
    private String receiptPrice;

    /**
     * 发票状态
     */
    private Integer receiptStatus;

    /**
     * 发票抬头
     */
    private String receiptTitle;

    /**
     * 店铺ID
     */
    private Long shopId;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 纳税人识别号
     */
    private String taxpayer;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 状态0正常 -1删除
     */
    private Boolean status;


}
