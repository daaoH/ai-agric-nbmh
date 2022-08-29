package com.hszn.nbmh.auth.endpoint;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.auth.support.handler.CustomAuthenticationFailureEventHandler;
import com.hszn.nbmh.common.core.constant.CacheConstant;
import com.hszn.nbmh.common.core.constant.PaginationConstant;
import com.hszn.nbmh.common.core.constant.SecurityConstants;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.core.utils.RetOps;
import com.hszn.nbmh.common.core.utils.SpringContextHolder;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.common.security.util.OAuth2EndpointUtils;
import com.hszn.nbmh.common.security.util.OAuth2ErrorCodesExpand;
import com.hszn.nbmh.common.security.util.OAuthClientException;
import com.hszn.nbmh.user.api.entity.SysOauthClientDetails;
import com.hszn.nbmh.user.api.feign.RemoteClientDetailsService;
import com.hszn.nbmh.user.api.params.input.TokenParam;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2TokenType;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
public class CustomTokenEndpoint {

	private final HttpMessageConverter<OAuth2AccessTokenResponse> accessTokenHttpResponseConverter = new OAuth2AccessTokenResponseHttpMessageConverter();

	private final AuthenticationFailureHandler authenticationFailureHandler = new CustomAuthenticationFailureEventHandler();

	private final OAuth2AuthorizationService authorizationService;

	private final RemoteClientDetailsService clientDetailsService;

	private final RedisTemplate<String, Object> redisTemplate;

	private final CacheManager cacheManager;

	/**
	 * 认证页面
	 * @param modelAndView
	 * @param error 表单登录失败处理回调的错误信息
	 * @return ModelAndView
	 */
	@GetMapping("/login")
	public ModelAndView require(ModelAndView modelAndView, @RequestParam(required = false) String error) {
		modelAndView.setViewName("templates/ftl/login");
		modelAndView.addObject("error", error);
		return modelAndView;
	}

	@GetMapping("/confirm_access")
	public ModelAndView confirm(Principal principal, ModelAndView modelAndView,
			@RequestParam(OAuth2ParameterNames.CLIENT_ID) String clientId,
			@RequestParam(OAuth2ParameterNames.SCOPE) String scope,
			@RequestParam(OAuth2ParameterNames.STATE) String state) {
		SysOauthClientDetails clientDetails = RetOps
				.of(clientDetailsService.getClientDetailsById(clientId, SecurityConstants.FROM_IN)).getData()
				.orElseThrow(() -> new OAuthClientException("clientId 不合法"));

		Set<String> authorizedScopes = StringUtils.commaDelimitedListToSet(clientDetails.getScope());
		modelAndView.addObject("clientId", clientId);
		modelAndView.addObject("state", state);
		modelAndView.addObject("scopeList", authorizedScopes);
		modelAndView.addObject("principalName", principal.getName());
		modelAndView.setViewName("templates/ftl/confirm");
		return modelAndView;
	}

	/**
	 * 退出并删除token
	 * @param authHeader Authorization
	 */
	@DeleteMapping("/logout")
	public Result<Boolean> logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
		if (StrUtil.isBlank(authHeader)) {
			return Result.ok();
		}

		String tokenValue = authHeader.replace(OAuth2AccessToken.TokenType.BEARER.getValue(), StrUtil.EMPTY).trim();
		return removeToken(tokenValue);
	}

	/**
	 * 校验token
	 * @param token 令牌
	 */
	@SneakyThrows
	@GetMapping("/check_token")
	public void checkToken(String token, HttpServletResponse response, HttpServletRequest request) {
		ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);

		if (StrUtil.isBlank(token)) {
			httpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
			this.authenticationFailureHandler.onAuthenticationFailure(request, response,
					new InvalidBearerTokenException(OAuth2ErrorCodesExpand.TOKEN_MISSING));
		}
		OAuth2Authorization authorization = authorizationService.findByToken(token, OAuth2TokenType.ACCESS_TOKEN);

		// 如果令牌不存在 返回401
		if (authorization == null || authorization.getAccessToken() == null) {
			this.authenticationFailureHandler.onAuthenticationFailure(request, response,
					new InvalidBearerTokenException(OAuth2ErrorCodesExpand.INVALID_BEARER_TOKEN));
		}

		Map<String, Object> claims = authorization.getAccessToken().getClaims();
		OAuth2AccessTokenResponse sendAccessTokenResponse = OAuth2EndpointUtils.sendAccessTokenResponse(authorization,
				claims);
		this.accessTokenHttpResponseConverter.write(sendAccessTokenResponse, MediaType.APPLICATION_JSON, httpResponse);
	}

	/**
	 * 令牌管理调用
	 * @param token token
	 */
	@Inner
	@DeleteMapping("/{token}")
	public Result<Boolean> removeToken(@PathVariable("token") String token) {
		OAuth2Authorization authorization = authorizationService.findByToken(token, OAuth2TokenType.ACCESS_TOKEN);
		if (authorization == null) {
			return Result.ok();
		}

		OAuth2Authorization.Token<OAuth2AccessToken> accessToken = authorization.getAccessToken();
		if (accessToken == null || StrUtil.isBlank(accessToken.getToken().getTokenValue())) {
			return Result.ok();
		}

		// 清空access token
		authorizationService.remove(authorization);
		// 处理自定义退出事件，保存相关日志
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SpringContextHolder.publishEvent(new LogoutSuccessEvent(authentication));
		return Result.ok();
	}

	/**
	 * 查询token
	 * @param params 分页参数
	 * @return
	 */
	@Inner
	@PostMapping("/page")
	public Result<Page> tokenList(@RequestBody Map<String, Object> params) {
		// 根据分页参数获取对应数据
		String key = String.format("%s::*", CacheConstant.PROJECT_OAUTH_ACCESS);
		int current = MapUtil.getInt(params, PaginationConstant.CURRENT);
		int size = MapUtil.getInt(params, PaginationConstant.SIZE);
		Set<String> keys = redisTemplate.keys(key);
		List<String> pages = keys.stream().skip((current - 1) * size).limit(size).collect(Collectors.toList());
		Page result = new Page(current, size);

		List<TokenParam> tokenList = redisTemplate.opsForValue().multiGet(pages).stream().map(obj -> {
			OAuth2Authorization authorization = (OAuth2Authorization) obj;
			TokenParam tokenParam = new TokenParam();
			tokenParam.setClientId(authorization.getRegisteredClientId());
			tokenParam.setId(authorization.getId());
			tokenParam.setUsername(authorization.getPrincipalName());
			OAuth2Authorization.Token<OAuth2AccessToken> accessToken = authorization.getAccessToken();
			tokenParam.setAccessToken(accessToken.getToken().getTokenValue());
			tokenParam.setExpiresAt(accessToken.getToken().getExpiresAt());
			tokenParam.setIssuedAt(accessToken.getToken().getIssuedAt());
			return tokenParam;
		}).collect(Collectors.toList());
		result.setRecords(tokenList);
		result.setTotal(keys.size());
		return Result.ok(result);
	}

}
