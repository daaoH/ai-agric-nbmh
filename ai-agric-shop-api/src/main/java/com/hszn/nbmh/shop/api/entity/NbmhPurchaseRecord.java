package com.hszn.nbmh.shop.api.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 采购记录表
 * </p>
 *
 * @author yuan
 * @since 2022-08-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NbmhPurchaseRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 发布id
     */
    private Long purchId;

    /**
     * 发布者id
     */
    private Long userId;

    /**
     * 报价者id
     */
    private Long quoteId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 最终成交价
     */
    private BigDecimal price;


}
