package com.hszn.nbmh.prevent.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.prevent.api.entity.NbmhCheck;
import com.hszn.nbmh.prevent.api.params.input.CheckParam;

/**
 * <p>
 * 稽查上报 服务类
 * </p>
 *
 * @author wangjun
 * @since 2022-08-25
 */
public interface INbmhCheckService extends IService<NbmhCheck> {

    /**
     * 分页查询
     *
     * @param param
     * @return IPage
     */
    IPage<NbmhCheck> getByPage(QueryRequest<CheckParam> param);

}
