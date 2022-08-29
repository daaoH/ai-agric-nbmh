package com.hszn.nbmh.common.core.utils;

import com.hszn.nbmh.common.core.constant.CommonConstant;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description: 响应信息主体
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "响应信息主体")
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @ApiModelProperty(value = "返回标记：成功标记=200，失败标记=400")
    private int code;

    @Getter
    @Setter
    @ApiModelProperty(value = "返回信息")
    private String msg;

    @Getter
    @Setter
    @ApiModelProperty(value = "数据")
    private T data;

    public static <T> Result<T> ok() {
        return restResult(null, CommonConstant.SUCCESS, null);
    }

    public static <T> Result<T> ok(T data) {
        return restResult(data, CommonConstant.SUCCESS, null);
    }

    public static <T> Result<T> ok(T data, String msg) {
        return restResult(data, CommonConstant.SUCCESS, msg);
    }

    public static <T> Result<T> failed() {
        return restResult(null, CommonConstant.FAIL, null);
    }

    public static <T> Result<T> failed(String msg) {
        return restResult(null, CommonConstant.FAIL, msg);
    }

    public static <T> Result<T> failed(T data) {
        return restResult(data, CommonConstant.FAIL, null);
    }

    public static <T> Result<T> failed(int code, String msg) {
        return restResult(null, code, msg);
    }

    public static <T> Result<T> restResult(T data, int code, String msg) {
        Result<T> apiResult = new Result<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public Result(CommonEnum commonEnum, T data) {
        this.code = commonEnum.getCode();
        this.msg = commonEnum.getMsg();
        this.data = data;
    }

    public Result(CommonEnum commonEnum) {
        this.code = commonEnum.getCode();
        this.msg = commonEnum.getMsg();
    }


    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }
}
