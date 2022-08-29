package com.hszn.nbmh.common.security.service;

import lombok.AllArgsConstructor;
import lombok.Data;
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
 * @Date:上午11:35 22/8/16
 * @Modified By:
 */
public class AuthUser extends User implements OAuth2AuthenticatedPrincipal {

    @Getter
    private final Long id;

    @Getter
    private final String phone;

    public AuthUser(Long id, String username,  String password, String phone, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
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
