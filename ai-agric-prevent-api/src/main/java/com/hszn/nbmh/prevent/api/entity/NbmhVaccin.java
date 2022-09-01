package com.hszn.nbmh.prevent.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 防疫信息表
 * </p>
 *
 * @author dawang
 * @since 2022-08-16
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Schema(name="防疫对象")
public class NbmhVaccin implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
    @Schema(name="id", description="主键id")
    @TableId(value="id", type=IdType.AUTO)
    private Long id;

    /**
     * 防疫编号
     */
    @Schema(name="vaccinNo", description="防疫编号")
    private String vaccinNo;

    /**
     * 农户id
     */
    @Schema(name="farmerId", description="农户id")
    private Long farmerId;

    /**
     * 农户名称
     */
    @Schema(name="farmerName", description="农户名称")
    private String farmerName;

    /**
     * 农户电话
     */
    @Schema(name="farmerPhone", description="农户电话")
    private String farmerPhone;

    /**
     * 农户头像
     */
    @Schema(name="farmerAvatar", description="农户头像")
    private String farmerAvatar;

    /**
     * 身份证号
     */
    @Schema(name="farmerIdNo", description="身份证号")
    private String farmerIdNo;

    /**
     * 农户地址
     */
    @Schema(name="farmerAddress", description="农户地址")
    private String farmerAddress;

    /**
     * 动物id
     */
    @Schema(name="animalId", description="动物id")
    private Long animalId;


    /**
     * 耳标
     */
    @Schema(name="earNo", description="耳标")
    private String earNo;


    /**
     * 动物图片
     */
    @Schema(name="animalUrl", description="动物图片")
    private String animalUrl;


    /**
     * 动物月龄
     */
    @Schema(name="animalAge", description="动物月龄")
    private Integer animalAge;

    /**
     * 动物重量
     */
    @Schema(name="animalWeight", description="动物重量")
    private Float animalWeight;

    /**
     * 检疫图片
     */
    @Schema(name="animalPics", description="检疫图片")
    private String animalPics;

    /**
     * 疫苗种类
     */
    @Schema(name="vaccinType", description="疫苗种类")
    private String vaccinType;

    /**
     * 检疫时间
     */
    @Schema(name="vaccinTime", description="检疫时间")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date vaccinTime;

    /**
     * 防疫员id
     */
    @Schema(name="vaccinUserId", description="防疫员id")
    private Long vaccinUserId;

    /**
     * 检疫人员
     */
    @Schema(name="vaccinUser", description="检疫人员")
    private String vaccinUser;

    /**
     * 备注
     */
    @Schema(name="remark", description="备注")
    private String remark;

    /**
     * 创建时间
     */
    @Schema(name="createTime", description="创建时间")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    /**
     * 更新时间
     */
    @Schema(name="updateTime", description="更新时间")
    private Date updateTime;

    /**
     * 状态
     */
    @Schema(name="status", description="状态")
    private Integer status;

    /**
     * 动物类型(种类 0猪 1牛)
     */
    @Schema(name="animalType", description="动物类型(种类 0猪 1牛)")
    private Integer animalType;

    /**
     * 防疫站id
     */
    @Schema(name="preventStationId", description="防疫站id")
    private Long preventStationId;


}
