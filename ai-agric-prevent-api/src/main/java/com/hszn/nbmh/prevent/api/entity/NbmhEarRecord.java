package com.hszn.nbmh.prevent.api.entity;

import com.baomidou.mybatisplus.annotation.*;
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
 * 耳标补打信息表
 * </p>
 *
 * @author MCR
 * @since 2022-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("nbmh_ear_record")
@ApiModel(value = "NbmhEarRecord对象", description = "耳标补打信息")
public class NbmhEarRecord implements Serializable {

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
     * 动物id
     */
    @NotNull(message = "新增数据时动物animalId不能为空", groups = {Save.class})
    @Schema(name = "animalId", description = "动物id")
    private Long animalId;

    /**
     * 耳标编号
     */
    @NotBlank(message = "新增数据时动物耳标编号earNo不能为空", groups = {Save.class})
    @Schema(name = "earNo", description = "耳标编号")
    @TableField(condition = SqlCondition.LIKE)
    private String earNo;

    /**
     * 动物头像
     */
    @Schema(name = "animalPic", description = "动物头像")
    private String animalPic;

    /**
     * 动物月龄
     */
    @Schema(name = "animalAge", description = "动物月龄")
    private Integer animalAge;

    /**
     * 动物体重
     */
    @Schema(name = "animalWeight", description = "动物体重")
    private Float animalWeight;

    /**
     * 防疫员id
     */
    @NotNull(message = "新增数据时防疫员userId不能为空", groups = {Save.class})
    @Schema(name = "userId", description = "防疫员id")
    private Long userId;

    /**
     * 防疫员
     */
    @NotBlank(message = "新增数据时防疫员userName不能为空", groups = {Save.class})
    @Schema(name = "userName", description = "防疫员")
    private String userName;

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
