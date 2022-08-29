package com.hszn.nbmh.app.params.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author：袁德民
 * @Description: 短信登录请求参数
 * @Date:下午9:24 22/8/20
 * @Modified By:
 */
@Schema(description = "短信登录请求参数")
@Data
public class SmsLoginParam implements Serializable {

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "验证码")
    private String code;
}
