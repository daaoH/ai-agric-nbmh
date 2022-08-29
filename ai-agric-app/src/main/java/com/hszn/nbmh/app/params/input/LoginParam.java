package com.hszn.nbmh.app.params.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午7:23 22/8/20
 * @Modified By:
 */
@Schema(description = "登录请求对象")
@Data
public class LoginParam implements Serializable {

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "密码")
    private String password;
}
