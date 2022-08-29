package com.hszn.nbmh.prevent.api.params.out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:15 22/8/24
 * @Modified By:
 */
@Schema(name="积分明细")
@Data
public class IntegralRecordResult implements Serializable {


    /**
     * 农户头像
     */
    @Schema(name="userAvatarUrl", description="农户头像")
    private String userAvatarUrl;

    /**
     * 积分
     */
    @Schema(name="integral", description="积分")
    private int integral;


    /**
     * 描述
     */
    @Schema(name="describe", description="描述")
    private String describe;


}
