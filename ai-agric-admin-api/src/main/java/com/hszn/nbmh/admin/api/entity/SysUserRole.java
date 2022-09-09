package com.hszn.nbmh.admin.api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.io.Serializable;

/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author yuan
 * @since 2022-09-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @NotNull(message = "更新或删除数据时用户userId不能为空", groups = {Save.class, Update.class, Delete.class})
    @Schema(name = "userId", description = "用户id")
    private Long userId;

    /**
     * 角色id
     */
    @NotNull(message = "更新或删除数据时角色roleId不能为空", groups = {Save.class, Update.class, Delete.class})
    @Schema(name = "roleId", description = "角色id")
    private Long roleId;

    /**
     * 用户类型 1普通用户 2专家 3站长 4防疫员 5养殖户 6商家
     */
    @NotNull(message = "更新或删除数据时用户类型userType不能为空", groups = {Save.class, Update.class, Delete.class})
    @Schema(name = "userType", description = "用户类型 1普通用户 2专家 3站长 4防疫员 5养殖户 6商家")
    private Integer userType;

    public interface Save extends Default {
    }

    public interface Update extends Default {
    }

    public interface Delete extends Default {
    }
}
