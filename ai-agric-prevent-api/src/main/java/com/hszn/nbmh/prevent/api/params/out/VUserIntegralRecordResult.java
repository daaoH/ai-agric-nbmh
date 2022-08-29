package com.hszn.nbmh.prevent.api.params.out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:15 22/8/23
 * @Modified By:
 */
@Schema(name="防疫员管理详情-积分头部统计对象")
@Data
public class VUserIntegralRecordResult {
    /**
     * 总积分
     */
    @Schema(name="totalIntegral", description="总积分")
    private int totalIntegral;
    /**
     * 年月
     */
    @Schema(name="yearsAndMonth", description="年月")
    private Date yearsAndMonth;

    /**
     * 总防疫数量
     */
    @Schema(name="totalVaccinNum", description="总防疫数量")
    private int totalVaccinNum;


    /**
     * 积分获取列表对象集合
     */
    @Schema(name="vUserIntegralRecordListResultList", description="积分获取列表对象集合")
    private List<VUserIntegralRecordListResult> vUserIntegralRecordListResultList;
}
