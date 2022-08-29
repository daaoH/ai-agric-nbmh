package com.hszn.nbmh.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hszn.nbmh.filter.CustomRequestGlobalFilter;
import com.hszn.nbmh.filter.PasswordDecoderFilter;
import com.hszn.nbmh.filter.SwaggerBasicGatewayFilter;
import com.hszn.nbmh.filter.ValidateCodeGatewayFilter;
import com.hszn.nbmh.handler.GlobalExceptionHandler;
import com.hszn.nbmh.handler.ImageCodeHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 网关配置
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(GatewayConfigProperties.class)
public class GatewayConfiguration {

	@Bean
	public PasswordDecoderFilter passwordDecoderFilter(GatewayConfigProperties configProperties) {
		return new PasswordDecoderFilter(configProperties);
	}

	@Bean
	public CustomRequestGlobalFilter customRequestGlobalFilter() {
		return new CustomRequestGlobalFilter();
	}

	@Bean
	@ConditionalOnProperty(name = "swagger.basic.enabled")
	public SwaggerBasicGatewayFilter swaggerBasicGatewayFilter(
			SpringDocConfiguration.SwaggerDocProperties swaggerProperties) {
		return new SwaggerBasicGatewayFilter(swaggerProperties);
	}

	@Bean
	public ValidateCodeGatewayFilter validateCodeGatewayFilter(GatewayConfigProperties configProperties,
															   ObjectMapper objectMapper, RedisTemplate redisTemplate) {
		return new ValidateCodeGatewayFilter(configProperties, objectMapper, redisTemplate);
	}

	@Bean
	public GlobalExceptionHandler globalExceptionHandler(ObjectMapper objectMapper) {
		return new GlobalExceptionHandler(objectMapper);
	}

	@Bean
	public ImageCodeHandler imageCodeHandler(RedisTemplate redisTemplate) {
		return new ImageCodeHandler(redisTemplate);
	}

}
