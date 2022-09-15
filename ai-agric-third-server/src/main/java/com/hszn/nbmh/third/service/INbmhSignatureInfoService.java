package com.hszn.nbmh.third.service;

import com.hszn.nbmh.third.entity.NbmhSignatureInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 签名信息表 服务类
 * </p>
 *
 * @author lw
 * @since 2022-09-08
 */
public interface INbmhSignatureInfoService extends IService<NbmhSignatureInfo> {

    /**
     * 修改状态
     *
     * @param flowId
     * @param i
     */
    void updateStatus(String flowId, int status,String ulr);
}
