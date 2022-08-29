package com.hszn.nbmh.prevent.api.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 养殖场动物JSON数据实体
 * </p>
 *
 * @author MCR
 * @since 2022-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AnimalJsonEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 动物类型
     */
    private Integer animalType;

    /**
     * 动物名称
     */
    private String animalName;

    /**
     * 动物数量
     */
    private Integer count;

}
