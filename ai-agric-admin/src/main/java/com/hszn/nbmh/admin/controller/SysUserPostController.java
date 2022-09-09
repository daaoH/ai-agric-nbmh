package com.hszn.nbmh.admin.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户与岗位关联表 前端控制器
 * </p>
 *
 * @author yuan
 * @since 2022-09-06
 */
@Tag(name = "用户岗位管理")
@Validated
@RestController
@RequestMapping("/sys-user-post")
public class SysUserPostController {

}
