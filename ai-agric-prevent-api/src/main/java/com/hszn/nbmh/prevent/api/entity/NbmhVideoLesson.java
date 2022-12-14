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
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 视频课堂视频
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
@TableName("nbmh_video_lesson")
@ApiModel(value = "NbmhVideoLesson对象", description = "视频课堂视频")
public class NbmhVideoLesson implements Serializable {

    private static final long serialVersionUID = 1L;

    @Null(message = "新增数据时Id必须为null", groups = {Save.class})
    @NotNull(message = "更新或删除数据时Id不能为空", groups = {Update.class, Delete.class})
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "主键id")
    private Integer id;

    /**
     * 视频发布人用户id
     */
    @NotNull(message = "新增数据时视频发布人用户userId不能为空", groups = {Save.class})
    @Schema(name = "userId", description = "视频发布人用户id")
    private Long userId;

    /**
     * 视频发布人真实姓名
     */
    @NotBlank(message = "新增数据时协视频发布人真实姓名userName不能为空", groups = {Save.class})
    @Schema(name = "userName", description = "视频发布人真实姓名")
    private String userName;

    /**
     * 视频发布人头像
     */
    @NotBlank(message = "新增数据时视频发布人头像userAvatar不能为空", groups = {Save.class})
    @Schema(name = "userAvatar", description = "视频发布人头像")
    private String userAvatar;

    /**
     * 视频编号
     */
    @Schema(name = "videoSn", description = "视频编号")
    private String videoSn;

    /**
     * 视频标题
     */
    @NotBlank(message = "新增数据时视频标题videoTitle不能为空", groups = {Save.class})
    @Schema(name = "videoTitle", description = "视频标题")
    private String videoTitle;

    /**
     * 视频类型所属类目ID
     */
    @NotNull(message = "新增数据时视频类型所属类目categoryId不能为空", groups = {Save.class})
    @Schema(name = "categoryId", description = "视频类型所属类目ID")
    private Integer categoryId;

    /**
     * 视频封面
     */
    @NotBlank(message = "新增数据时视频封面videoCover不能为空", groups = {Save.class})
    @Schema(name = "videoCover", description = "视频封面")
    private String videoCover;

    /**
     * 视频地址
     */
    @NotBlank(message = "新增数据时视频地址videoUrl不能为空", groups = {Save.class})
    @Schema(name = "videoUrl", description = "视频地址")
    private String videoUrl;

    /**
     * 视频试看链接
     */
    @Schema(name = "tryLookLink", description = "视频试看链接")
    private String tryLookLink;

    /**
     * 视频简介
     */
    @NotBlank(message = "新增数据时视频简介videoDesc不能为空", groups = {Save.class})
    @Schema(name = "videoDesc", description = "视频简介")
    private String videoDesc;

    /**
     * 视频关键字，采用逗号间隔
     */
    @Schema(name = "keywords", description = "视频关键字，采用逗号间隔")
    private String keywords;

    /**
     * 是否上架
     */
    @Schema(name = "isOnSale", description = "是否上架")
    private Integer isOnSale;

    /**
     * 排序
     */
    @Schema(name = "sortOrder", description = "排序")
    private Integer sortOrder;

    /**
     * 视频价格
     */
    @Schema(name = "videoPrice", description = "视频价格")
    private BigDecimal videoPrice;

    /**
     * 售卖总数
     */
    @Schema(name = "sellNum", description = "售卖总数")
    private Integer sellNum;

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
     * 状态 0:审核中，1:正常，2:审核失败，-1逻辑删除
     */
    @Schema(name = "status", description = "状态 0:审核中，1:正常，2:审核失败，-1逻辑删除")
    private Integer status;

    /**
     * 审核情况说明
     */
    @Schema(name = "auditDesc", description = "审核情况说明")
    private String auditDesc;

    public interface Save extends Default {
    }

    public interface Update extends Default {
    }

    public interface Delete extends Default {
    }

}
