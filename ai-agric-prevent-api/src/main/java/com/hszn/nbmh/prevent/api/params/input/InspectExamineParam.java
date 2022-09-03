package com.hszn.nbmh.prevent.api.params.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author wangjun
 * @since 2022-08-22
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Schema(name="积分记录请求参数信息")
public class InspectExamineParam {

    /**
     * 动物ids
     */
    @Schema(name="ids", description="动物ids")
    private List<Long> ids;


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
     * 防疫站id
     */
    @Schema(name="preventStationId", description="防疫站id")
    private Long preventStationId;
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
     * 启运地
     */
    @Schema(name="placeConsigned", description="启运地")
    private String placeConsigned;

    /**
     * 报备编号
     */
    @Schema(name="reportNumber", description="报备编号")
    private String reportNumber;

}
