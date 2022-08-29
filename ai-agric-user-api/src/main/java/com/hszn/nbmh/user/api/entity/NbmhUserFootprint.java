package com.hszn.nbmh.user.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户浏览足迹表
 * </p>
 *
 * @author yuan
 * @since 2022-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NbmhUserFootprint implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户表的用户ID
     */
    private Long userId;

    /**
     * 浏览商品ID
     */
    private Long goodsId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 状态 0正常 -1删除
     */
    private Boolean stats;


}
