package com.hszn.nbmh.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author：袁德民
 * @Description: 角色
 * @Date:上午8:39 22/8/16
 * @Modified By:
 */
@Getter
@AllArgsConstructor
public enum RoleEnum {

    NORMAL_ROLE(1, "普通用户"),
    EXPERT_ROLE(2, "专家"),
    MASTER_ROLE(3, "站长"),
    PREVENTOR_ROLE(4, "防疫员"),
    FARMER_ROLE(5, "养殖户"),
    SELLER_ROLE(6, "商家");

    /**
     * 1普通用户 2专家 3站长 4防疫员 5养殖户 6商家
     */
    private Integer code;

    private String role;
}
