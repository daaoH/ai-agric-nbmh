package com.hszn.nbmh.order.controller;


import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.service.AuthUser;
import com.hszn.nbmh.common.security.util.SecurityUtils;
import com.hszn.nbmh.order.api.entity.NbmhReceipt;
import com.hszn.nbmh.order.service.INbmhReceiptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 发票信息 前端控制器
 * </p>
 *
 * @author yuan
 * @since 2022-09-05
 */
@Tag(name = "receipt", description = "发票接口")
@RestController
@RequestMapping("/receipt")
public class NbmhReceiptController {

    @Autowired
    private INbmhReceiptService receiptService;

    @Operation(description = "开具发票")
    @PostMapping("/addReceipt")
    public Result addReceipt(@RequestBody NbmhReceipt receipt){
        AuthUser authUser = SecurityUtils.getUser();
        receipt.setUserId(authUser.getId());
        return Result.ok(receiptService.save(receipt));
    }
}
