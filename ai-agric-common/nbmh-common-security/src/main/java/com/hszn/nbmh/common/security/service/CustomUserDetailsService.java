package com.hszn.nbmh.common.security.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.hszn.nbmh.common.core.constant.CommonConstant;
import com.hszn.nbmh.common.core.constant.SecurityConstants;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.core.utils.RetOps;
import com.hszn.nbmh.user.api.params.out.LoginUser;
import com.hszn.nbmh.user.api.entity.NbmhUser;
import org.springframework.core.Ordered;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public interface CustomUserDetailsService extends UserDetailsService, Ordered {

	/**
	 * 是否支持此客户端校验
	 * @param clientId 目标客户端
	 * @return true/false
	 */
	default boolean support(String clientId, String grantType) {
		return true;
	}

	/**
	 * 排序值 默认取最大的
	 * @return 排序值
	 */
	default int getOrder() {
		return 0;
	}

	/**
	 * 构建userdetails
	 * @param result 用户信息
	 * @return UserDetails
	 */
	default UserDetails getUserDetails(Result<LoginUser> result) {
		LoginUser info = RetOps.of(result).getData().orElseThrow(() -> new UsernameNotFoundException("用户不存在"));

		Set<String> dbAuthsSet = new HashSet<>();

		Collection<GrantedAuthority> authorities = AuthorityUtils
				.createAuthorityList(dbAuthsSet.toArray(new String[0]));
		NbmhUser user = info.getUser();

		boolean mutilRole = false;
		List<Integer> userRoles = new ArrayList<>();
		if(CollectionUtil.isNotEmpty(info.getExtraInfo())){
			if (info.getExtraInfo().size() > 1) {
				mutilRole = true;
			}
			userRoles.addAll(info.getExtraInfo().stream().map(e -> e.getType()).collect(Collectors.toList()));
		}

		// 构造security用户
		AuthUser authUser = new AuthUser(user.getId(), user.getUserName(),
				SecurityConstants.BCRYPT + user.getPassword(), user.getPhone(), true, true, true,
				StrUtil.equals(user.getStatus().toString(), CommonConstant.STATUS_NORMAL), authorities, mutilRole, userRoles);


		return authUser;
	}

	/**
	 * 通过用户实体查询
	 * @param AuthUser user
	 * @return
	 */
	default UserDetails loadUserByUser(AuthUser authUser) {
		return this.loadUserByUsername(authUser.getUsername());
	}

}
