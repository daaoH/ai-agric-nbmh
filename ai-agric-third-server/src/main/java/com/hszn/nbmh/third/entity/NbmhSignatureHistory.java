package com.hszn.nbmh.third.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 签名信息历史表
 * </p>
 *
 * @author lw
 * @since 2022-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NbmhSignatureHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 事件Id
     */
    private String action;

    /**
     * 签名流程ID
     */
    private String flowId;

    /**
     * 第三方用户ID
     */
    private String accountId;

    /**
     * 用户ID
     */
    private Long thirdPartyUserId;

    /**
     * 通知事件
     */
    private Date signTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
