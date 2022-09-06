package com.hszn.nbmh.prevent.api.params.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:15 22/8/16
 * @Modified By:
 */
@Schema(name="防疫信息")
@Data
public class TradeReportPageParam implements Serializable {

    /**
     * 农户id
     */
    @Schema(name="farmerId", description="农户id")
    private long farmerId;

    /**
     * 商贩id
     */
    @Schema(name="buyerId", description="商贩id")
    private long buyerId;

    /**
     * 动物类型
     */
    @Schema(name="animalType", description="动物类型")
    private Integer animalType;

}
