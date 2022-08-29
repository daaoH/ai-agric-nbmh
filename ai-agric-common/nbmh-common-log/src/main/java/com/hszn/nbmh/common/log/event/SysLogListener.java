package com.hszn.nbmh.common.log.event;

import com.hszn.nbmh.common.core.constant.SecurityConstants;
import com.hszn.nbmh.user.api.entity.SysLog;
import com.hszn.nbmh.user.api.feign.RemoteLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * 异步监听日志事件
 */
@Slf4j
@RequiredArgsConstructor
public class SysLogListener {

	private final RemoteLogService remoteLogService;

	@Async
	@Order
	@EventListener(SysLogEvent.class)
	public void saveSysLog(SysLogEvent event) {
		SysLog sysLog = (SysLog) event.getSource();
		remoteLogService.saveLog(sysLog, SecurityConstants.FROM_IN);
	}

}
