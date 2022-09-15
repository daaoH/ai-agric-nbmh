package com.hszn.nbmh.prevent.api.params.out;

import com.hszn.nbmh.prevent.api.entity.NbmhAnimal;
import com.hszn.nbmh.prevent.api.entity.NbmhInspect;
import com.hszn.nbmh.prevent.api.entity.NbmhVaccin;
import com.hszn.nbmh.user.api.params.out.CurUserInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:15 22/8/29
 * @Modified By:
 */
@Schema(name="动物信息响应信息")
@Data
public class AnimalDetailsResult extends NbmhAnimal implements Serializable {

    /**
     * 动物信息
     */
    @Schema(name="animal", description="动物信息")
    private NbmhAnimal animal;

    /**
     * 防疫信息
     */
    @Schema(name="inspect", description="检疫信息")
    private NbmhInspect inspect;


    @Schema(name="vaccinList", description="防疫信息")
    private List<NbmhVaccin> vaccinList;

    @Schema(name="curUserInfo", description="养殖户信息")
    private CurUserInfo curUserInfo;

}
