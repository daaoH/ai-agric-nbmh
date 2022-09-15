package com.hszn.nbmh.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.user.api.entity.NbmhUserExtract;

/**
 * <p>
 * 用户提现表 服务类
 * </p>
 *
 * @author wangjun
 * @since 2022-09-15
 */
public interface INbmhUserExtractService extends IService<NbmhUserExtract> {

    /**
     * 分页查询
     *
     * @param param
     * @return IPage<NbmhUserExtract>
     */
    IPage<NbmhUserExtract> getByPage(QueryRequest<NbmhUserExtract> param);

}
