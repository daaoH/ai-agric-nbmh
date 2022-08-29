package com.hszn.nbmh.third.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 快递物流踪迹信息实体
 * </p>
 *
 * @author MCR
 * @since 2022-08-26
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Traces implements Serializable {

    /**
     *当前状态(同StateEx)
     */
    private String Action;

    /**
     *描述
     */
    private String AcceptStation;

    /**
     *时间
     */
    private String AcceptTime;

    /**
     *所在城市
     */
    private String Location;

    /**
     *备注
     */
    private String Remark;

}
