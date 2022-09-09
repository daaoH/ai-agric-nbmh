package com.hszn.nbmh.prevent.api.params.out;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:15 22/09/09
 * @Modified By:
 */
@Schema(name="站长 防疫统计")
@Data
public class VaccinStatisticsResult {


    @Schema(name="userCount", description="养殖户人数")
    private Long userCount;

    @Schema(name="animalCount", description="免疫接种总数量")
    private Long animalCount;

    @Schema(name="butcherCount", description="自屠宰数量")
    private Long butcherCount;

    @Schema(name="harmlessCount", description="无害化数量")
    private Long harmlessCount;

    @Schema(name="categories", description="时间集合列表")
    private List<String> categories;

    @Schema(name="series", description="数据集合列表")
    private List<MapAndDataResult> series;

}
