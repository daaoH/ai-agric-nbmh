package com.hszn.nbmh.user.api.feign;

import com.hszn.nbmh.common.core.constant.SecurityConstants;
import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.user.api.constant.UserPathConstant;
import com.hszn.nbmh.user.api.entity.SysOauthClientDetails;
import com.hszn.nbmh.user.api.fallback.ClientDetailsServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(contextId = "remoteClientDetailsService", value = ServiceNameConstant.USER_SERVICE,
		fallback = ClientDetailsServiceFallback.class)
public interface RemoteClientDetailsService {

	/**
	 * 通过clientId 查询客户端信息
	 * @param clientId 用户名
	 * @param from 调用标志
	 * @return Result
	 */
	@GetMapping("/client/getClientDetailsById/{clientId}")
	Result<SysOauthClientDetails> getClientDetailsById(@PathVariable("clientId") String clientId,
													   @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 查询全部客户端
	 * @param from 调用标识
	 * @return Result
	 */
	@GetMapping("/client/list")
	Result<List<SysOauthClientDetails>> listClientDetails(@RequestHeader(SecurityConstants.FROM) String from);

}
