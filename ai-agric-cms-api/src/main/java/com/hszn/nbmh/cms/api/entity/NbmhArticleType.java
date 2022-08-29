package com.hszn.nbmh.cms.api.entity;

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
 * 文章类型表
 * </p>
 *
 * @author MCR
 * @since 2022-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("nbmh_article_type")
@ApiModel(value = "NbmhArticleType对象", description = "文章类型")
public class NbmhArticleType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 自增
     */
    @Null(groups = {NbmhArticleType.Save.class})
    @NotBlank(groups = {NbmhArticleType.Update.class, NbmhArticleType.Delete.class})
    @TableId(value = "type_id", type = IdType.AUTO)
    @Schema(name = "type_id", description = "主键")
    private Long typeId;

    /**
     * 类型名称
     */
    @Schema(name = "typeName", description = "类型名称")
    private String typeName;

    /**
     * 排序
     */
    @Schema(name = "sort", description = "排序")
    private Integer sort;

    /**
     * 描述
     */
    @Schema(name = "descs", description = "描述")
    private String descs;

    /**
     * 文章类型父 id
     */
    @Schema(name = "parentId", description = "文章类型父 id")
    private Long parentId;

    /**
     * 添加时间
     */
    @Schema(name = "createTime", description = "添加时间")
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
