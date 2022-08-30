package com.hszn.nbmh.shop.controller;


import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.shop.api.entity.NbmhQuoteRecord;
import com.hszn.nbmh.shop.service.INbmhQuoteRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 报价表 前端控制器
 * </p>
 *
 * @author yuan
 * @since 2022-08-30
 */
@Tag(name = "quote-record", description = "报价记录接口")
@SecurityRequirement(name= HttpHeaders.AUTHORIZATION)
@RestController
@RequestMapping("/quote-record")
public class NbmhQuoteRecordController {


    @Autowired
    private INbmhQuoteRecordService quoteRecordService;

    @Inner(false)
    @Operation(summary = "提交报价")
    @PostMapping("/addQuoteRecord")
    public Result addQuoteRecord(@RequestBody NbmhQuoteRecord quoteRecord){
        boolean ret = quoteRecordService.save(quoteRecord);
        if(ret){
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_SUBMIT_FAILED.getMsg());
    }
}
