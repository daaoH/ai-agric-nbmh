package com.hszn.nbmh.user.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @Null(message = "新增数据时Id必须为null", groups = {Save.class})
    @NotNull(message = "更新或删除数据时Id不能为空", groups = {Update.class, Delete.class})
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "主键id")
    private Long id;

    /**
     * 兽医用户id
     */
    @NotNull(message = "新增数据时兽医用户userId不能为空", groups = {Save.class})
    @Schema(name = "userId", description = "兽医用户id")
    private Long userId;

    /**
     * 兽医用户姓名
     */
    @TableField(condition = SqlCondition.LIKE)
    @NotNull(message = "新增数据时兽医用户姓名userName不能为空", groups = {Save.class})
    @Schema(name = "userName", description = "兽医用户姓名")
    private String userName;

    /**
     * 累计接诊次数
     */
    @Schema(name = "acceptOrderNum", description = "累计接诊次数")
    private Integer acceptOrderNum;

    /**
     * 兽医类型0:非官方，1:官方
     */
    @Schema(name = "doctorType", description = "兽医类型0:非官方，1:官方")
    private Integer doctorType;

    /**
     * 擅长畜种0:猪，1:牛，2：羊；3：鸡；4：鸭；5：鹅；6：狗，以逗号分割
     */
    @TableField(condition = SqlCondition.LIKE)
    @Schema(name = "goodAnimalType", description = "擅长畜种0:猪，1:牛，2：羊；3：鸡；4：鸭；5：鹅；6：狗")
    private String goodAnimalType;

    /**
     * 兽医所属地区（例：云南省昆明市官渡区）
     */
    @TableField(condition = SqlCondition.LIKE)
    @NotBlank(message = "新增数据时兽医所属地区area不能为空", groups = {Save.class})
    @Schema(name = "area", description = "兽医所属地区（例：云南省昆明市官渡区）")
    private String area;

    /**
     * 擅长病症
     */
    @NotBlank(message = "新增数据时擅长病症goodDisease不能为空", groups = {Save.class})
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

    /**
     * 是否接诊（0：休息中；1：可接诊）
     */
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
