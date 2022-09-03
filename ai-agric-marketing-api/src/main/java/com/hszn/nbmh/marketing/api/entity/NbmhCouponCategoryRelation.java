package com.hszn.nbmh.marketing.api.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 优惠券分类关联
 * </p>
 *
 * @author lw
 * @since 2022-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
public class NbmhCouponCategoryRelation implements Serializable {

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
     * 产品分类id
     */
    private Long categoryId;

    /**
     * 产品分类名称
     */
    private String categoryName;

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
     * 状态0正常 -1删除
     */
    private Boolean status;


}
