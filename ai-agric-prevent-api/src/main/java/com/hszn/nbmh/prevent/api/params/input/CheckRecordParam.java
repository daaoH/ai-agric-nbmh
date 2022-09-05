package com.hszn.nbmh.prevent.api.params.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:15 22/8/25
 * @Modified By:
 */

@Data
@Schema(name="稽查输入参数信息")
public class CheckRecordParam {

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
     * 举报时间
     */
    @Schema(name="checkDate", description="检疫时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8", shape=JsonFormat.Shape.STRING)
    private Date checkDate;

}
