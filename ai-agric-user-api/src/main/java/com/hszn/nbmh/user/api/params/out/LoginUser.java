package com.hszn.nbmh.user.api.params.out;

import com.hszn.nbmh.user.api.entity.NbmhUser;
import com.hszn.nbmh.user.api.entity.NbmhUserExtraInfo;
import com.hszn.nbmh.user.api.entity.SysPost;
import com.hszn.nbmh.user.api.entity.SysRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Schema(description = "app登录用户信息")
@Data
public class LoginUser implements Serializable {

    /**
     * 用户基本信息
     */
    @Schema(description = "用户基本信息")
    private NbmhUser user;

    /**
     * 用户扩展信息
     */
    @Schema(description = "用户扩展数据")
    private List<NbmhUserExtraInfo> extraInfo;

}