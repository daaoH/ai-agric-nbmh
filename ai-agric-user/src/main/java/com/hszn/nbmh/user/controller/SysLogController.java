package com.hszn.nbmh.user.controller;


import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.user.api.entity.SysLog;
import com.hszn.nbmh.user.service.ISysLogService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 日志表 前端控制器
 * </p>
 *
 * @author yuan
 * @since 2022-08-17
 */
@RestController
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
@RequestMapping("/sys-log")
public class SysLogController {

    @Autowired
    private ISysLogService sysLogService;
    /**
     * 插入日志
     * @param sysLog 日志实体
     * @return success/false
     */
    @Inner
    @PostMapping("/savelog")
    public Result<Boolean> save(@Valid @RequestBody SysLog sysLog) {
        return Result.ok(sysLogService.save(sysLog));
    }
}
