package com.hszn.nbmh.third.service;

import com.hszn.nbmh.common.core.mould.OssData;

import java.util.Map;

/**
 * @author wangjun
 */
public interface ExcelService {
    /**
     * 写入Excel
     *
     * @return
     */
    OssData exportExcel(Map<String, Object> map, Map<String, Object> exlParam) throws Exception;
}
