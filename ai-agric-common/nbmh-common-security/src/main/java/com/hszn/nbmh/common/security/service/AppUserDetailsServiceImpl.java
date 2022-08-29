package com.hszn.nbmh.common.security.service;

import com.hszn.nbmh.common.core.constant.SecurityConstants;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.user.api.feign.RemoteUserService;
import com.hszn.nbmh.user.api.params.out.LoginUser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户详细信息
 *
 */
@Slf4j
@RequiredArgsConstructor
public class AppUserDetailsServiceImpl implements CustomUserDetailsService {

	private final RemoteUserService remoteUserService;

	/**
	 * 手机号登录
	 * @param phone 手机号
	 * @return
	 */
	@Override
	@SneakyThrows
	public UserDetails loadUserByUsername(String phone) {

		Result<LoginUser> result = remoteUserService.queryUserByPhone(phone, SecurityConstants.FROM_IN);

		UserDetails userDetails = getUserDetails(result);

		return userDetails;
	}

	/**
	 * check-token 使用
	 * @param AuthUser user
	 * @return
	 */
	@Override
	public UserDetails loadUserByUser(AuthUser user) {
		return this.loadUserByUsername(user.getPhone());
	}

	/**
	 * 是否支持此客户端校验
	 * @param clientId 目标客户端
	 * @return true/false
	 */
	@Override
	public boolean support(String clientId, String grantType) {
		return SecurityConstants.APP.equals(clientId);
	}

}
