package com.hszn.nbmh.good.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author yuan
 * @since 2022-08-25
 */
@Schema(description = "用户评论")
@Data
@EqualsAndHashCode(callSuper = false)
public class NbmhComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 如果是商品评论则表示商品id；如果是type=1，则是其他评论事物的id
     */
    @Schema(description = "评论商品id")
    private Long thingId;

    /**
     * 评论类型，type=0，则是商品评论；如果是type=1，其他的评论；
     */
    @Schema(description = "评论类型")
    private Integer type;

    /**
     * 评论内容
     */
    @Schema(description = "评论内容")
    private String content;

    /**
     * 管理员回复内容
     */
    @Schema(description = "管理员回复内容")
    private String adminContent;

    /**
     * 用户表的用户ID
     */
    @Schema(description = "用户ID")
    private Long userId;

    /**
     * 评论用户名称
     */
    @Schema(description = "评论用户名称")
    private String userName;

    /**
     * 头像
     */
    @Schema(description = "头像")
    private String userAvar;

    /**
     * 城市及ip地址
     */
    @Schema(description = "城市及ip地址")
    private String ip;

    /**
     * 是否含有图片
     */
    @Schema(description = "是否含有图片")
    private Boolean hasPicture;

    /**
     * 图片地址列表，采用JSON数组格式
     */
    @Schema(description = "图片")
    private String picUrls;

    /**
     * 评分， 1-5
     */
    @Schema(description = "评分， 1-5")
    private Integer star;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private Date updateTime;

    /**
     * 状态 0正常 -1删除
     */
    @Schema(description = "状态 0正常 -1删除")
    private Boolean status;


}
