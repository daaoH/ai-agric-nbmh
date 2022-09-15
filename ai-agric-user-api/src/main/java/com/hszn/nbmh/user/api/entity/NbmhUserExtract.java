package com.hszn.nbmh.user.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户提现表
 * </p>
 *
 * @author wangjun
 * @since 2022-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NbmhUserExtract implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 名称
     */
    private String realName;

    /**
     * bank = 银行卡 alipay = 支付宝 wx=微信
     */
    private String extractType;

    /**
     * 银行卡
     */
    private String bankCode;

    /**
     * 开户地址
     */
    private String bankAddress;

    /**
     * 支付宝账号
     */
    private String alipayCode;

    /**
     * 微信号
     */
    private String wechat;

    /**
     * 提现金额
     */
    private BigDecimal extractPrice;

    /**
     * 备注
     */
    private String remark;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 失败原因
     */
    private String failMsg;

    /**
     * 失败时间
     */
    private Date failTime;

    /**
     * 添加时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 状态 -1 未通过 0 审核中 1 已提现
     */
    private Integer status;


}
