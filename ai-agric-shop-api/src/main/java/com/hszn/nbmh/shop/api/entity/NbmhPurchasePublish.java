package com.hszn.nbmh.shop.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 采购发布信息表
 * </p>
 *
 * @author yuan
 * @since 2022-08-30
 */
@Schema(description = "采购发布")
@Data
@EqualsAndHashCode(callSuper = false)
public class NbmhPurchasePublish implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 发布者
     */
    @Schema(description = "发布者")
    private Long userId;

    /**
     * 采购品种
     */
    @Schema(description = "采购品种")
    private Integer productType;

    /**
     * 采购周期 0单次1每天 2每周 3每月
     */
    @Schema(description = "采购周期 0单次1每天 2每周 3每月")
    private Integer purchCycle;

    /**
     * 采购单位
     */
    @Schema(description = "采购单位")
    private String purchUnit;

    /**
     * 采购数量
     */
    @Schema(description = "采购数量")
    private String purchNum;

    /**
     * 采购规格json数据
     */
    @Schema(description = "采购规格json数据")
    private String specs;

    @Schema(description = "货源地省")
    private String deliverProvince;

    @Schema(description = "货源地市")
    private String deliverCity;

    @Schema(description = "货源地县")
    private String deliverTown;

    /**
     * 期望货源地
     */
    @Schema(description = "期望货源地")
    private String deliverAddress;

    @Schema(description = "收货地省")
    private String receiveProvince;

    @Schema(description = "收货地市")
    private String receiveCity;

    @Schema(description = "收货地县")
    private String receiveTown;

    /**
     * 收货地
     */
    @Schema(description = "收货地")
    private String receiveAddress;

    /**
     * 补充说明
     */
    @Schema(description = "补充说明")
    private String remark;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @Schema(description = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @Schema(description = "更新时间")
    private Date updateTime;

    /**
     * 状态 0报价中 1结束 -1已过期 -2取消
     */
    @Schema(description = "状态 0报价中 1结束 -1已过期 -2取消")
    private Integer status;


}
