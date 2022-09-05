package com.hszn.nbmh.prevent.api.params.out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 防疫站工作人员根据记录ID查询屠宰/无害化申报信息 响应实体
 * </p>
 *
 * @author MCR
 * @since 2022-09-05
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class NbmhButcherReportDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 防疫站id
     */
    @Schema(name = "preventStationId", description = "防疫站id")
    private Long preventStationId;

    /**
     * 养殖户id
     */
    @Schema(name = "farmerId", description = "养殖户id")
    private Long farmerId;

    /**
     * 养殖户名称
     */
    @Schema(name = "farmerName", description = "养殖户名称")
    private String farmerName;

    /**
     * 养殖户电话
     */
    @Schema(name = "farmerPhone", description = "养殖户电话")
    private String farmerPhone;

    /**
     * 养殖户规模
     */
    @Schema(name = "farmerType", description = "养殖户规模")
    private String farmerType;

    /**
     * 养殖户身份证号码
     */
    @Schema(name = "farmerCard", description = "养殖户身份证号码")
    private String farmerCard;

    /**
     * 养殖户头像
     */
    @Schema(name = "farmerAvatar", description = "养殖户头像")
    private String farmerAvatar;

    /**
     * 养殖户地址
     */
    @Schema(name = "farmerAddress", description = "养殖户地址")
    private String farmerAddress;

    /**
     * 报告类型 0自屠宰报备 1无公害报备
     */
    @Schema(name = "reportType", description = "报告类型 0自屠宰报备 1无公害报备")
    private Integer reportType;

    /**
     * 动物列表
     */
    List<NbmhButcherReportAnimal> animalList;

}
