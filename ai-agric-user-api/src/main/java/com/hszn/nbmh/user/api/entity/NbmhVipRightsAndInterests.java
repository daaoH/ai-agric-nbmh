package com.hszn.nbmh.user.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * vip用户权益
 * </p>
 *
 * @author wangjun
 * @since 2022-09-15
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class NbmhVipRightsAndInterests implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
    @TableId(value="id", type=IdType.AUTO)
    @Schema(name="id", description="主键id")
    private Integer id;

    /**
     * 名称
     */
    @Schema(name="title", description="名称")
    private String title;

    /**
     * 介绍
     */
    @Schema(name="introduction", description="介绍")
    private String introduction;

    /**
     * 状态 0正常 -1删除
     */
    @Schema(name="status", description="状态 0正常 -1删除")
    private Integer status;

    /**
     * 创建日期
     */
    @Schema(name="createTime", description="创建日期")
    private Date createTime;

    /**
     * 更新日期
     */
    @Schema(name="updateTime", description="更新日期")
    private Date updateTime;


    /**
     *
     */
    @Schema(name="vipPrices", description="权益明细数据集")
    @TableField(exist=false)
    private List<NbmhVipPrice> vipPrices;


}
