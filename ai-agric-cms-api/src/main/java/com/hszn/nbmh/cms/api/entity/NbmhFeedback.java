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

import javax.validation.groups.Default;

/**
 * <p>
 * 意见反馈表
 * </p>
 *
 * @author pyq
 * @since 2022-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("nbmh_feedback")
@ApiModel(value = "NbmhFeedback对象", description = "意见反馈")
public class NbmhFeedback implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @Schema(name = "userId", description = "用户id")
    private Long userId;

    /**
     * 用户名称
     */
    @Schema(name = "userName", description = "用户名称")
    private String userName;

    /**
     * 用户头像
     */
    @Schema(name = "userAvata", description = "用户头像")
    private String userAvatar;

    /**
     * 手机号
     */
    @Schema(name = "mobile", description = "手机号")
    private String mobile;

    /**
     * 反馈类型
     */
    @Schema(name = "feedType", description = "反馈类型")
    private String feedType;

    /**
     * 反馈内容
     */
    @Schema(name = "content", description = "反馈内容")
    private String content;

    /**
     * 是否含有图片
     */
    @Schema(name = "hasPicture", description = "是否含有图片")
    private Boolean hasPicture;

    /**
     * 图片地址列表，采用JSON数组格式
     */
    @Schema(name = "picUrls", description = "图片地址列表")
    private String picUrls;

    /**
     * 用户ip地址
     */
    @Schema(name = "ip", description = "用户ip地址")
    private String ip;

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
