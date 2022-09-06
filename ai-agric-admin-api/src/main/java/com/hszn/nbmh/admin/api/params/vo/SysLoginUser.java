package com.hszn.nbmh.admin.api.params.vo;

import com.hszn.nbmh.admin.api.entity.SysPost;
import com.hszn.nbmh.admin.api.entity.SysRole;
import com.hszn.nbmh.admin.api.entity.SysUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author：袁德民
 * @Description: 后台管理登录用户数据
 * @Date:下午9:03 22/9/6
 * @Modified By:
 */
@Schema(description = "后台管理登录用户数据")
@Data
public class SysLoginUser implements Serializable {
    /**
     * 用户基本信息
     */
    private SysUser sysUser;

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
