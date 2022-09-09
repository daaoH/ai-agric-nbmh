package com.hszn.nbmh.prevent.api.params.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:15 22/09/08
 * @Modified By:
 */
@Schema(name="防疫统计信息")
@Data
public class StatistissParam implements Serializable {

    /**
     * 防疫站id
     */
    @Schema(name="preventStationId", description="防疫站id")
    private Long preventStationId;

    /**
     * 近一年
     */
    @Schema(name="oneYear", description="近一年")
    private boolean oneYear;

    /**
     * 近半年
     */
    @Schema(name="halfYear", description="近半年")
    private boolean halfYear;


}
