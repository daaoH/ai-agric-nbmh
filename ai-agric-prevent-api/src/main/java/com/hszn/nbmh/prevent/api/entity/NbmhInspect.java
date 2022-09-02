package com.hszn.nbmh.prevent.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 检疫 检擦
 * </p>
 *
 * @author wangjun
 * @since 2022-08-24
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(name="检疫对象")
public class NbmhInspect implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value="id", type=IdType.AUTO)
    @Schema(name="id", description="主键id")
    private Long id;

    /**
     * 检疫编号
     */
    @Schema(name="inspectNumble", description="检疫编号")
    private String inspectNumber;

    /**
     * 检疫证明
     */
    @Schema(name="inspectProveUrl", description="检疫证明")
    private String inspectProveUrl;

    /**
     * 耳标号
     */
    @Schema(name="earNo", description="耳标号")
    private String earNo;

    /**
     * 检疫人id
     */
    @Schema(name="vaccinId", description="检疫人id")
    private Long vaccinId;

    /**
     * 送检人
     */
    @Schema(name="submitBy", description="送检人")
    private String submitBy;

    /**
     * 送检人电话
     */
    @Schema(name="submitByPhone", description="送检人电话")
    private String submitByPhone;

    /**
     * 到达地
     */
    @Schema(name="destination", description="到达地")
    private String destination;

    /**
     * 启运地
     */
    @Schema(name="placeConsigned", description="启运地")
    private String placeConsigned;


    /**
     * 养殖户id
     */
    @Schema(name="userId", description="养殖户id")
    private Long userId;

    /**
     * 养殖户
     */
    @Schema(name="userName", description="养殖户")
    private String userName;

    /**
     * 养殖户头像
     */
    @Schema(name="userAvatarUrl", description="养殖户头像")
    private String userAvatarUrl;


    /**
     * 养殖户手机
     */
    @Schema(name="userPhone", description="养殖户手机")
    private String userPhone;

    /**
     * 动物id
     */
    @Schema(name="animalId", description="动物id")
    private Long animalId;

    /**
     * 动物状态 0正常 1已抵押 2自屠宰 3无公害 4检疫
     */
    @Schema(name="animalStatus", description="动物状态 0正常 1已抵押 2自屠宰 3无公害 4检疫")
    private int animalStatus;


    /**
     * 动物类型(种类 0猪 1牛)
     */
    @Schema(name="animalType", description="动物类型(种类 0猪 1牛)")
    private int animalType;

    /**
     * 创建时间
     */
    @Schema(name="createTime", description="创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @Schema(name="updateTime", description="修改时间")
    private Date updateTime;

    /**
     * 状态(1:,2:,3:,4:,5:)
     */
    @Schema(name="status", description="状态(1:未检疫,2:已检疫,3:数据失效,4:已举报,5:)")
    private int status;

    /**
     * 防疫站标识
     */
    @Schema(name="stationId", description="防疫站标识")
    private Long stationId;

    /**
     * 买家
     */
    @Schema(name="buyerName", description="买家")
    private String buyerName;

    /**
     * 买家电话
     */
    @Schema(name="buyerPhone", description="买家电话")
    private String buyerPhone;

    /**
     * 买家身份证
     */
    @Schema(name="buyerCard", description="买家身份证")
    private String buyerCard;


    /**
     * 报备编号
     */
    @Schema(name="reportNumber", description="报备编号")
    private String reportNumber;

    /**
     * 防疫站id
     */
    @Schema(name="preventStationId", description="防疫站id")
    private String preventStationId;

}
