package com.hszn.nbmh.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.log.annotation.SysLog;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.user.api.entity.SysOauthClientDetails;
import com.hszn.nbmh.user.service.ISysOauthClientDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.Tag;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.ru.INN;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 终端信息表 前端控制器
 * </p>
 *
 * @author yuan
 * @since 2022-08-16
 */

@Api(tags = "客户端管理模块")
@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class SysOauthClientDetailsController {

    private final ISysOauthClientDetailsService sysOauthClientDetailsService;

    /**
     * 通过ID查询
     * @param clientId 客户端id
     * @return SysOauthClientDetails
     */
    @GetMapping("/{clientId}")
    public Result<List<SysOauthClientDetails>> getByClientId(@PathVariable String clientId) {
        return Result.ok(sysOauthClientDetailsService
                .list(Wrappers.<SysOauthClientDetails>lambdaQuery().eq(SysOauthClientDetails::getClientId, clientId)));
    }

    /**
     * 简单分页查询
     * @param page 分页对象
     * @param sysOauthClientDetails 系统终端
     * @return
     */
    @GetMapping("/page")
    public Result<IPage<SysOauthClientDetails>> getOauthClientDetailsPage(Page page,
                                                                     SysOauthClientDetails sysOauthClientDetails) {
        return Result.ok(sysOauthClientDetailsService.page(page, Wrappers.query(sysOauthClientDetails)));
    }

    /**
     * 添加
     * @param sysOauthClientDetails 实体
     * @return success/false
     */
    @SysLog("添加终端")
    @PostMapping
    public Result<Boolean> add(@Valid @RequestBody SysOauthClientDetails sysOauthClientDetails) {
        return Result.ok(sysOauthClientDetailsService.save(sysOauthClientDetails));
    }

    /**
     * 删除
     * @param id ID
     * @return success/false
     */
    @SysLog("删除终端")
    @DeleteMapping("/{id}")
    public Result<Boolean> removeById(@PathVariable String id) {
        return Result.ok(sysOauthClientDetailsService.removeClientDetailsById(id));
    }

    /**
     * 编辑
     * @param sysOauthClientDetails 实体
     * @return success/false
     */
    @SysLog("编辑终端")
    @PutMapping
    public Result<Boolean> update(@Valid @RequestBody SysOauthClientDetails sysOauthClientDetails) {
        return Result.ok(sysOauthClientDetailsService.updateClientDetailsById(sysOauthClientDetails));
    }

    @SysLog("清除终端缓存")
    @DeleteMapping("/cache")
    public Result clearClientCache() {
        sysOauthClientDetailsService.clearClientCache();
        return Result.ok();
    }

    @Inner(false)
    @GetMapping("/getClientDetailsById/{clientId}")
    public Result getClientDetailsById(@PathVariable String clientId) {
        return Result.ok(sysOauthClientDetailsService.getOne(
                Wrappers.<SysOauthClientDetails>lambdaQuery().eq(SysOauthClientDetails::getClientId, clientId), false));
    }
}
