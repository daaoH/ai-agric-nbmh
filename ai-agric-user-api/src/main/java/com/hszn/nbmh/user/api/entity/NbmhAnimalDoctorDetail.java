package com.hszn.nbmh.user.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 兽医详细信息表
 * </p>
 *
 * @author MCR
 * @since 2022-08-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("nbmh_animal_doctor_detail")
@ApiModel(value = "NbmhAnimalDoctorDetail对象", description = "兽医详细信息")
public class NbmhAnimalDoctorDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Null(groups = {NbmhAnimalDoctorDetail.Save.class})
    @NotBlank(groups = {NbmhAnimalDoctorDetail.Update.class, NbmhAnimalDoctorDetail.Delete.class})
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "主键id")
    private Long id;

    /**
     * 兽医用户id
     */
    @Schema(name = "userId", description = "兽医用户id")
    private Long userId;

    /**
     * 累计接诊次数
     */
    @Schema(name = "admissions", description = "累计接诊次数")
    private Integer admissions;

    /**
     * 兽医类型0:非官方，1:官方
     */
    @Schema(name = "doctorType", description = "兽医类型0:非官方，1:官方")
    private Integer doctorType;

    /**
     * 擅长畜种0:猪，1:牛
     */
    @Schema(name = "goodAnimalType", description = "擅长畜种0:猪，1:牛")
    private Integer goodAnimalType;

    /**
     * 擅长病症
     */
    @Schema(name = "goodDisease", description = "擅长病症")
    private String goodDisease;

    /**
     * 兽医证编号
     */
    @Schema(name = "certificateNo", description = "兽医证编号")
    private String certificateNo;

    /**
     * 视频问诊金额
     */
    @Schema(name = "videoMedicalPrice", description = "视频问诊金额")
    private BigDecimal videoMedicalPrice;

    /**
     * 会诊金额
     */
    @Schema(name = "medicalPrice", description = "会诊金额")
    private BigDecimal medicalPrice;

    /**
     * 上门问诊金额
     */
    @Schema(name = "doorMedicalPrice", description = "上门问诊金额")
    private BigDecimal doorMedicalPrice;

    /**
     * 经度
     */
    @Schema(name = "longitude", description = "经度")
    private Double longitude;

    /**
     * 纬度
     */
    @Schema(name = "latitude", description = "纬度")
    private Double latitude;

    /**
     * 经纬度所计算的geo_hash码
     */
    @Schema(name = "geoCode", description = "经纬度所计算的geo_hash码")
    private String geoCode;

    /**
     * 热度（推荐度）
     */
    @Schema(name = "heatWeight", description = "热度（推荐度）")
    private Double heatWeight;

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

    /**
     * 状态 0正常 -1冻结
     */
    @Schema(name = "status", description = "状态 0正常 -1冻结")
    private Integer status;

    @ApiModelProperty(value = "是否接诊（0：休息中；1：可接诊）", hidden = true)
    @TableField(exist = false)
    private Integer medicalStatus;

    public interface Save extends Default {
    }

    public interface Update extends Default {
    }

    public interface Delete extends Default {
    }

}
