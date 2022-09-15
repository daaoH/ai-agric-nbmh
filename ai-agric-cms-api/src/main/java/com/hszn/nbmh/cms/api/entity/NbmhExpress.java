package com.hszn.nbmh.cms.api.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.groups.Default;

/**
 * <p>
 * 快递公司表
 * </p>
 *
 * @author pyq
 * @since 2022-09-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("nbmh_express")
@ApiModel(value = "NbmhExpress对象", description = "快递公司")
public class NbmhExpress implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 快递公司id
     */
    @Null(groups = {NbmhExpress.Save.class})
    @NotNull(groups = {NbmhExpress.Update.class, NbmhExpress.Delete.class})
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "快递公司id")
    private Integer id;

    /**
     * 快递公司简称
     */
    @TableField(condition = SqlCondition.LIKE)
    @Schema(name = "code", description = "快递公司简称")
    private String code;

    /**
     * 快递公司全称
     */
    @TableField(condition = SqlCondition.LIKE)
    @Schema(name = "name", description = "快递公司全称")
    private String name;

    /**
     * 排序
     */
    @Schema(name = "sort", description = "排序")
    private Integer sort;

    /**
     * 是否显示
     */
    @Schema(name = "isShow", description = "是否显示")
    private Integer isShow;

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
     * 状态
     */
    @Schema(name = "status", description = "状态")
    private Integer status;

    public interface Save extends Default {
    }

    public interface Update extends Default {
    }

    public interface Delete extends Default {
    }

}
