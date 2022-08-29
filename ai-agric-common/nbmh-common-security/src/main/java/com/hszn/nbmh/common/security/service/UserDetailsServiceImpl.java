package com.hszn.nbmh.common.security.service;

import com.hszn.nbmh.common.core.constant.SecurityConstants;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.user.api.feign.RemoteUserService;
import com.hszn.nbmh.user.api.params.out.LoginUser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户详细信息
 */
@Slf4j
@Primary
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements CustomUserDetailsService {

	private final RemoteUserService remoteUserService;

	/**
	 * 用户名密码登录
	 * @param username 用户名
	 * @return
	 */
	@Override
	@SneakyThrows
	public UserDetails loadUserByUsername(String username) {

		Result<LoginUser> result = remoteUserService.queryUserByPhone(username, SecurityConstants.FROM_IN);
		UserDetails userDetails = getUserDetails(result);

		return userDetails;
	}

	@Override
	public int getOrder() {
		return Integer.MIN_VALUE;
	}

}
