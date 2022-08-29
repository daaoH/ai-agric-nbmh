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
@Schema(name="防疫记录请求参数信息")
@Data
public class VaccinRecordParam implements Serializable {


    /**
     * 农畜耳标号
     */
    @Schema(name="earNo", description="农畜耳标号")
    private String earNo;

    /**
     * 抵押(0否 1是)
     */
    @Schema(name="isMortgage", description="抵押(0否 1是)")
    private Integer isMortgage;

    /**
     * 抵押(0否 1是)
     */
    @Schema(name="isPrevent", description="抵押(0否 1是)")
    private Integer isPrevent;

    /**
     * 种类 0猪 1牛
     */
    @Schema(name="type", description="种类 0猪 1牛")
    private Integer type;

    /**
     * 检疫人员
     */
    @Schema(name="vaccinUser", description="检疫人员")
    private String vaccinUser;

    /**
     * 添加时间(年-月-日)
     */
    @Schema(name="createTime", description="添加时间(年-月-日)")
    private String createTime;

}
