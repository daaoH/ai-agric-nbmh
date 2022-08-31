package com.hszn.nbmh.user.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 兽医详细信息表
 * </p>
 *
 * @author MCR
 * @since 2022-08-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class NbmhAnimalDoctorDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 兽医用户id
     */
    private Long userId;

    /**
     * 累计接诊次数
     */
    private Integer admissions;

    /**
     * 兽医类型0:非官方，1:官方
     */
    private Integer doctorType;

    /**
     * 擅长畜种0:猪，1:牛
     */
    private Integer goodAnimalType;

    /**
     * 擅长病症
     */
    private String goodDisease;

    /**
     * 兽医证编号
     */
    private String certificateNo;

    /**
     * 视频问诊金额
     */
    private BigDecimal videoMedicalPrice;

    /**
     * 会诊金额
     */
    private BigDecimal medicalPrice;

    /**
     * 上门问诊金额
     */
    private BigDecimal doorMedicalPrice;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 状态 0正常 -1冻结 -2离职
     */
    private Integer status;


}
