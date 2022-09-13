package com.hszn.nbmh.user.api.params.input;

import com.hszn.nbmh.user.api.entity.NbmhUserExtraInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;


/**
 * @Author：袁德民
 * @Description: 基础注册参数
 * @Date:下午9:25 22/8/23
 * @Modified By:
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Builder(toBuilder=true)
@NoArgsConstructor
@Accessors(chain=true)
@Schema(description="基础注册参数对象")
public class RegisterParam {

    @Schema(description="是否为站长", name="isStationmaster")
    private boolean isStationMaster;

    @Schema(description="邀请人ID", name="inviteBy")
    private Long inviteBy;

    @Schema(description="手机号或用户名", name="userName")
    private String userName;

    @Schema(description="登录类型（0用户名、1手机号）", name="loginType")
    private Integer loginType;

    @Schema(description="附属信息", name="extraInfo")
    private NbmhUserExtraInfo extraInfo;

    @Schema(description="邀请类型 3站长  5:养殖户  4:防疫员 7:稽查员", name="inviteType")
    private int inviteType;

    @Schema(description="防疫站名称", name="preventStationName")
    private String preventStationName;

    @Schema(description="手机号", name="phone")
    private String phone;

}
