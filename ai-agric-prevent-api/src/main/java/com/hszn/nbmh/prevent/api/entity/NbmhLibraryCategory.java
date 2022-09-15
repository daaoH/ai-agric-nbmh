package com.hszn.nbmh.prevent.api.entity;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 知识库分类表
 * </p>
 *
 * @author lw
 * @since 2022-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NbmhLibraryCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 父ID
     */
    private Long parentId;

    /**
     * 类目名称
     */
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

    /**
     * 子节点
     */
    @TableField(exist = false)
    private List<NbmhLibraryCategory> child;


}
