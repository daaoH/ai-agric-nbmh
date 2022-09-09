package com.hszn.nbmh.prevent.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 处方药品列表
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
@TableName("nbmh_prescription_drug")
@ApiModel(value = "NbmhPrescriptionDrug对象", description = "处方药品")
public class NbmhPrescriptionDrug implements Serializable {

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
     * 处方基础信息id
     */
    @NotNull(message = "新增数据时处方基础信息prescriptionId不能为空", groups = {Save.class})
    @Schema(name = "prescriptionId", description = "处方基础信息id")
    private Long prescriptionId;

    /**
     * 药品Id
     */
    @NotNull(message = "新增数据时药品drugId不能为空", groups = {Save.class})
    @Schema(name = "drugId", description = "药品名称")
    private Long drugId;

    /**
     * 药品名称
     */
    @NotBlank(message = "新增数据时药品名称drugName不能为空", groups = {Save.class})
    @Schema(name = "drugName", description = "药品名称")
    private String drugName;

    /**
     * 药品规格
     */
    @Schema(name = "drugFormat", description = "药品规格")
    private String drugFormat;

    /**
     * 药品单价
     */
    @Schema(name = "drugUnitPrice", description = "药品单价")
    private Double drugUnitPrice;

    /**
     * 药品数量
     */
    @Schema(name = "drugAmount", description = "药品数量")
    private Integer drugAmount;

    /**
     * 药品使用说明
     */
    @Schema(name = "drugUseIntro", description = "药品使用说明")
    private String drugUseIntro;

    /**
     * 备注
     */
    @Schema(name = "remark", description = "备注")
    private String remark;

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
     * 状态 0正常 -1停止
     */
    @Schema(name = "status", description = "状态 0正常 -1停止")
    private Integer status;

    public interface Save extends Default {
    }

    public interface Update extends Default {
    }

    public interface Delete extends Default {
    }

}
