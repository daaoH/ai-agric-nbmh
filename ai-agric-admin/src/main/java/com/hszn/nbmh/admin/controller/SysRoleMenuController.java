package com.hszn.nbmh.admin.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色菜单表 前端控制器
 * </p>
 *
 * @author yuan
 * @since 2022-09-06
 */
@Tag(name = "角色菜单管理")
@Validated
@RestController
@RequestMapping("/sys-role-menu")
public class SysRoleMenuController {

}
