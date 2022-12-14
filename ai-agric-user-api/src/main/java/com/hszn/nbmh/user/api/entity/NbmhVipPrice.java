package com.hszn.nbmh.user.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户vip价格对照表
 * </p>
 *
 * @author wangjun
 * @since 2022-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NbmhVipPrice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 等级
     */
    private String level;

    /**
     * 名称
     */
    private String title;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 计算值
     */
    private String value;

    /**
     * 介绍
     */
    private String introduction;

    /**
     * 单元(例:个,天,次 )
     */
    private String unit;

    /**
     * 类型 1:次(每次活动给的个数),2:天,3:月 4:年,5:%(百分比保留两位)6:个(人数)7:套(模板)
     */
    private Integer type;

    /**
     * 有效期
     */
    private String validDate;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 更新日期
     */
    private Date updateTime;

    /**
     * 状态 0正常 -1删除
     */
    private Integer status;

    /**
     * 权益id
     */
    private Long vipRightsAndInterestsId;

    /**
     * 排序值 默认0
     */
    private Integer sort;


}
