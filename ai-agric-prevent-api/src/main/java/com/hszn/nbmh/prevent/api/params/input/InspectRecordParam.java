package com.hszn.nbmh.prevent.api.params.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author wangjun
 * @since 2022-08-22
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Schema(name="防疫员-检疫记录")
public class InspectRecordParam {


    /**
     * 检疫时间
     */
    @Schema(name="inspectDate", description="检疫时间")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date inspectDate;

    /**
     * 养殖户
     */
    @Schema(name="userName", description="养殖户")
    private String userName;

    /**
     * status
     */
    @Schema(name="status", description="状态(1:未检疫,2:已检疫,3:数据失效,4:已举报,5:)")
    private int status;

    /**
     * 动物类型(种类 0猪 1牛)
     */
    @Schema(name="animalType", description="动物类型(种类 0猪 1牛)")
    private int animalType;


    /**
     * 养殖户id
     */
    @Schema(name="userId", description="养殖户id")
    private Long userId;

    /**
     * 检疫编号
     */
    @Schema(name="reportNumber", description="检疫编号")
    private String reportNumber;


}