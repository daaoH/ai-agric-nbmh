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
 * 用户协议表
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
@TableName("nbmh_agreement")
@ApiModel(value = "NbmhAgreement对象", description = "用户协议")
public class NbmhAgreement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Null(groups = {NbmhAgreement.Save.class})
    @NotBlank(groups = {NbmhAgreement.Update.class, NbmhAgreement.Delete.class})
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "主键id")
    private Long id;

    /**
     * 协议类型 1:登录注册协议 2开店协议 3..
     */
    @Schema(name = "type", description = "协议类型 1:登录注册协议 2开店协议")
    private Integer type;

    /**
     * 协议内容
     */
    @Schema(name = "content", description = "协议内容")
    private String content;

    /**
     * 图片
     */
    @Schema(name = "pictures", description = "图片")
    private String pictures;

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
