package com.hszn.nbmh.good.api.params.input;

import com.hszn.nbmh.good.api.entity.NbmhGoodsAttribute;
import com.hszn.nbmh.good.api.params.vo.AttributeVo;
import com.hszn.nbmh.good.api.params.vo.SpecsVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author：袁德民
 * @Description:新增商品
 * @Date:下午8:37 22/9/7
 * @Modified By:
 */
@Schema(description = "新增商品")
@Data
public class AddGoodsParams {

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
     * 属性集合
     */
    @Schema(description = "属性集合")
    private List<AttributeVo> attributes;

    /**
     * 规格集合
     */
    @Schema(description = "规格集合")
    private List<SpecsVo> specs;
}
