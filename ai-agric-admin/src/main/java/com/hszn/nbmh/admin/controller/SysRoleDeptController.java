package com.hszn.nbmh.admin.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色与部门对应关系 前端控制器
 * </p>
 *
 * @author yuan
 * @since 2022-09-06
 */
@Tag(name = "角色部门关系管理")
@Validated
@RestController
@RequestMapping("/sys-role-dept")
public class SysRoleDeptController {

}
