package com.hszn.nbmh.admin.api.params.vo;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午8:50 22/9/6
 * @Modified By:
 */
public class SysAuthUser extends User implements OAuth2AuthenticatedPrincipal {

    /**
     * 用户ID
     */
    @Getter
    private final Long id;

    /**
     * 部门ID
     */
    @Getter
    private final Long deptId;

    /**
     * 手机号
     */
    @Getter
    private final String phone;

    public SysAuthUser(Long id, Long deptId, String username, String password, String phone, boolean enabled,
                   boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
                   Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.deptId = deptId;
        this.phone = phone;
    }


    @Override
    public Map<String, Object> getAttributes() {
        return new HashMap<>();
    }

    @Override
    public String getName() {
        return this.getUsername();
    }

}
