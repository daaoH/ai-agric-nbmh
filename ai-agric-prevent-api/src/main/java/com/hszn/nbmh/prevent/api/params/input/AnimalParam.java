package com.hszn.nbmh.prevent.api.params.input;

import com.hszn.nbmh.prevent.api.entity.NbmhAnimal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:15 22/8/16
 * @Modified By:
 */
@Schema(name="防疫信息")
@Data
public class AnimalParam extends NbmhAnimal implements Serializable {

}
