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
 * 岗位信息表
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
public class SysPost implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 岗位ID
     */
    @Null(message = "新增数据时Id必须为null", groups = {Save.class})
    @NotNull(message = "更新或删除数据时Id不能为空", groups = {Update.class, Delete.class})
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "主键id")
    private Long id;

    /**
     * 岗位编码
     */
    @NotBlank(message = "新增数据时岗位编码postCode不能为空", groups = {Save.class})
    @Schema(name = "postCode", description = "岗位编码")
    private String postCode;

    /**
     * 岗位名称
     */
    @NotBlank(message = "新增数据时岗位名称postName不能为空", groups = {Save.class})
    @Schema(name = "name", description = "岗位名称")
    private String postName;

    /**
     * 岗位排序
     */
    @Schema(name = "postSort", description = "岗位排序")
    private Integer postSort;

    /**
     * 是否删除  -1：已删除  0：正常
     */
    @Schema(name = "status", description = "是否删除  -1：已删除  0：正常")
    private Integer status;

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
     * 更新时间
     */
    @Schema(name = "updateTime", description = "更新时间")
    private Date updateTime;

    /**
     * 更新人
     */
    @Schema(name = "updateBy", description = "更新人")
    private String updateBy;

    /**
     * 备注信息
     */
    @Schema(name = "remark", description = "备注信息")
    private String remark;

    public interface Save extends Default {
    }

    public interface Update extends Default {
    }

    public interface Delete extends Default {
    }

}
