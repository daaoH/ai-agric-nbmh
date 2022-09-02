package com.hszn.nbmh.shop.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 店铺扩展信息表
 * </p>
 *
 * @author lw
 * @since 2022-09-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "NbmhShopExtraInfo", description = "店铺扩展信息表")
public class NbmhShopExtraInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 店铺id
     */
    @Schema(name = "shopId", description = "店铺id")
    private Long shopId;

    /**
     * 实体标志
     */
    @Schema(name = "havingFlag", description = "实体标志(有true,无false)")
    private Boolean havingFlag;

    /**
     * 实体地址
     */
    @Schema(name = "address", description = "实体地址")
    private String address;

    /**
     * 实体照片(门头照)
     */
    @Schema(name = "entityPhoto", description = "实体照片(门头照)")
    private String entityPhoto;

    /**
     * 运营者姓名
     */
    @Schema(name = "operationName", description = "运营者姓名")
    private String operationName;

    /**
     * 运营者电话号码
     */
    @Schema(name = "operationPhone", description = "运营者电话号码")
    private String operationPhone;

    /**
     * 运营者邮箱
     */
    @Schema(name = "operationEmail", description = "运营者邮箱")
    private String operationEmail;

    /**
     * 状态
     */
    @Schema(name = "status", description = "状态")
    private Integer status;

    /**
     * 创建时间
     */
    @Schema(name = "createTime", description = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @Schema(name = "updateTime", description = "更新时间")
    private Date updateTime;

}
