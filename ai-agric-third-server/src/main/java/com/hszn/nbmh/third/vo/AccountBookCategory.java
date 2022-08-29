package com.hszn.nbmh.third.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author fubo
 * @since 2021-12-22
 */
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(value="AccountBookCategory对象", description="")
public class AccountBookCategory implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value="id")
    @TableId(value="id", type=IdType.AUTO)
    private Long id;

    @ApiModelProperty(value="记账本id")
    private Long bizId;

    @ApiModelProperty(value="记账簿类型(0->进货单 1->出货单 2销售采购单)")
    private String bizType;

    @ApiModelProperty(value="分类id")
    private Long categoryId;

    @ApiModelProperty(value="商品名称")
    private String goodsName;

    @ApiModelProperty(value="单价")
    private BigDecimal price;

    @ApiModelProperty(value="数量")
    private int quantity;

    @ApiModelProperty(value="总价格")
    private BigDecimal totalPrice;

    @ApiModelProperty(value="单位")
    private String unit;

    @ApiModelProperty(value="创建时间")
    private Date createTime;

    @ApiModelProperty(value="修改时间")
    private Date modifyTime;

    @ApiModelProperty(value="操作人")
    private String operator;

    @ApiModelProperty(value="操作人id")
    private Long operatorId;

    @ApiModelProperty(value="作废(1->有效的  2->作废)")
    private String invalid;

    @ApiModelProperty(value="供应商id")
    private Long supplierId;

    @ApiModelProperty(value="供应商名称")
    private String supplierName;

    @ApiModelProperty(value="驾驶员id")
    private Long driverId;

    @ApiModelProperty(value="驾驶员名称")
    private String driverName;

    @ApiModelProperty(value="经销商id")
    private Long distributorId;

    @ApiModelProperty(value="进销商名称")
    private String distributorName;

    @ApiModelProperty(value="shipment_id")
    private String shipmentId;

    @TableField(exist=false)
    @ApiModelProperty(value="平均价格")
    private BigDecimal avgPrice;

    @ApiModelProperty(value="机构码")
    private String orgCode;

    @ApiModelProperty(value="换算值(计量单位/=公斤数)")
    private BigDecimal conversionValue;

    @ApiModelProperty(value="剩余数量")
    private String surplus;

    @ApiModelProperty(value="商品净重描述")
    private String deputyUnitDescribe;

    @ApiModelProperty(value="移动端唯一标识")
    private String goodsOnly;

    @ApiModelProperty(value="剩余库存（公斤数）")
    @TableField(exist=false)
    private BigDecimal surplusKg;

    @ApiModelProperty(value="冷库商品id")
    private Long goodsId;

    @ApiModelProperty(value="价格类型")
    private String priceType;
}
