
package com.hszn.nbmh.common.core.exception;

import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * 全局异常处理，处理可预见的异常，Order 排序优先级高
 *
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@RestControllerAdvice
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class RestExceptionHandler {

    /**
     * 未能捕获的异常
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.warn("未知异常:{}", e.getMessage());
        log.error("未知异常:{}", e);
        return Result.failed(CommonEnum.FAILED_RESPONSE.getCode(), "服务器内部错误");
    }

    @ExceptionHandler(ServiceException.class)
    public Result handleServiceException(ServiceException e) {
        log.warn("自定义异常:{}", e.getMessage());
        return Result.failed(e.getCode(), e.getMsg());
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<Object> handleError(MissingServletRequestParameterException e) {
        log.warn("缺少请求参数:{}", e.getMessage());
        String message = String.format("缺少必要的请求参数: %s", e.getParameterName());
        return Result.failed(CommonEnum.PARAM_MISS.getCode(), message);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result<Object> handleError(MethodArgumentTypeMismatchException e) {
        log.warn("请求参数格式错误:{}", e.getMessage());
        String message = String.format("请求参数格式错误: %s", e.getName());
        return Result.failed(CommonEnum.PARAM_TYPE_ERROR.getCode(), message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Object> handleError(MethodArgumentNotValidException e) {
        log.warn("参数验证失败:{}", e.getMessage());
        return handleError(e.getBindingResult());
    }

    @ExceptionHandler(BindException.class)
    public Result<Object> handleError(BindException e) {
        log.warn("参数绑定失败:{}", e.getMessage());
        return handleError(e.getBindingResult());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result<Object> handleError(ConstraintViolationException e) {
        log.warn("参数验证失败:{}", e.getMessage());
        return handleError(e.getConstraintViolations());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public Result<Object> handleError(BadCredentialsException e){
        log.warn("认证失败:{}", e.getMessage());
        return Result.failed("用户名或密码错误");
    }

    /**
     * 处理 BindingResult
     *
     * @param result BindingResult
     * @return Result
     */
    protected Result<Object> handleError(BindingResult result) {
        FieldError error = result.getFieldError();
        String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
        return Result.failed(CommonEnum.PARAM_BIND_ERROR.getCode(), message);
    }

    /**
     * 处理 ConstraintViolation
     *
     * @param violations 校验结果
     * @return Result
     */
    protected Result<Object> handleError(Set<ConstraintViolation<?>> violations) {
        ConstraintViolation<?> violation = violations.iterator().next();
        String path = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
        String message = String.format("%s:%s", path, violation.getMessage());
        return Result.failed(CommonEnum.PARAM_VALID_ERROR.getCode(), message);
    }

}
