package com.hszn.nbmh.user.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户收货地址表
 * </p>
 *
 * @author yuan
 * @since 2022-08-25
 */
@Schema(description = "用户收货地址")
@Data
@EqualsAndHashCode(callSuper = false)
public class NbmhUserAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 收货人名称
     */
    @Schema(description = "收货人名称")
    private String name;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long userId;

    /**
     * 省
     */
    @Schema(description = "省")
    private String province;

    /**
     * 市
     */
    @Schema(description = "市")
    private String city;

    /**
     * 县
     */
    @Schema(description = "县")
    private String county;

    /**
     * 详细收货地址
     */
    @Schema(description = "详细收货地址")
    private String addressDetail;

    /**
     * 地区编码
     */
    @Schema(description = "地区编码")
    private String areaCode;

    /**
     * 邮政编码
     */
    @Schema(description = "邮政编码")
    private String postalCode;

    /**
     * 手机号码
     */
    @Schema(description = "手机号码")
    private String tel;

    /**
     * 是否默认地址
     */
    @Schema(description = "是否默认地址")
    private Boolean isDefault;

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
     * 状态0正常 -1删除
     */
    @Schema(description = "状态0正常 -1删除")
    private Integer status;


}
