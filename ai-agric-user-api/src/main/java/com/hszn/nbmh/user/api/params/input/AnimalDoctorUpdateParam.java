package com.hszn.nbmh.user.api.params.input;

import com.hszn.nbmh.user.api.entity.NbmhAnimalDoctorDetail;
import com.hszn.nbmh.user.api.entity.NbmhUserExtraInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

/**
 * <p>
 * 兽医专家个人资料更新参数实体
 * </p>
 *
 * @author MCR
 * @since 2022-09-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "兽医专家个人资料更新参数实体")
public class AnimalDoctorUpdateParam {

    @NotNull(message = "更新数据时userId不能为空", groups = {Update.class})
    @Schema(description = "用户userId", name = "userId")
    private Long userId;

    @NotNull(message = "更新兽医用户扩展信息数据时extraInfo不能为空", groups = {Update.class})
    @Schema(description = "用户附属信息", name = "extraInfo")
    private NbmhUserExtraInfo extraInfo;

    @NotNull(message = "更新兽医专属详细信息数据时animalDoctorDetail不能为空", groups = {Update.class})
    @Schema(description = "兽医专属详细信息", name = "animalDoctorDetail")
    private NbmhAnimalDoctorDetail animalDoctorDetail;

    public interface Update extends Default {
    }

}
