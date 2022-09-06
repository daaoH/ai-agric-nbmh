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
 * 动物诊疗接单记录
 * </p>
 *
 * @author MCR
 * @since 2022-08-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("nbmh_medical_accept")
@ApiModel(value = "NbmhMedicalAccept对象", description = "动物诊疗接单记录")
public class NbmhMedicalAccept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Null(groups = {NbmhMedicalAccept.Save.class})
    @NotBlank(groups = {NbmhMedicalAccept.Update.class, NbmhMedicalAccept.Delete.class})
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "主键id")
    private Long id;

    /**
     * 诊断订单编号
     */
    @Schema(name = "medicalOrderNumber", description = "诊断订单编号")
    private String medicalOrderNumber;

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
     * 兽医职称
     */
    @Schema(name = "doctorJobTags", description = "兽医职称")
    private String doctorJobTags;

    /**
     * 工作年限
     */
    @Schema(name = "workYear", description = "工作年限")
    private String workYear;

    /**
     * 技能说明
     */
    @Schema(name = "skillDesc", description = "技能说明")
    private String skillDesc;

    /**
     * 会诊时间
     */
    @Schema(name = "acceptOrderTime", description = "会诊时间")
    private Date acceptOrderTime;

    /**
     * 是否为诊疗会议管理员0:否，1:是
     */
    @Schema(name = "meetingAdminStatus", description = "是否为诊疗会议管理员0:否，1:是")
    private Integer meetingAdminStatus;

    /**
     * 状态-1:已拒绝，0:待会诊，1:会诊中，2:待评价，3:待支付，4:已结束
     */
    @Schema(name = "orderStatus", description = "状态-1:已拒绝，0:待会诊，1:会诊中，2:待评价，3:待支付，4:已结束")
    private Integer orderStatus;

    /**
     * 拒绝原因
     */
    @Schema(name = "rejectReason", description = "拒绝原因")
    private String rejectReason;

    /**
     * 诊断结果
     */
    @Schema(name = "medicalResult", description = "诊断结果")
    private String medicalResult;

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
     * 状态 1正常，0删除
     */
    @Schema(name = "status", description = "状态 1正常，0删除")
    private Integer status;

    public interface Save extends Default {
    }

    public interface Update extends Default {
    }

    public interface Delete extends Default {
    }

}
