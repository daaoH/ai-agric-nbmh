package com.hszn.nbmh.prevent.api.params.out;

import com.hszn.nbmh.prevent.api.entity.NbmhAnimal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:15 22/8/16
 * @Modified By:
 */
@Schema(name="动物信息响应信息")
@Data
public class AnimalResult extends NbmhAnimal implements Serializable {

    /**
     * 防疫员
     */
    @Schema(name="vaccinUser", description="防疫员")
    private String vaccinUser;
    /**
     * 防疫时间
     */
    @Schema(name="preventTime", description="防疫时间")
    private Date preventTime;
}
