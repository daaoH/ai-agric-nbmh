package com.hszn.nbmh.shop.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 店铺信息表
 * </p>
 *
 * @author lw
 * @since 2022-08-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "店铺信息")
public class NbmhShopInfo implements Serializable {

    private static final long serialVersionUID = 202208311532L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商户id
     */
    private Long userId;

    /**
     * 租户id
     */
    private String tenantId;

    /**
     * 店铺logo
     */
    @Schema(description = "店铺logo")
    private String logoUrl;

    /**
     * 店铺名称
     */
    @Schema(description = "店铺名称")
    @TableField(condition = SqlCondition.LIKE)
    private String shopName;

    /**
     * 商家类型 1个体工商 2企业
     */
    @Schema(description = "商家类型 1个体工商 2企业")
    private Integer shopType;

    /**
     * 营业执照
     */
    @Schema(description = "营业执照")
    private String companyLicense;

    /**
     * 企业名称
     */
    @Schema(description = "企业名称")
    private String companyName;

    /**
     * 企业信用代码
     */
    @Schema(description = "企业信用代码")
    private String companyCode;

    /**
     * 企业电话
     */
    @Schema(description = "企业电话")
    private String companyTel;

    /**
     * 企业地址
     */
    @Schema(description = "企业地址")
    private String companyAddress;

    /**
     * 经营地址
     */
    @Schema(description = "经营地址")
    private String shopAddress;

    /**
     * 经度
     */
    @Schema(description = "经度")
    private String longitude;

    /**
     * 纬度
     */
    @Schema(description = "纬度")
    private String latitude;

    /**
     * 企业法人
     */
    @Schema(description = "企业法人")
    private String companyPerson;

    /**
     * 法人证件类型
     */
    @Schema(description = "法人证件类型")
    private String legalPersonIdType;

    /**
     * 法人身份证号
     */
    @Schema(description = "法人身份证号")
    private String legalPersonNo;

    /**
     * 法人身份证正面
     */
    @Schema(description = "法人身份证正面")
    private String personIdFront;

    /**
     * 法人身份证反面
     */
    @Schema(description = "法人身份证反面")
    private String personIdBack;

    /**
     * 经营类目
     */
    @Schema(description = "经营类目")
    private String businessType;

    /**
     * 经营模式
     */
    @Schema(description = "经营模式")
    private String businessModel;

    /**
     * 经营年限(比如5-10年）
     */
    @Schema(description = "经营年限(比如5-10年）")
    private String businessYear;

    /**
     * 团队人员
     */
    @Schema(description = "团队人员")
    private String shopTeam;

    /**
     * 0审核中，1正常 ，2已打烊，3禁用
     */
    @Schema(description = "0审核中，1正常 ，2已打烊，3禁用")
    private Integer status;

    /**
     * 认证状态 0未认证 1身份认证 2企业认证
     */
    @Schema(description = "认证状态 0未认证 1身份认证 2企业认证")
    private Integer authStatus;

    /**
     * 到期时间
     */
    @Schema(description = "到期时间")
    private Date dueTime;

    /**
     * 是否到期 0 不是 1是
     */
    @Schema(description = "是否到期 0 不是 1是")
    private Integer isDue;

    /**
     * 模版id
     */
    @Schema(description = "模版id")
    private Long shopTemplateId;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 营业时间,自行分割
     */
    @Schema(description = "营业时间")
    private String businessHours;

    /**
     * 店铺电话
     */
    @Schema(description = "店铺电话")
    private String shopPhone;

    /**
     * 收款码
     */
    @Schema(description = "收款码")
    private String payQrcode;

    /**
     * 店铺二维码
     */
    @Schema(description = "店铺二维码")
    private String shopQrcode;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 店铺简介
     */
    @Schema(description = "店铺简介")
    private String remark;


}
