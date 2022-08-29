package com.hszn.nbmh.auth.api.fallback;

import com.hszn.nbmh.auth.api.feign.Oauth2Api;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午6:55 22/8/17
 * @Modified By:
 */
@Component
public class Oauth2ApiFallback implements Oauth2Api {


    @Override
    public String postAccessToken(MultiValueMap<String, String> parameters, MultiValueMap<String, String> headers) {
        return null;
    }

    @Override
    public void removeToken(String access_token) {

    }
}
