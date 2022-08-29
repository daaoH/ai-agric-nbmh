package com.hszn.nbmh.good.api.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品基本信息表
 * </p>
 *
 * @author yuan
 * @since 2022-08-25
 */
@Schema(description = "商品信息")
@Data
@EqualsAndHashCode(callSuper = false)
public class NbmhGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品编号
     */
    @Schema(description = "商品编号")
    private String goodsSn;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    private String name;

    /**
     * 商品所属类目ID
     */
    @Schema(description = "商品所属类目ID")
    private Integer categoryId;

    /**
     * 所属品牌
     */
    @Schema(description = "所属品牌")
    private Integer brandId;

    /**
     * 商品宣传图片列表，采用JSON数组格式
     */
    @Schema(description = "商品宣传图片")
    private String gallery;

    /**
     * 商品关键字，采用逗号间隔
     */
    @Schema(description = "商品关键字")
    private String keywords;

    /**
     * 商品简介
     */
    @Schema(description = "商品简介")
    private String brief;

    /**
     * 是否上架
     */
    @Schema(description = "是否上架")
    private Boolean isOnSale;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sortOrder;

    /**
     * 商品页面商品图片
     */
    @Schema(description = "商品图片")
    private String picUrl;

    /**
     * 商品分享海报
     */
    @Schema(description = "商品分享海报")
    private String shareUrl;

    /**
     * 是否新品首发，如果设置则可以在新品首发页面展示
     */
    @Schema(description = "是否新品首发")
    private Boolean isNew;

    /**
     * 是否人气推荐，如果设置则可以在人气推荐页面展示
     */
    @Schema(description = "是否人气推荐")
    private Boolean isHot;

    /**
     * 商品单位，例如件、盒
     */
    @Schema(description = "商品单位，例如件、盒")
    private String unit;

    /**
     * 批发价格
     */
    @Schema(description = "批发价格")
    private BigDecimal wholesalePrice;

    /**
     * 零售价格
     */
    @Schema(description = "零售价格")
    private BigDecimal retailPrice;

    /**
     * 商品详情
     */
    @Schema(description = "商品详情")
    private String detail;

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
