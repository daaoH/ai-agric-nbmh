package com.hszn.nbmh.good.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品规格表
 * </p>
 *
 * @author yuan
 * @since 2022-08-25
 */
@Schema(description = "商品规格")
@Data
@EqualsAndHashCode(callSuper = false)
public class NbmhGoodsSpecification implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品表的商品ID
     */
    @Schema(description = "商品ID")
    private Long goodsId;

    /**
     * 商品规格名称
     */
    @Schema(description = "商品规格名称")
    private String specification;

    /**
     * 商品规格值
     */
    @Schema(description = "商品规格值")
    private String value;

    /**
     * 价格
     */
    @Schema(description = "价格")
    private BigDecimal price;

    /**
     * 商品规格图片
     */
    @Schema(description = "商品规格图片")
    private String picUrl;

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
