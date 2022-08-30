package com.hszn.nbmh.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.hszn.nbmh.app.params.input.LoginParam;
import com.hszn.nbmh.app.params.input.SmsLoginParam;
import com.hszn.nbmh.app.params.out.TokenInfoReturn;
import com.hszn.nbmh.app.params.out.UserInfoReturn;
import com.hszn.nbmh.app.params.vo.UserInfoVo;
import com.hszn.nbmh.auth.api.feign.Oauth2Api;
import com.hszn.nbmh.common.core.constant.SecurityConstants;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.enums.CredentialType;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.third.entity.SmsValidateEntity;
import com.hszn.nbmh.third.feign.RemoteSmsService;
import com.hszn.nbmh.user.api.feign.RemoteUserService;
import com.hszn.nbmh.user.api.params.input.RegisterParam;
import com.hszn.nbmh.user.api.params.out.CurUserInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author：袁德民
 * @Description: 移动端用户接口
 * @Date:下午6:46 22/8/20
 * @Modified By:
 */
@Tag(name = "移动端用户接口")
@RestController
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
@RequestMapping("/app-user")
public class AppUserController {

    @Autowired
    private RemoteUserService userService;

    @Autowired
    private Oauth2Api oauth2Api;

    @Autowired
    private RemoteSmsService smsService;

    @Inner(false)
    @Operation(summary = "密码登录", description = "密码登录")
    @PostMapping("/pwd-login")
    public Result pwdLogin(@RequestBody LoginParam param) {

        String userName = param.getUserName();
        String password = param.getPassword();
        //判断用户是否存在
        Result existRet = userService.checkUserExist(userName, SecurityConstants.FROM_IN);
        boolean exist = (boolean)existRet.getData();
        if (exist) {
            //用户存在，去登录
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Content-Type", "multipart/form-data");
            headers.add("Authorization", "Basic YXBwOmFwcA==");

            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("username", userName);
            body.add("password", password);
            body.add("grant_type", "password");
            body.add("scope", "server");

            try {
                String rets = oauth2Api.postAccessToken(body, headers);
                return getUserResult(rets);
            } catch (Exception e) {
                return Result.failed(e.getMessage());
            }

        } else {
            return Result.failed("用户不存在");
        }

    }

    @NotNull
    private Result getUserResult(String rets) {
        if (ObjectUtils.isEmpty(rets)) {
            return Result.failed("用户名或密码错误");
        } else {
            //token信息解析
            TokenInfoReturn token = JSONObject.parseObject(rets, TokenInfoReturn.class);
            if (ObjectUtils.isNotEmpty(token)) {
                //返回数据
                UserInfoReturn retData = new UserInfoReturn();
                retData.setToken(token.getAccess_token());
                retData.setRefreshToken(token.getRefresh_token());

                UserInfoVo userInfo = token.getUser_info();
                if (ObjectUtils.isNotEmpty(userInfo)) {
                    //查询当前登录用户信息
                    Result<CurUserInfo> curUserRet = userService.queryCurUserInfo(userInfo.getId(), null);
                    if(curUserRet.getCode() == 200){
                        CurUserInfo curUser = curUserRet.getData();
                        retData.setMutilRole(curUser.getMutilRole());
                        retData.setRoles(curUser.getRoles());
                        retData.setUserInfo(curUser);
                    }else{
                        Result.failed("获取用户信息失败");
                    }
                } else {
                    return Result.failed("用户数据为空");
                }

                return Result.ok(retData);
            } else {
                return Result.failed("用户数据解析错误");
            }
        }
    }

    @Inner(false)
    @Transactional
    @Operation(summary = "验证码登录", description = "验证码登录")
    @PostMapping("/sms-login")
    public Result smsLogin(@RequestBody SmsLoginParam param) {
        String phone = param.getPhone();
        String code = param.getCode();
        if(StringUtils.isBlank(phone)){
            return Result.failed("手机号不能为空");
        }
        //验证验证码
        Result validateRet = smsService.validateCode(phone, code);
        if(validateRet.getCode() == 200){
            Map<String, Object> validateEntity = (LinkedHashMap) validateRet.getData();
            if((boolean)validateEntity.get("validateResult")){
                //验证码验证正确，检查手机号是否存在
                Result existRet = userService.checkUserExist(phone, SecurityConstants.FROM);
                if(existRet.getCode() == 200){
                    Boolean exist = (Boolean)existRet.getData();
                    if(!exist){
                        //用户不存在，新建用户信息
                        RegisterParam registerParam = new RegisterParam();
                        registerParam.setUserName(phone);
                        registerParam.setLoginType(CredentialType.SMS.getCode());
                        Result register = userService.registerUser(registerParam);
                        if(register.getCode() != 200){
                            return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
                        }
                    }

                    //去登录，返回用户信息及token
                    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
                    headers.add("Content-Type", "multipart/form-data");
                    headers.add("Authorization", "Basic YXBwOmFwcA==");

                    MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
                    body.add("mobile", phone);
                    body.add("grant_type", "app");
                    body.add("scope", "server");
                    try {
                        String rets = oauth2Api.postAccessToken(body, headers);
                        return getUserResult(rets);
                    }catch (Exception e){
                        return Result.failed(e.getMessage());
                    }
                }else{
                    return Result.failed(CommonEnum.DATA_NOT_EXIST.getMsg());
                }
            }else{
                return Result.failed(CommonEnum.SMS_VALIDATE_FAIL.getMsg());
            }
        }else{
            return validateRet;
        }
    }
}
