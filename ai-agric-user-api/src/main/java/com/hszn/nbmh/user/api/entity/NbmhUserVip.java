package com.hszn.nbmh.user.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户vip价格绑定表
 * </p>
 *
 * @author wangjun
 * @since 2022-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NbmhUserVip implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 有效期(月,年)描述
     */
    private String validDate;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 权益id
     */
    private Long vipRightsAndInterestsId;

    /**
     * 权益明细id
     */
    private Long vipPriceId;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 更新日期
     */
    private Date updateTime;

    /**
     * 状态 0:有效  1无效
     */
    private Integer status;


}
