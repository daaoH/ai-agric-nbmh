package com.hszn.nbmh.cms.api.entity;

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
 * 广告表
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
@TableName("nbmh_ad")
@ApiModel(value = "NbmhAd对象", description = "广告信息")
public class NbmhAd implements Serializable {

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
     * 广告标题
     */
    @NotBlank(message = "新增数据时广告标题title不能为空", groups = {Save.class})
    @Schema(name = "title", description = "广告标题")
    private String title;

    /**
     * 区域码　不同的区域码表示不同的省市县
     */
    @Schema(name = "areaCode", description = "区域码　不同的区域码表示不同的省市县")
    private String areaCode;

    /**
     * 所广告的商品页面或者活动页面链接地址
     */
    @Schema(name = "link", description = "所广告的商品页面或者活动页面链接地址")
    private String link;

    /**
     * 广告宣传图片
     */
    @NotBlank(message = "新增数据时广告宣传图片url不能为空", groups = {Save.class})
    @Schema(name = "url", description = "广告宣传图片")
    private String url;

    /**
     * 广告位置
     */
    @Schema(name = "position", description = "广告位置")
    private Integer position;

    /**
     * 板块类型 1.兽医板块；2:
     */
    @Schema(name = "modelType", description = "板块类型 1.兽医板块；2:")
    private Integer modelType;

    /**
     * 活动内容
     */
    @Schema(name = "content", description = "活动内容")
    private String content;

    /**
     * 广告开始时间
     */
    @Schema(name = "startTime", description = "广告开始时间")
    private Date startTime;

    /**
     * 广告结束时间
     */
    @Schema(name = "endTime", description = "广告结束时间")
    private Date endTime;

    /**
     * 是否启动
     */
    @Schema(name = "enabled", description = "是否启动")
    private Integer enabled;

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

    /**
     * 广告类型 1:启动页 2。banner
     */
    @Schema(name = "type", description = "广告类型 1:启动页 2。banner")
    private Integer type;

    /**
     * 显示形式 1:常规展示 2:弹出
     */
    @Schema(name = "style", description = "显示形式 1:常规展示 2:弹出")
    private Integer style;

    public interface Save extends Default {
    }

    public interface Update extends Default {
    }

    public interface Delete extends Default {
    }

}
