package com.hszn.nbmh.user.api.params.input;

import com.hszn.nbmh.user.api.entity.NbmhAnimalDoctorDetail;
import com.hszn.nbmh.user.api.entity.NbmhUserExtraInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 兽医专家注册参数实体
 * </p>
 *
 * @author MCR
 * @since 2022-08-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "兽医专家注册参数实体")
public class AnimalDoctorRegisterParam {


    @Schema(description = "登录名（手机号或用户名）", name = "loginName")
    private String loginName;

    @Schema(description = "登录类型（0用户名、1手机号）", name = "loginType")
    private Integer loginType;

    @Schema(description = "附属信息", name = "extraInfo")
    private NbmhUserExtraInfo extraInfo;

    @Schema(description = "兽医详细信息", name = "animalDoctorDetail")
    private NbmhAnimalDoctorDetail animalDoctorDetail;

}
