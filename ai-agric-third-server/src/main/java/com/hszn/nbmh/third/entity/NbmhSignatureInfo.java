package com.hszn.nbmh.third.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 签名信息表
 * </p>
 *
 * @author lw
 * @since 2022-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NbmhSignatureInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商户id
     */
    private Long userId;

    /**
     * 店铺Id
     */
    private Long shopId;

    /**
     * 签名流程ID
     */
    private String flowId;

    /**
     * 第三方用户ID
     */
    private String accountId;

    /**
     * 状态,0创建,1已读,2已签订
     */
    private Integer status;

    /**
     * 签名文档地址
     */
    private String fileUrl;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
