package com.hszn.nbmh.app.params.out;

import com.hszn.nbmh.app.params.vo.UserInfoVo;
import com.hszn.nbmh.user.api.entity.NbmhUser;
import com.hszn.nbmh.user.api.params.out.CurUserInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Author：袁德民
 * @Description: 返回给前端的用户信息
 * @Date:下午3:57 22/8/24
 * @Modified By:
 */
@Schema(description = "用户信息")
@Data
public class UserInfoReturn {

    @Schema(description = "用户信息")
    private UserInfoVo userInfo;

    @Schema(description = "token")
    private String token;

    @Schema(description = "刷新token")
    private String refreshToken;

    @Schema(description = "是否多个角色 0:单一角色 1:多个角色")
    private boolean mutilRole;

    @Schema(description = "角色数组　1普通用户 2专家 3站长 4防疫员 5养殖户 6商家")
    private List<Integer> roles;
}
