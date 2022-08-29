package com.hszn.nbmh.third.controller;

import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.third.entity.KdSearchEntity;
import com.hszn.nbmh.third.service.KdSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 快递鸟--快递查询服务 业务控制器
 * </p>
 *
 * @author MCR
 * @since 2022-08-26
 */
@Tag(name = "快递鸟--快递查询服务")
@RestController
@RequestMapping("/kd-search")
public class KdSearchController {

    @Autowired
    private KdSearchService kdSearchService;

    @Operation(summary = "查询快递信息", method = "POST")
    @Parameters({@Parameter(description = "快递公司编码", name = "shipperCode"), @Parameter(description = "物流单号", name = "logisticCode"), @Parameter(description = "手机号码（顺丰快递必填）", name = "mobile")})
    @PostMapping("/searchKdInfo")
    @Inner(false)
    public Result searchKdInfo(@RequestParam String shipperCode, @RequestParam String logisticCode, @RequestParam(value = "mobile", required = false) String mobile) {
        if (StringUtils.isBlank(logisticCode) || StringUtils.isBlank(logisticCode)) {
            return Result.failed("快递公司编码或者物流单号不能为空");
        }

        KdSearchEntity response = kdSearchService.searchKdInfo(shipperCode, logisticCode, mobile);
        if (response != null) {
            return Result.ok(response);
        }

        return Result.failed(CommonEnum.SMS_VALIDATE_FAIL.getMsg());
    }

}
