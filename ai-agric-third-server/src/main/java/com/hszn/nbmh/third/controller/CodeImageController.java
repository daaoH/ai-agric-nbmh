package com.hszn.nbmh.third.controller;


import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.hszn.nbmh.common.core.mould.CodeImageRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.third.service.CodeImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangjun
 */

@Slf4j
@Validated
@RestController
@RequestMapping("/cloud-qrcode")
@Tag(name="二维码")
public class CodeImageController {

    @Autowired
    private CodeImageService codeImageService;

    @PostMapping("/generate")
    @Operation(summary="生成二维码")
    public Result generate(@RequestBody CodeImageRequest param) {
        String res=codeImageService.generate(param);
        if (ObjectUtils.isNotEmpty(res)) {
            return Result.ok(res);
        }
        return Result.failed("二维码生成失败!");
    }
}
