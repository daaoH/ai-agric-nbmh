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
 * 文章信息表
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
@TableName("nbmh_article")
@ApiModel(value = "NbmhArticle对象", description = "文章信息")
public class NbmhArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Null(groups = {NbmhArticle.Save.class})
    @NotBlank(groups = {NbmhArticle.Update.class, NbmhArticle.Delete.class})
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "主键id")
    private Long id;

    /**
     * 封面
     */
    @Schema(name = "showPic", description = "封面")
    private String showPic;

    /**
     * 标题
     */
    @Schema(name = "title", description = "标题")
    private String title;

    /**
     * 内容
     */
    @Schema(name = "content", description = "内容")
    private String content;

    /**
     * 是否显示
     */
    @Schema(name = "display", description = "是否显示")
    private Integer display;

    /**
     * 文章来源
     */
    @Schema(name = "src", description = "文章来源")
    private String src;

    /**
     * 文章类型 id
     */
    @Schema(name = "articleTypeId", description = "文章类型 id")
    private Long articleTypeId;

    /**
     * 推荐
     */
    @Schema(name = "recommend", description = "推荐")
    private Integer recommend;

    /**
     * 排序
     */
    @Schema(name = "sort", description = "排序")
    private Integer sort;

    /**
     * 用户 id
     */
    @Schema(name = "userId", description = "用户 id")
    private Long userId;

    /**
     * 点击量
     */
    @Schema(name = "clickCount", description = "点击量")
    private Long clickCount;

    /**
     * 描述
     */
    @Schema(name = "abstracts", description = "描述")
    private String abstracts;

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
