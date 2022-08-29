package com.hszn.nbmh.third.controller;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.hszn.nbmh.common.core.mould.OssData;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.third.service.OssUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjun
 */
@Slf4j
@Validated
@RestController
@Scope("prototype")
@RequestMapping("/cloud-oss")
@Tag(name="阿里云oss上传")
public class OssUploadController {

    private final OssUploadService ossUploadService;

    @Value("${oss.fileHost}")
    String fileHost;

    @Autowired
    public OssUploadController(OssUploadService ossUploadService) {
        this.ossUploadService=ossUploadService;
    }

    /**
     * 获取服务器的ip地址
     *
     * @return ossData
     */
    @GetMapping("/getHost")
    @Inner(false)
    @Operation(summary="获取服务器的ip地址")
    public Result getHost() {
        OssData ossData=new OssData();
        ossData.setHost(fileHost);
        return Result.ok(ossData);
    }


    /**
     * oss上传文件(单个文件上传)
     *
     * @param file
     * @return
     */
    @PostMapping("/fileUpload")
    @Inner(false)
    @Operation(summary="oss上传文件(单个文件上传)")
    @Parameters({@Parameter(description="file", name="from-data提交方式 MultipartFile")})
    public Result fileUpload(@RequestParam(value="file", required=false) MultipartFile file) {
        // 上传文件返回url
        OssData fileData=ossUploadService.upload(file);
        if (ObjectUtils.isNotEmpty(fileData)) {
            return Result.ok(fileData);
        } else {
            return Result.failed("上传失败！请稍后在试！");
        }
    }

    /**
     * oss多文件上传
     *
     * @param files
     * @return
     */
    @PostMapping("/filesUpload")
    @Inner(false)
    @Operation(summary="oss上传文件(多文件上传)")
    @Parameters({@Parameter(description="files", name="from-data提交方式 MultipartFile")})
    public Result filesUpload(@RequestParam(value="files", required=false) List<MultipartFile> files) {
        List<OssData> fileDatas=new ArrayList<>();
        for (MultipartFile file : files) {
            OssData fileData=ossUploadService.upload(file);
            if (ObjectUtils.isNotEmpty(fileData)) {
                fileDatas.add(fileData);
            } else {
                return Result.failed("上传失败！请稍后在试！");
            }
        }
        return Result.ok(fileDatas);
    }
}
