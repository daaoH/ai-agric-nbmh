package com.hszn.nbmh.prevent.api.params.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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
public class UserIntegralRecordParam {

    /**
     * 防疫-检疫人-id
     */
    @Schema(name="vaccinUserId", description="防疫-检疫人-id")
    private Long vaccinUserId;

    /**
     * 防疫站id
     */
    @Schema(name="preventStationId", description="防疫站id")
    private Long preventStationId;

    /**
     * 是否为收入(0:false,1:true)
     */
    @Schema(name="isIncome", description="是否为收入(0:false,1:true)")
    private Integer isIncome;


    /**
     * 年月
     */
    @Schema(name="yearsAndMonth", description="年月")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8", shape=JsonFormat.Shape.STRING)
    private Date yearsAndMonth;


    /**
     * 动物类型(种类 0猪 1牛)
     */
    @Schema(name="animalType", description="动物类型(种类 0猪 1牛)")
    private Integer animalType;



    /**
     * 来源(1:防疫,2:检疫,3:分账)
     */
    @Schema(name="source", description="来源(1:邀请 2:防疫,3:检疫,3:分账)")
    private Integer source;

}
