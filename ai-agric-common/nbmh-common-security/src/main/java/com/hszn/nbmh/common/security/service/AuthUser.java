package com.hszn.nbmh.common.security.service;

import com.hszn.nbmh.user.api.params.out.CurUserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

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

    @Setter
    @Getter
    private Boolean mutilRole;

    @Setter
    @Getter
    private List<Integer> roles = new ArrayList<>();

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
