package com.hszn.nbmh.auth.config;

import com.hszn.nbmh.auth.support.CustomeOAuth2AccessTokenGenerator;
import com.hszn.nbmh.auth.support.core.CustomDaoAuthenticationProvider;
import com.hszn.nbmh.auth.support.core.CustomeOAuth2TokenCustomizer;
import com.hszn.nbmh.auth.support.core.FormIdentityLoginConfigurer;
import com.hszn.nbmh.auth.support.handler.CustomAuthenticationFailureEventHandler;
import com.hszn.nbmh.auth.support.handler.CustomAuthenticationSuccessEventHandler;
import com.hszn.nbmh.auth.support.password.OAuth2ResourceOwnerPasswordAuthenticationConverter;
import com.hszn.nbmh.auth.support.password.OAuth2ResourceOwnerPasswordAuthenticationProvider;
import com.hszn.nbmh.auth.support.sms.OAuth2ResourceOwnerSmsAuthenticationConverter;
import com.hszn.nbmh.auth.support.sms.OAuth2ResourceOwnerSmsAuthenticationProvider;
import com.hszn.nbmh.common.core.constant.SecurityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.token.DelegatingOAuth2TokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2RefreshTokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.oauth2.server.authorization.web.authentication.*;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Arrays;

/**
 *
 * ?????????????????????
 */
@Configuration
@RequiredArgsConstructor
public class AuthorizationServerConfiguration {

	private final OAuth2AuthorizationService authorizationService;

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
		OAuth2AuthorizationServerConfigurer<HttpSecurity> authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer<>();

		http.apply(authorizationServerConfigurer.tokenEndpoint((tokenEndpoint) -> {// ???????????????????????????
			tokenEndpoint.accessTokenRequestConverter(accessTokenRequestConverter()) // ??????????????????????????????Converter
					.accessTokenResponseHandler(new CustomAuthenticationSuccessEventHandler()) // ?????????????????????
					.errorResponseHandler(new CustomAuthenticationFailureEventHandler());// ?????????????????????
		}).clientAuthentication(oAuth2ClientAuthenticationConfigurer -> // ????????????????????????
		oAuth2ClientAuthenticationConfigurer.errorResponseHandler(new CustomAuthenticationFailureEventHandler()))// ???????????????????????????
				.authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint// ????????????????????????confirm??????
						.consentPage(SecurityConstants.CUSTOM_CONSENT_PAGE_URI)));

		RequestMatcher endpointsMatcher = authorizationServerConfigurer.getEndpointsMatcher();
		DefaultSecurityFilterChain securityFilterChain = http.requestMatcher(endpointsMatcher)
				.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
				.apply(authorizationServerConfigurer.authorizationService(authorizationService)// redis??????token?????????
						.providerSettings(ProviderSettings.builder().issuer(SecurityConstants.PROJECT_LICENSE).build()))
				// ????????????????????????????????????
				.and().apply(new FormIdentityLoginConfigurer()).and().build();

		// ?????????????????????????????????
		addCustomOAuth2GrantAuthenticationProvider(http);
		return securityFilterChain;
	}

	/**
	 * ???????????????????????? </br>
	 * client:username:uuid
	 * @return OAuth2TokenGenerator
	 */
	@Bean
	public OAuth2TokenGenerator oAuth2TokenGenerator() {
		CustomeOAuth2AccessTokenGenerator accessTokenGenerator = new CustomeOAuth2AccessTokenGenerator();
		// ??????Token ????????????????????????
		accessTokenGenerator.setAccessTokenCustomizer(new CustomeOAuth2TokenCustomizer());
		return new DelegatingOAuth2TokenGenerator(accessTokenGenerator, new OAuth2RefreshTokenGenerator());
	}

	/**
	 * request -> xToken ?????????????????????
	 * @return DelegatingAuthenticationConverter
	 */
	private AuthenticationConverter accessTokenRequestConverter() {
		return new DelegatingAuthenticationConverter(Arrays.asList(
				new OAuth2ResourceOwnerPasswordAuthenticationConverter(),
				new OAuth2ResourceOwnerSmsAuthenticationConverter(), new OAuth2RefreshTokenAuthenticationConverter(),
				new OAuth2ClientCredentialsAuthenticationConverter(),
				new OAuth2AuthorizationCodeAuthenticationConverter(),
				new OAuth2AuthorizationCodeRequestAuthenticationConverter()));
	}

	/**
	 * ?????????????????????????????????
	 *
	 * 1. ???????????? </br>
	 * 2. ???????????? </br>
	 *
	 */
	@SuppressWarnings("unchecked")
	private void addCustomOAuth2GrantAuthenticationProvider(HttpSecurity http) {
		AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
		OAuth2AuthorizationService authorizationService = http.getSharedObject(OAuth2AuthorizationService.class);

		OAuth2ResourceOwnerPasswordAuthenticationProvider resourceOwnerPasswordAuthenticationProvider = new OAuth2ResourceOwnerPasswordAuthenticationProvider(
				authenticationManager, authorizationService, oAuth2TokenGenerator());

		OAuth2ResourceOwnerSmsAuthenticationProvider resourceOwnerSmsAuthenticationProvider = new OAuth2ResourceOwnerSmsAuthenticationProvider(
				authenticationManager, authorizationService, oAuth2TokenGenerator());

		// ?????? UsernamePasswordAuthenticationToken
		http.authenticationProvider(new CustomDaoAuthenticationProvider());
		// ?????? OAuth2ResourceOwnerPasswordAuthenticationToken
		http.authenticationProvider(resourceOwnerPasswordAuthenticationProvider);
		// ?????? OAuth2ResourceOwnerSmsAuthenticationToken
		http.authenticationProvider(resourceOwnerSmsAuthenticationProvider);
	}

}
