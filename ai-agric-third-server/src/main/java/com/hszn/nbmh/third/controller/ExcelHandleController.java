package com.hszn.nbmh.third.controller;


import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.third.configure.TemplatePathConfig;
import com.hszn.nbmh.third.service.ExcelService;
import com.hszn.nbmh.third.utils.ExcelUtilData;
import com.hszn.nbmh.third.vo.AccountBookAvgPriceVo;
import com.hszn.nbmh.third.vo.OtherExpenseBook;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author wangjun
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/excel")
@Tag(name="Excel处理")
public class ExcelHandleController {

    private final TemplatePathConfig templatePathConfig;

    private final ExcelService excelService;


    @PostMapping("/test")
    @Operation(summary="测试")
    public Result test() throws Exception {
        String tampPath="测试.ftl";
        String excelName="测试.xls";
        String file="/测试.xls";
        Map<String, Object> data=new HashMap<>();
        Map<String, List<AccountBookAvgPriceVo>> stringListMap=new HashMap<>();
        List<OtherExpenseBook> otherExpenseBook=new ArrayList<>();

        Map map=ExcelUtilData.excelHandle(tampPath, excelName, file, templatePathConfig.getTestFileTemplate());

        List<Map<String, Object>> dataList=new ArrayList<>();
        for (Map.Entry<String, List<AccountBookAvgPriceVo>> entry : stringListMap.entrySet()) {
            for (AccountBookAvgPriceVo vo : entry.getValue()) {
                Map<String, Object> dataMap=new HashMap<>();
                dataMap.put("avgPrice", vo.getAvgPrice());
                dataMap.put("goodsName", vo.getGoodsName());
                dataMap.put("totalPrice", vo.getTotalPrice());
                dataMap.put("totalQuantit", vo.getTotalQuantity());
                dataMap.put("list", vo.getAccountBookCategoryList());
                dataList.add(dataMap);
            }
        }
        Map<String, Object> resultMap=new HashMap<>();
        resultMap.put("totalTransportPrice", 1);
        resultMap.put("settledPrice", 2);
        resultMap.put("unSettledPrice", 3);
        resultMap.put("otherTotalPrice", 4);
        resultMap.put("pendingPayment", 5);

        // 封装数据
        Map<String, Object> exlParam=new HashMap<>();
        data.put("exporDate", new Date());
        exlParam.put("data", data);
        exlParam.put("findList", dataList);
        exlParam.put("totalData", resultMap);
        exlParam.put("otherExpenseBook", otherExpenseBook);
        return Result.ok(excelService.exportExcel(map, exlParam));
    }


}
