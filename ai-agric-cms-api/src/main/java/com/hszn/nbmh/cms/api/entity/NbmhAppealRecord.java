package com.hszn.nbmh.cms.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.groups.Default;

/**
 * <p>
 * 申诉记录表
 * </p>
 *
 * @author pyq
 * @since 2022-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("nbmh_appeal_record")
@ApiModel(value = "NbmhAppealRecord对象", description = "申诉记录")
public class NbmhAppealRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Null(groups = {NbmhAppealRecord.Save.class})
    @NotBlank(groups = {NbmhAppealRecord.Update.class, NbmhAppealRecord.Delete.class})
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "主键id")
    private Integer id;

    /**
     * 类型 
     */
    @Schema(name = "type", description = "类型")
    private Integer type;

    /**
     * 标题
     */
    @Schema(name = "title",description = "标题")
    private String title;

    /**
     * 内容
     */
    @Schema(name = "content", description = "申诉内容")
    private String content;

    /**
     * 创建时间
     */
    @Schema(name = "createTime", description = "创建时间")
    private Date createTime;

    /**
     * 收到的回复
     */
    @Schema(name = "replyContent", description = "收到的回复")
    private String replyContent;

    /**
     * 更新时间
     */
    @Schema(name = "updateTime", description = "更新时间")
    private Date updateTime;

    /**
     * 状态 0申诉中 1申诉结束 2取消　-1删除
     */
    @Schema(name = "status", description = "状态 0申诉中 1申诉结束 2取消　-1删除")
    private Integer status;

    public interface Save extends Default {
    }

    public interface Update extends Default {
    }

    public interface Delete extends Default {
    }
    /**
     * 用户id
     */
    @Schema(name = "userid", description = "用户id")
    private Long userId;


}
