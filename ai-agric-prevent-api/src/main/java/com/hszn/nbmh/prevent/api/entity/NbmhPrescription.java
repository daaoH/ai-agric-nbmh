package com.hszn.nbmh.prevent.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 处方基础信息表
 * </p>
 *
 * @author MCR
 * @since 2022-09-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("nbmh_prescription")
@ApiModel(value = "NbmhPrescription对象", description = "处方基础信息")
public class NbmhPrescription implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Null(groups = {NbmhPrescription.Save.class})
    @NotBlank(groups = {NbmhPrescription.Update.class, NbmhPrescription.Delete.class})
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "主键id")
    private Long id;

    /**
     * 动物诊疗订单id
     */
    @Schema(name = "medicalOrderId", description = "动物诊疗订单id")
    private Long medicalOrderId;

    /**
     * 养殖户id
     */
    @Schema(name = "farmerId", description = "养殖户id")
    private Long farmerId;

    /**
     * 养殖户名称
     */
    @Schema(name = "farmerName", description = "养殖户名称")
    private String farmerName;

    /**
     * 养殖户头像
     */
    @Schema(name = "farmerAvatar", description = "养殖户头像")
    private String farmerAvatar;

    /**
     * 就诊动物类型 0猪 1牛
     */
    @Schema(name = "animalType", description = "就诊动物类型 0猪 1牛")
    private Integer animalType;

    /**
     * 诊断结果
     */
    @Schema(name = "medicalResult", description = "诊断结果")
    private String medicalResult;

    /**
     * 兽医id
     */
    @Schema(name = "doctorId", description = "兽医id")
    private Long doctorId;

    /**
     * 兽医名称
     */
    @Schema(name = "doctorName", description = "兽医名称")
    private String doctorName;

    /**
     * 兽医头像
     */
    @Schema(name = "doctorAvatar", description = "兽医头像")
    private String doctorAvatar;

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
     * 状态1正常，0删除
     */
    @Schema(name = "status", description = "状态1正常，0删除")
    private Integer status;

    public interface Save extends Default {
    }

    public interface Update extends Default {
    }

    public interface Delete extends Default {
    }

}
