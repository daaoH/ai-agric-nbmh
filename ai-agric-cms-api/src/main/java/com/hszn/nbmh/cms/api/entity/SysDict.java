package com.hszn.nbmh.cms.api.entity;

import java.util.Date;
import java.io.Serializable;

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

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author 李肖
 * @since 2022-08-31
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("sys_dict")
@ApiModel(value = "SysDict对象", description = "字典信息")
public class SysDict implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "主键id")
    private Long id;
    /**
     * 类型
     */
    @Schema(name = "type", description = "类型")
    private String type;
    /**
     * 描述
     */
    @Schema(name = "description", description = "描述")
    private String description;

    /**
     * 备注
     */
    @Schema(name = "remark", description = "备注")
    private String remark;

    /**
     * 是否是系统内置
     */
    @Schema(name = "systemFlag", description = "是否是系统内置")
    private String systemFlag;

    /**
     * 删除标记
     */
    @Schema(name = "status", description = "删除标记")
    private String status;

    /**
     * 创建时间
     */
    @Schema(name = "createTime", description = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @Schema(name = "createBy", description = "创建人")
    private String createBy;

    /**
     * 更新人
     */
    @Schema(name = "updateBy", description = "更新人")
    private String updateBy;

    /**
     * 更新时间
     */
    @Schema(name = "updateTime", description = "更新时间")
    private Date updateTime;

}
