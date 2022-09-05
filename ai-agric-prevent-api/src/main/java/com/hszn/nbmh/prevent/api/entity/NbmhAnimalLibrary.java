package com.hszn.nbmh.prevent.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 动物基因库/病例库
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
@TableName("nbmh_animal_library")
@ApiModel(value = "NbmhAnimalLibrary对象", description = "动物基因库/病例库")
public class NbmhAnimalLibrary implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Null(groups = {NbmhAnimalLibrary.Save.class})
    @NotBlank(groups = {NbmhAnimalLibrary.Update.class, NbmhAnimalLibrary.Delete.class})
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "主键id")
    private Long id;

    /**
     * 基因库/病例库文章编号
     */
    @Schema(name = "librarySn", description = "基因库/病例库文章编号")
    private String librarySn;

    /**
     * 基因库/病例库文章标题
     */
    @Schema(name = "title", description = "基因库/病例库文章标题")
    private String title;

    /**
     * 类型 0:基因库，1:病例库
     */
    @Schema(name = "libraryType", description = "类型 0:基因库，1:病例库")
    private Integer libraryType;

    /**
     * 基因库/病例库文章所属类目ID
     */
    @Schema(name = "categoryId", description = "基因库/病例库文章所属类目ID")
    private Integer categoryId;

    /**
     * 基因库/病例库文章宣传图片列表，采用JSON数组格式
     */
    @Schema(name = "gallery", description = "基因库/病例库文章宣传图片列表，采用JSON数组格式")
    private String gallery;

    /**
     * 基因库/病例库文章简介
     */
    @Schema(name = "libraryDesc", description = "基因库/病例库文章简介")
    private String libraryDesc;

    /**
     * 基因库/病例库文章详情
     */
    @Schema(name = "detail", description = "基因库/病例库文章详情")
    private String detail;

    /**
     * 基因库/病例库文章关键字，采用逗号间隔
     */
    @Schema(name = "keywords", description = "基因库/病例库文章关键字，采用逗号间隔")
    @TableField(condition = SqlCondition.LIKE)
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
     * 零售价格
     */
    @Schema(name = "libraryPrice", description = "零售价格")
    private BigDecimal libraryPrice;

    /**
     * 售卖总数
     */
    @Schema(name = "sellNum", description = "售卖总数")
    private Integer sellNum;

    /**
     * 基因库/病例库文章发表人ID
     */
    @Schema(name = "userId", description = "基因库/病例库文章发表人ID")
    private Long userId;

    /**
     * 基因库/病例库文章发表人姓名
     */
    @Schema(name = "userName", description = "基因库/病例库文章发表人姓名")
    private String userName;

    /**
     * 基因库/病例库文章发表人头像
     */
    @Schema(name = "userAvatar", description = "基因库/病例库文章发表人头像")
    private String userAvatar;

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
