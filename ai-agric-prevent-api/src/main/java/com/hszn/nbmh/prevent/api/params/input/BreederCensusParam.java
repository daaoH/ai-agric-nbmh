package com.hszn.nbmh.prevent.api.params.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:15 22/9/2
 * @Modified By:
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Schema(name="防疫员端(农户详情-动物列表,防疫记录,信用抵押)+(动物信息统计)")
public class BreederCensusParam implements Serializable {

    /**
     * 请求类型,1动物列表,2防疫记录,3信用抵押
     */
    @Schema(name="requestType", description="请求类型,1动物列表,2防疫记录,3信用抵押")
    private Integer requestType;
    /**
     * 请求类型,1动物列表,2防疫记录,3信用抵押
     */
    @Schema(name="userId", description="农户id")
    private long userId;
    /**
     * 动物类型,0猪,3牛
     */
    @Schema(name="animalType", description="动物类型,0猪,3牛")
    private Integer animalType;
    /**
     * 动物类型,0猪,3牛
     */
    @Schema(name="DataTime", description="数据时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8", shape=JsonFormat.Shape.STRING)
    private Date DataTime;
    /**
     * 状态
     */
    @Schema(name="status", description="状态")
    private Integer status;
    /**
     * 耳标号
     */
    @Schema(name="earNo", description="耳标号")
    private String earNo;


}
