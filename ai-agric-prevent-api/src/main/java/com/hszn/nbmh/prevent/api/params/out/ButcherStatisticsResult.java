package com.hszn.nbmh.prevent.api.params.out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 自屠宰/无害化申报统计响应实体
 * </p>
 *
 * @author MCR
 * @since 2022-09-05
 */
@Schema(name = "自屠宰/无害化申报统计响应实体")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ButcherStatisticsResult implements Serializable {

    /**
     * 全镇屠宰数量
     */
    @Schema(name = "slaughterNum", description = "全镇屠宰数量")
    private int slaughterNum;

    /**
     * 某村屠宰数量
     */
    @Schema(name = "villageSlaughterNum", description = "某村屠宰数量")
    private int villageSlaughterNum;

    /**
     * 某镇无害化数量
     */
    @Schema(name = "harmlessNum", description = "某镇无害化数量")
    private int harmlessNum;

    /**
     * 某村无害化数量
     */
    @Schema(name = "villageHarmlessNum", description = "某村无害化数量")
    private int villageHarmlessNum;

}
