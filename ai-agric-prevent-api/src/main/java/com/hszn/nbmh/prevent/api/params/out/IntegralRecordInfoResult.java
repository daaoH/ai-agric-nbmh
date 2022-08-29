package com.hszn.nbmh.prevent.api.params.out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:15 22/8/24
 * @Modified By:
 */
@Schema(name="积分明细信息")
@Data
public class IntegralRecordInfoResult implements Serializable {

    /**
     * 总积分
     */
    @Schema(name="integral", description="总积分")
    private int totalIntegral;


    /**
     * 积分记录列表数据
     */
    @Schema(name="integralRecordResult", description="积分记录列表数据")
    private List<IntegralRecordResult> integralRecordResults;


}
