package com.hszn.nbmh.user.api.params.out;

import com.hszn.nbmh.user.api.entity.NbmhUser;
import com.hszn.nbmh.user.api.entity.NbmhUserExtraInfo;
import com.hszn.nbmh.user.api.entity.SysPost;
import com.hszn.nbmh.user.api.entity.SysRole;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class LoginUser implements Serializable {

    /**
     * 用户基本信息
     */
    private NbmhUser user;

    /**
     * 用户扩展信息
     */
    private List<NbmhUserExtraInfo> extraInfo;

    /**
     * 权限标识集合
     */
    private String[] permissions;

    /**
     * 角色集合
     */
    private Long[] roles;

    /**
     * 角色集合
     */
    private List<SysRole> roleList;

    /**
     * 岗位集合
     */
    private Long[] posts;

    /**
     * 岗位集合
     */
    private List<SysPost> postList;

}