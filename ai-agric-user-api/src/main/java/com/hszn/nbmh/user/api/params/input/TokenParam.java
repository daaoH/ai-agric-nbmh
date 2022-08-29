package com.hszn.nbmh.user.api.params.input;

import lombok.Data;

import java.time.Instant;

/**
 * @Author：袁德民
 * @Description:　令牌参数
 * @Date:下午3:51 22/8/17
 * @Modified By:
 */
@Data
public class TokenParam {

    private String id;

    private Long userId;

    private String clientId;

    private String username;

    private String accessToken;

    private Instant issuedAt;

    private Instant expiresAt;
}
