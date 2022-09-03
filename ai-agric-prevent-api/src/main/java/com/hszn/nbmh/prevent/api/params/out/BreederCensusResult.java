package com.hszn.nbmh.prevent.api.params.out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:15 22/9/2
 * @Modified By:
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Schema(name="防疫员端(农户详情-动物列表,防疫记录,信用抵押)+(动物信息统计)")
public class BreederCensusResult<T> implements Serializable {

    /**
     * 统计数据
     */
    @Schema(name="statisticsResult", description="统计数据")
    private StatisticsResult statisticsResult;

    /**
     * 数据集合
     */
    @Schema(name="userId", description="数据集合")
    private List<T> list;

}
