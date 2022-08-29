package com.hszn.nbmh.auth.controller;

import com.hszn.nbmh.common.core.constant.SecurityConstants;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.user.api.feign.RemoteUserService;
import com.hszn.nbmh.user.api.params.out.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午7:05 22/8/17
 * @Modified By:
 */

@RestController
@RequestMapping("/test")
public class TestController {

    private PasswordEncoder ENCODER = new BCryptPasswordEncoder();

    @Autowired
    private RemoteUserService userService;

    @Inner(false)
    @GetMapping("/getinfo")
    public Result getInfo(){

        String pwd = ENCODER.encode("123456");
        System.out.println(pwd);
        /*Result<LoginUser> ret = userService.queryUserByPhone("13311398111", SecurityConstants.FROM_IN);
        if(ret.getCode() == 200) {
            return Result.ok();
        }*/

        return Result.ok(pwd);
    }
}
