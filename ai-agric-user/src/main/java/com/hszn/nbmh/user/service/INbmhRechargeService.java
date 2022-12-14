package com.hszn.nbmh.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.user.api.entity.NbmhRecharge;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 充值记录表 服务类
 * </p>
 *
 * @author yuan
 * @since 2022-09-08
 */
public interface INbmhRechargeService extends IService<NbmhRecharge> {

    /**
     * 分页查询
     *
     * @param param
     * @return IPage<NbmhUserExtract>
     */
    IPage<NbmhRecharge> getByPage(QueryRequest<NbmhRecharge> param);

}
