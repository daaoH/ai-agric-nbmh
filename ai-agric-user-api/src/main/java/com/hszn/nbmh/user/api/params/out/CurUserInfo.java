package com.hszn.nbmh.user.api.params.out;

import com.hszn.nbmh.user.api.entity.NbmhUser;
import com.hszn.nbmh.user.api.entity.NbmhUserExtraInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Schema(description = "当前用户信息")
@Data
public class CurUserInfo implements Serializable {

    /**
     * 用户基本信息
     */
    @Schema(description = "用户基本信息")
    private NbmhUser user;

    /**
     * 用户扩展信息
     */
    @Schema(description = "用户扩展信息")
    private NbmhUserExtraInfo extraInfo;

    /**
     * 当前角色类型
     */
    @Schema(description = "当前角色")
    private Integer type;

    /**
     * 是否有多个角色
     */
    @Schema(description = "是否多角色 0:否 1:是")
    private Boolean mutilRole;

    /**
     * 角色数组
     */
    @Schema(description = "角色 1普通用户 2专家 3站长 4防疫员 5养殖户 6商家")
    private List<Integer> roles;

}