package com.hszn.nbmh.prevent.api.params.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hszn.nbmh.prevent.api.entity.NbmhEarRecord;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 耳标补打信息表--查询参数
 * </p>
 *
 * @author MCR
 * @since 2022-08-29
 */
@Data
public class NbmhEarRecordParam extends NbmhEarRecord implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 创建时间
     */
    @Schema(name = "createTimeParam", description = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8", shape = JsonFormat.Shape.STRING)
    private Date createTimeParam;

}
