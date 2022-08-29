package com.hszn.nbmh.user.api.params.input;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.groups.Default;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 防疫站信息表
 * </p>
 *
 * @author MCR
 * @since 2022-08-15
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
@ApiModel(value="NbmhPreventStation对象", description="防疫站信息")
public class NbmhPreventStationParam implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
    @Schema(name="id", description="主键id")
    private Long id;

    /**
     * 防疫站名称
     */
    @Schema(name="stationName", description="防疫站名称")
    private String stationName;

    /**
     * 防疫站地址
     */
    @Schema(name="stationAddress", description="防疫站地址")
    private String stationAddress;

    /**
     * 经度
     */
    @Schema(name="stationLongitude", description="经度")
    private String stationLongitude;

    /**
     * 纬度
     */
    @Schema(name="stationLatitude", description="纬度")
    private String stationLatitude;

    /**
     * 联系电话
     */
    @Schema(name="stationPhone", description="联系电话")
    private String stationPhone;

    /**
     * 站长
     */
    @Schema(name="stationMaster", description="站长")
    private String stationMaster;

    /**
     * 站长id
     */
    @Schema(name="masterId", description="站长id")
    private Long masterId;

    /**
     * 证件
     */
    @Schema(name="certificate", description="证件")
    private String certificate;

    /**
     * 创建时间
     */
    @Schema(name="createTime", description="创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @Schema(name="updateTime", description="更新时间")
    private Date updateTime;

    /**
     * 状态 0正常 -1停止
     */
    @Schema(name="status", description="状态 0正常 -1停止")
    private Integer status;

    public interface Save extends Default {
    }

    public interface Update extends Default {
    }

    public interface Delete extends Default {
    }

}
