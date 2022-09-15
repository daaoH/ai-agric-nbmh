package com.hszn.nbmh.cms.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 违规记录表
 * </p>
 *
 * @author 李肖
 * @since 2022-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("nbmh_violation_record")
@ApiModel(value = "NbmhViolationRecord对象", description = "违规记录表")
public class NbmhViolationRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "主键id")
    private Long id;

    /**
     * 用户id
     */
    @Schema(name = "userId", description = "用户id")
    private Long userId;

    /**
     * 类型
     */
    @Schema(name = "type", description = "类型")
    private Integer type;

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
     * 状态 0正常　-1删除
     */
    @Schema(name = "status", description = "状态")
    private Integer status;


}
