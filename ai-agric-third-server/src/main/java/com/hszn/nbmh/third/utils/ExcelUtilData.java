package com.hszn.nbmh.third.utils;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtilData {

    /**
     * @param tampPath  模板名称 例子（采购订单.ftl"）
     * @param excelName 最后生成的表格的名称(进货单平均价.xls)
     * @param filePath  生成的路径  例子（/采购订单.xls）
     * @throws FileNotFoundException
     */
    public static Map<String, Object> excelHandle(String tampPath, String excelName, String filePath, String avgFileTemplate) {
        Map<String, Object> map=new HashMap<String, Object>();
        // 模板所在的路径
        map.put("tempFoldPath", avgFileTemplate);
        // 生成的路径
        map.put("filePath", avgFileTemplate + filePath);
        // 模板名称
        map.put("tampPath", tampPath);
        // 最后生成的表格的名称
        map.put("excelName", excelName);
        return map;
    }
}
