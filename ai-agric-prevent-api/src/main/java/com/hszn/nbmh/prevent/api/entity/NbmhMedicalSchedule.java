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
 * 接诊时间设置
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
@TableName("nbmh_medical_schedule")
@ApiModel(value = "NbmhMedicalSchedule对象", description = "接诊时间设置")
public class NbmhMedicalSchedule implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Null(groups = {NbmhMedicalSchedule.Save.class})
    @NotBlank(groups = {NbmhMedicalSchedule.Update.class, NbmhMedicalSchedule.Delete.class})
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "主键id")
    private Long id;

    /**
     * 兽医用户id
     */
    @Schema(name = "userId", description = "兽医用户id")
    private Long userId;

    /**
     * 工作安排类型 0每天 1法定工作日 2指定日期
     */
    @Schema(name = "scheduleType", description = "工作安排类型 0每天 1法定工作日 2指定日期")
    private Integer scheduleType;

    /**
     * 接诊日期
     */
    @Schema(name = "medicalDate", description = "接诊日期")
    private Date medicalDate;

    /**
     * 上午接诊开始时间
     */
    @Schema(name = "morningStartTime", description = "上午接诊开始时间")
    private Date morningStartTime;

    /**
     * 上午接诊结束时间
     */
    @Schema(name = "morningEndTime", description = "上午接诊结束时间")
    private Date morningEndTime;

    /**
     * 下午接诊开始时间
     */
    @Schema(name = "pmStartTime", description = "下午接诊开始时间")
    private Date pmStartTime;

    /**
     * 下午接诊结束时间
     */
    @Schema(name = "pmEndTime", description = "下午接诊结束时间")
    private Date pmEndTime;

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
     * 状态 0正常 -1删除
     */
    @Schema(name = "status", description = "状态 0正常 -1删除")
    private Integer status;

    public interface Save extends Default {
    }

    public interface Update extends Default {
    }

    public interface Delete extends Default {
    }


}
