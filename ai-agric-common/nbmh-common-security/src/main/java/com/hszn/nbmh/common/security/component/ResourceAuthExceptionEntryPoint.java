package com.hszn.nbmh.common.security.component;

import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hszn.nbmh.common.core.constant.CommonConstant;
import com.hszn.nbmh.common.core.utils.Result;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 客户端异常处理 AuthenticationException 不同细化异常处理
 */
@Component
@RequiredArgsConstructor
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper;

	@Override
	@SneakyThrows
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) {
		response.setCharacterEncoding(CommonConstant.UTF8);
		response.setContentType(CommonConstant.CONTENT_TYPE);
		Result<String> result = new Result<>();
		result.setCode(CommonConstant.FAIL);
		response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
		if (authException != null) {
			result.setMsg("error");
			result.setData(authException.getMessage());
		}

		// 针对令牌过期返回特殊的 424
		if (authException instanceof InvalidBearerTokenException) {
			response.setStatus(org.springframework.http.HttpStatus.FAILED_DEPENDENCY.value());
			result.setMsg("token expire");
		}
		PrintWriter printWriter = response.getWriter();
		printWriter.append(objectMapper.writeValueAsString(result));
	}

}
