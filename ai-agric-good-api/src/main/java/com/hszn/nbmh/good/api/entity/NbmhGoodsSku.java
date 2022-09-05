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
 * sku库存
 * </p>
 *
 * @author yuan
 * @since 2022-09-01
 */
@Schema(description = "商品sku")
@Data
@EqualsAndHashCode(callSuper = false)
public class NbmhGoodsSku implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 本店店铺id
     */
    private Long shopId;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 产品id
     */
    private Long goodsId;

    /**
     * 产品名称
     */
    @Schema(description = "产品名称")
    private String goodsName;

    /**
     * sku编码
     */
    @Schema(description = "sku编码")
    private String skuCode;

    /**
     * sku编码
     */
    @Schema(description = "sku名称")
    private String skuName;

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
     * 商品规格
     */
    @Schema(description = "商品规格")
    private String specs;

    /**
     * sku重量
     */
    @Schema(description = "sku重量")
    private BigDecimal weight;

    /**
     * 运费承担者
     */
    @Schema(description = "运费承担者")
    private String freightPayer;

    /**
     * 运费模板ID
     */
    @Schema(description = "运费模板ID")
    private Integer freightTemplateId;

    /**
     * 展示图片
     */
    @Schema(description = "展示图片")
    private String pic;

    /**
     * 实售价
     */
    @Schema(description = "实售价")
    private BigDecimal price;

    /**
     * 指导价（划线价）
     */
    @Schema(description = "指导价")
    private BigDecimal originalPrice;

    /**
     * 库存
     */
    @Schema(description = "库存")
    private Integer stock;

    /**
     * 商品状态(默认上架，0--下架（仓库中），1--上架，2--已售完)
     */
    @Schema(description = "商品状态(默认上架，0--下架（仓库中），1--上架，2--已售完)")
    private Integer goodsStatus;

    /**
     * 预警库存
     */
    @Schema(description = "预警库存")
    private Integer lowStock;

    /**
     * 销量
     */
    @Schema(description = "销量")
    private Integer saleNum;

    /**
     * 限购数量
     */
    @Schema(description = "限购数量")
    private Integer perLimit;

    /**
     * 赠送的积分
     */
    @Schema(description = "赠送的积分")
    private Integer giftIntegration;

    /**
     * 商品属性
     */
    @Schema(description = "商品属性")
    private String attribute;

    /**
     * 商品详情
     */
    @Schema(description = "商品详情")
    private String detail;

    /**
     * 评分
     */
    @Schema(description = "评分")
    private Integer score;

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
     * 状态：0正常　-1删除
     */
    @Schema(description = "状态：0正常　-1删除")
    private Integer status;


}
