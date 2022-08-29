package com.hszn.nbmh.prevent.api.params.out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:15 22/8/17
 * @Modified By:
 */
@Schema(name="动物统计信息响应信息")
@Data
public class StatisticsResult implements Serializable {

    /**
     * 存栏数量
     */
    @Schema(name="enclosureNum", description="存栏数量")
    private int enclosureNum;

    /**
     * 免疫数量
     */
    @Schema(name="immuneNum", description="免疫数量")
    private int immuneNum;

    /**
     * 未免疫数量
     */
    @Schema(name="notImmuneNum", description="未免疫数量")
    private int notImmuneNum;

    /**
     * 屠宰数量
     */
    @Schema(name="slaughterNum", description="屠宰数量")
    private int slaughterNum;

    /**
     * 无害化数量
     */
    @Schema(name="harmlessNum", description="无害化数量")
    private int harmlessNum;

    /**
     * 检疫数量
     */
    @Schema(name="quarantineNum", description="检疫数量")
    private int quarantineNum;


    /**
     * 总数
     */
    @Schema(name="total", description="总数")
    private int total;
}
