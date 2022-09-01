package com.hszn.nbmh.shop.api.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 开店经营类目表
 * </p>
 *
 * @author lw
 * @since 2022-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NbmhOperateCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父ID
     */
    private Long parentId;

    /**
     * 类目名称
     */
    @TableField(condition = SqlCondition.LIKE)
    private String name;

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

    @TableField(exist = false)
    private List<NbmhOperateCategory> child;
}
