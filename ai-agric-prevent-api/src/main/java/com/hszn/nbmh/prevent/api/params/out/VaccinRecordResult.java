package com.hszn.nbmh.prevent.api.params.out;

import com.hszn.nbmh.prevent.api.entity.NbmhAnimal;
import com.hszn.nbmh.prevent.api.entity.NbmhVaccin;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:15 22/8/16
 * @Modified By:
 */
@Schema(name="防疫记录响应参数信息")
@Data
public class VaccinRecordResult extends NbmhVaccin implements Serializable {

    /**
     * 动物信息
     */
    @Schema(name="animal", description="动物信息")
    private NbmhAnimal animal;


}
