package com.hszn.nbmh.user.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author yuan
 * @since 2022-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;

    private String roleName;

    private String roleCode;

    private String roleDesc;

    private Date createTime;

    private Date updateTime;

    /**
     * 删除标识（0-正常,1-删除）
     */
    private String status;


}
