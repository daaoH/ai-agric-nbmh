package com.hszn.nbmh.app.params.out;

import com.hszn.nbmh.app.params.vo.UserInfoVo;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author：袁德民
 * @Description: 请求返回的token信息
 * @Date:下午3:55 22/8/24
 * @Modified By:
 */
@Data
@AllArgsConstructor
public class TokenInfoReturn {

    private String sub;

    private String clientId;

    private String iss;

    private String token_type;

    private String access_token;

    private String refresh_token;

    private String[] aud;

    private String license;

    private String nbf;

    private UserInfoVo user_info;

    private String[] scope;

    private Double exp;

    private Double expires_in;

    private Double iat;

    private String jti;

}
