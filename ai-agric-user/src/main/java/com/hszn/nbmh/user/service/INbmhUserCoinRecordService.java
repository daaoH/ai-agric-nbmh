package com.hszn.nbmh.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.prevent.api.entity.NbmhVaccin;
import com.hszn.nbmh.user.api.entity.NbmhUserCoinRecord;

/**
 * <p>
 * 农牧币记录信息 服务类
 * </p>
 *
 * @author wangjun
 * @since 2022-09-14
 */
public interface INbmhUserCoinRecordService extends IService<NbmhUserCoinRecord> {

    /**
     * 分页查询
     *
     * @param param
     * @return IPage<NbmhUserCoinRecord>
     */
    IPage<NbmhUserCoinRecord> getByPage(QueryRequest<NbmhUserCoinRecord> param);

}
