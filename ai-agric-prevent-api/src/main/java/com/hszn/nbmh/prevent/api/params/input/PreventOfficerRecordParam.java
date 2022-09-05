package com.hszn.nbmh.prevent.api.params.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:15 22/9/5
 * @Modified By:
 */
@Schema(name="防疫员防疫记录请求参数信息")
@Data
public class PreventOfficerRecordParam implements Serializable {

    /**
     * 防疫站id
     */
    @Schema(name="preventStationId", description="防疫站id")
    private Long preventStationId;

    /**
     * 防疫员id
     */
    @Schema(name="vaccinUserId", description="防疫员id")
    private Long vaccinUserId;


    /**
     * 防疫时间
     */
    @Schema(name="vaccinDate", description="防疫时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8", shape=JsonFormat.Shape.STRING)
    private Date vaccinDate;

}
