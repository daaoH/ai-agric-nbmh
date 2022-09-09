package com.hszn.nbmh.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hszn.nbmh.admin.api.entity.SysUser;
import com.hszn.nbmh.admin.api.params.vo.SysLoginUser;
import com.hszn.nbmh.admin.service.ISysUserService;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author yuan
 * @since 2022-09-06
 */
@Tag(name = "sys-user", description = "后台管理用户接口")
@Validated
@RestController
@RequestMapping("/sys-user")
public class SysUserController {

    @Autowired
    private ISysUserService userService;

    /**
     * 获取指定用户全部信息
     *
     * @return 用户信息
     */
    @Inner
    @GetMapping("/info/{username}")
    public Result<SysLoginUser> info(@PathVariable String username) {
        SysUser user = userService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUserName, username));
        if (user == null) {
            return Result.failed(CommonEnum.DATA_NOT_EXIST.getMsg());
        }
        return Result.ok(userService.getUserInfo(user));
    }
}
