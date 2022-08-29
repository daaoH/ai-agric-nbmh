package com.hszn.nbmh.prevent.api.params.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    @Schema(name="vaccinId", description="防疫-检疫人-id")
    private Long vaccinId;

    /**
     * 是否为收入(0:false,1:true)
     */
    @Schema(name="isIncome", description="是否为收入(0:false,1:true)")
    private Integer isIncome;


    /**
     * 年月
     */
    @Schema(name="yearsAndMonth", description="年月")
    private Date yearsAndMonth;


    /**
     * 动物类型(种类 0猪 1牛)
     */
    @Schema(name="animalType", description="动物类型(种类 0猪 1牛)")
    private Integer animalType;

}
