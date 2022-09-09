package com.hszn.nbmh.admin.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 系统角色表
 * </p>
 *
 * @author MCR
 * @since 2022-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SysRole implements Serializable {

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
     * 角色名称
     */
    @NotBlank(message = "新增数据时角色名称roleName不能为空", groups = {Save.class})
    @Schema(name = "roleName", description = "角色名称")
    private String roleName;

    /**
     * 角色CODE
     */
    @NotBlank(message = "新增数据时角色roleCode不能为空", groups = {Save.class})
    @Schema(name = "roleCode", description = "角色CODE")
    private String roleCode;

    /**
     * 角色描述
     */
    @Schema(name = "roleDesc", description = "角色描述")
    private String roleDesc;

    /**
     * 状态标识（0-正常,1-删除）
     */
    @Schema(name = "status", description = "状态标识（0-正常,1-删除）")
    private Integer status;

    /**
     * 创建时间
     */
    @Schema(name = "createTime", description = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @Schema(name = "updateTime", description = "修改时间")
    private Date updateTime;

    /**
     * 修改人
     */
    @Schema(name = "updateBy", description = "修改人")
    private String updateBy;

    /**
     * 创建人
     */
    @Schema(name = "createBy", description = "创建人")
    private String createBy;

    public interface Save extends Default {
    }

    public interface Update extends Default {
    }

    public interface Delete extends Default {
    }

}
