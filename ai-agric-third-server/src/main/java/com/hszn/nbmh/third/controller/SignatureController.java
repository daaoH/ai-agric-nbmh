package com.hszn.nbmh.third.controller;

import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.third.entity.NbmhSignatureInfo;
import com.hszn.nbmh.third.service.SignatureService;
import com.hszn.nbmh.third.utils.signature.bean.CallBackBean;
import com.hszn.nbmh.third.utils.signature.exception.DefineException;
import com.hszn.nbmh.third.utils.signature.factory.other.FileInfoQuery;
import com.hszn.nbmh.third.utils.signature.factory.response.FileInfoResponse;
import com.hszn.nbmh.third.utils.signature.factory.response.QrySignFieldResponse;
import com.hszn.nbmh.third.utils.signature.factory.signfile.signfields.QrySignField;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

/**
 * 电子签章服务类
 *
 * @author liwei
 * @version 1.0
 * @since 2022-09-07 14:06
 */
@Slf4j
@RestController
@RequestMapping("/signature")
@Tag(name = "电子签章服务类")
public class SignatureController {

    @Resource
    private SignatureService signatureService;

    @Operation(summary = "电子签章开始流程")
    @GetMapping("/start")
    Result<String> start() throws NoSuchAlgorithmException, DefineException {
        //开始流程
        signatureService.signatureStart();
        return Result.ok("操作成功");
    }

    @Operation(summary = "E签宝回调接口")
    @PostMapping("/callback")
    @Inner(value = false)
    Result<String> callback(@RequestBody CallBackBean bean) throws DefineException {
        signatureService.callbackHandler(bean);
        return Result.ok("处理成功").setMsg("success");
    }

    @Operation(summary = "合同模板展示url")
    @GetMapping("/fileReview")
    @Inner(value = false)
    Result<String> fileReview() throws DefineException {
        FileInfoQuery query = new FileInfoQuery(signatureService.getFileId());
        FileInfoResponse execute = query.execute();
        return Result.ok(execute.getData().getDownloadUrl());
    }


    @Operation(summary = "获取当前用户的签约信息")
    @GetMapping("/currentInfo")
    Result<NbmhSignatureInfo> currentInfo() throws DefineException {
        return Result.ok(signatureService.currentInfo());
    }

}
