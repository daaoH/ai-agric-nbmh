package com.hszn.nbmh.good.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 类目表
 * </p>
 *
 * @author yuan
 * @since 2022-08-25
 */
@Schema(description = "商品类目")
@Data
@EqualsAndHashCode(callSuper = false)
public class NbmhGoodsCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类目名称
     */
    @Schema(description = "类目名称")
    private String name;

    /**
     * 类目关键字，以JSON数组格式
     */
    @Schema(description = "类目关键字")
    private String keywords;

    /**
     * 类目广告语介绍
     */
    @Schema(description = "类目广告语介绍")
    private String describes;

    /**
     * 父类目ID
     */
    @Schema(description = "父类目ID")
    private Integer pid;

    /**
     * 类目图标
     */
    @Schema(description = "类目图标")
    private String iconUrl;

    /**
     * 类目图片
     */
    @Schema(description = "类目图片")
    private String picUrl;

    /**
     * 类目级别
     */
    @Schema(description = "类目级别")
    private String level;

    /**
     * 排序
     */
    @Schema(description = "类目排序")
    private Integer sortOrder;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private Date updateTime;

    /**
     * 状态
     */
    @Schema(description = "状态 0正常 -1删除")
    private Boolean status;


}
