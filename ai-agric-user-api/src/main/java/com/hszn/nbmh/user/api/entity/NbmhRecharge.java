package com.hszn.nbmh.user.api.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 充值记录表
 * </p>
 *
 * @author yuan
 * @since 2022-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NbmhRecharge implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 支付状态
     */
    private String payStatus;

    /**
     * 充值金额
     */
    private BigDecimal rechargeMoney;

    /**
     * 充值订单编号
     */
    private String rechargeSn;

    /**
     * 充值方式
     */
    private String rechargeWay;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 第三方流水号
     */
    private String transactionId;

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
