package com.hszn.nbmh.auth.api.feign;

import com.hszn.nbmh.auth.api.fallback.Oauth2ApiFallback;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = ServiceNameConstant.AUTH_SERVICE, url = "http://192.168.0.34:13000", fallback = Oauth2ApiFallback.class)
public interface Oauth2Api {

    /**
     * 获取access_token<br>
     * 这是spring-security-oauth2底层的接口，类TokenEndpoint<br>
     *
     * @param parameters
     * @return
     */
    @PostMapping(path = "/oauth2/token")
    String postAccessToken(@RequestParam MultiValueMap<String, String> parameters, @RequestHeader MultiValueMap<String, String> headers);

    /**
     * 删除access_token和refresh_token<br>
     * 认证中心的OAuth2Controller方法removeToken
     *
     * @param access_token
     */
    @DeleteMapping(path = "/remove_token")
    void removeToken(@RequestParam("access_token") String access_token);

}
