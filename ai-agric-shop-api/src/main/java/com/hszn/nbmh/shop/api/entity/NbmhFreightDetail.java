package com.hszn.nbmh.shop.api.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 运费模板详细信息
 * </p>
 *
 * @author yuan
 * @since 2022-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NbmhFreightDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 运费模板ID
     */
    private Long freightTemplateId;

    /**
     * 地址
     */
    private String area;

    /**
     * 地区ID
     */
    private String areaId;

    /**
     * 续重/续件
     */
    private BigDecimal continueUnit;

    /**
     * 续费
     */
    private BigDecimal continuePrice;

    /**
     * 首重/首件
     */
    private BigDecimal firstUnit;

    /**
     * 运费
     */
    private BigDecimal firstPrice;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 状态 0正常　-1删除
     */
    private Integer status;


}
