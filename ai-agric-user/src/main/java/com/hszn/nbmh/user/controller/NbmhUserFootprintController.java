package com.hszn.nbmh.user.controller;


import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.user.api.entity.NbmhUserFootprint;
import com.hszn.nbmh.user.service.INbmhUserFootprintService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户浏览足迹表 前端控制器
 * </p>
 *
 * @author yuan
 * @since 2022-08-25
 */
@Tag(name = "user-footprint", description = "用户浏览足迹接口")
@RestController
@RequestMapping("/user-footprint")
public class NbmhUserFootprintController {

    @Autowired
    private INbmhUserFootprintService footprintService;

    @Operation(description = "添加用户浏览足迹")
    @PostMapping("/addFootprint")
    public Result addFootprint(@RequestBody NbmhUserFootprint footprint){
        boolean ret = footprintService.save(footprint);
        if(ret){
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }
}
