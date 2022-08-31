package com.hszn.nbmh.shop.api.params.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 店铺信息表
 *
 * @author liwei
 * @version 1.0
 * @since 2022-08-31 17:32
 */
@Data
@Schema(description = "店铺信息DTO")
public class ShopInfoParam {
    /**
     * 店铺ID
     */
    @Schema(description = "店铺id")
    private Long id;

    /**
     * 商户id
     */
    @Schema(description = "商户id")
    private Long userId;

    /**
     * 租户id
     */
    @Schema(description = "租户id")
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
    private String shopName;

    /**
     * 商家类型 1个体工商 2企业
     */
    @Schema(description = "商家类型 1个体工商 2企业")
    @NotNull(message = "不能为空")
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
     * 认证状态 0未认证 1身份认证 2企业认证
     */
    @Schema(description = "认证状态 0未认证 1身份认证 2企业认证")
    private Integer authStatus;

    /**
     * 营业时间,自行分割
     */
    @Schema(description = "营业时间")
    private String businessHours;

    /**
     * 店铺电话
     */
    @Schema(description = "经营电话")
    private String shopPhone;


    /**
     * 店铺简介
     */
    @Schema(description = "店铺简介")
    private String remark;

}
