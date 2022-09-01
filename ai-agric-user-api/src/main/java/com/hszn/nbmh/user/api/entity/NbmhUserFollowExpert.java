package com.hszn.nbmh.user.api.entity;

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
 * 关注的专家记录表
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
@TableName("nbmh_user_follow_expert")
@ApiModel(value = "NbmhUserFollowExpert对象", description = "关注的专家记录")
public class NbmhUserFollowExpert implements Serializable {

    private static final long serialVersionUID = 1L;

    @Null(groups = {NbmhUserFollowExpert.Save.class})
    @NotBlank(groups = {NbmhUserFollowExpert.Update.class, NbmhUserFollowExpert.Delete.class})
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "主键id")
    private Integer id;

    /**
     * 养殖户id
     */
    @Schema(name = "userId", description = "养殖户id")
    private Long userId;

    /**
     * 养殖户真实姓名
     */
    @Schema(name = "userName", description = "养殖户真实姓名")
    private String userName;

    /**
     * 专家用户id
     */
    @Schema(name = "expertId", description = "专家用户id")
    private Long expertId;

    /**
     * 专家真实姓名
     */
    @Schema(name = "expertName", description = "专家真实姓名")
    private String expertName;

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
     * 状态：1:已关注，2：取消关注
     */
    @Schema(name = "status", description = "状态：1:已关注，2：取消关注")
    private Integer status;

    public interface Save extends Default {
    }

    public interface Update extends Default {
    }

    public interface Delete extends Default {
    }

}
