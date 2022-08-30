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
 * 报价表
 * </p>
 *
 * @author yuan
 * @since 2022-08-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NbmhQuoteRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 报价用户id
     */
    private Long userId;

    /**
     * 采购需求id
     */
    private Long purchId;

    /**
     * 报价商品id
     */
    private Long goodId;

    /**
     * 规格id
     */
    private Integer specsId;

    /**
     * 报价金额
     */
    private BigDecimal quotePrice;

    /**
     * 发货地
     */
    private String deliverAddress;

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


}
