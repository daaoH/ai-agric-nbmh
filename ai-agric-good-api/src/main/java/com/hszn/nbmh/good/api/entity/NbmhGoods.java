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
 * @since 2022-09-01
 */
@Schema(description = "商品基本信息")
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
     * 租户id
     */
    private String tenantId;

    /**
     * 店铺id
     */
    private Long shopId;

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
     * 商品副标题
     */
    @Schema(description = "商品副标题")
    private String subTitle;


    /**
     * 发货地省
     */
    @Schema(description = "发货地省")
    private String deliverProvince;

    /**
     * 发货地市
     */
    @Schema(description = "发货地市")
    private String deliverCity;

    /**
     * 发货地县/区
     */
    @Schema(description = "发货地县/区")
    private String deliverTown;

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
     * 运费模板ID
     */
    @Schema(description = "运费模板ID")
    private Integer freightTemplateId;

    /**
     * 图片组 json保存
     */
    @Schema(description = "图片组 json保存")
    private String albumPics;

    /**
     * 商品图片
     */
    @Schema(description = "商品图片")
    private String picUrl;

    /**
     * 视频url
     */
    @Schema(description = "视频url")
    private String videoUrl;

    /**
     * 商品关键字，采用逗号间隔
     */
    @Schema(description = "商品关键字，采用逗号间隔")
    private String keywords;

    /**
     * 商品简介
     */
    @Schema(description = "商品简介")
    private String brief;

    /**
     * 卖点描述
     */
    @Schema(description = "卖点描述")
    private String saleDescribe;

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
     * 商品单位，例如件、盒
     */
    @Schema(description = "商品单位")
    private String unit;

    /**
     * 实售价
     */
    @Schema(description = "实售价")
    private BigDecimal price;

    /**
     * 指导价（划线价）
     */
    @Schema(description = "指导价（划线价）")
    private BigDecimal originalPrice;

    /**
     * 商品分享海报
     */
    @Schema(description = "商品分享海报")
    private String shareUrl;

    /**
     * 是否为推荐商品
     */
    @Schema(description = "是否为推荐商品")
    private Boolean recommend;

    /**
     * 是否新品
     */
    @Schema(description = "是否新品")
    private Boolean isNew;

    /**
     * 是否热门产品
     */
    @Schema(description = "是否热门产品")
    private Boolean isHot;

    /**
     * 销量
     */
    @Schema(description = "销量")
    private Integer saleNum;

    /**
     * 评价数量
     */
    @Schema(description = "评价数量")
    private Integer commentNum;

    /**
     * 商品好评率
     */
    @Schema(description = "商品好评率")
    private Double grade;

    /**
     * 库存
     */
    @Schema(description = "库存")
    private Integer stock;

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
     * 状态 0正常 -1删除
     */
    @Schema(description = "状态 0正常 -1删除")
    private Integer status;


}
