package com.hszn.nbmh.app.params.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午4:03 22/8/24
 * @Modified By:
 */
@Data
@AllArgsConstructor
public class UserInfoVo implements Serializable {

    private String username;
    private String password;
    private String[] authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private Long id;
    private String phone;
    private String name;
    private String attributes;
}
